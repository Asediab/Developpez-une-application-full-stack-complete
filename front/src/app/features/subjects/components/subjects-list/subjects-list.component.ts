import {Component, OnInit} from '@angular/core';
import {SubjectsService} from "../../services/subjects.service";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";
import {MatSnackBar} from "@angular/material/snack-bar";
import {take} from "rxjs";
import {SubscriptionInterface} from "../../../user/interfaces/subscription.interface";
import {UserInterface} from "../../../user/interfaces/user.interface";
import {SessionService} from "../../../auth/services/session.service";
import {SubscriptionService} from "../../../user/services/subscription.service";
import {SubjectInterface} from "../../interfaces/subject.interface";

@Component({
  selector: 'app-subjects-list',
  templateUrl: './subjects-list.component.html',
  styleUrls: ['./subjects-list.component.scss']
})
export class SubjectsListComponent implements OnInit {
  isDesktop!: boolean;
  public onError: boolean = false;
  subjects!: SubjectInterface[] | undefined;
  user!: UserInterface | undefined;

  constructor(
    private subjectService: SubjectsService,
    private subscriptionService: SubscriptionService,
    private breakpointObserver: BreakpointObserver,
    private sessionService: SessionService,
    private matSnackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.user = this.sessionService.authUser;
    this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result: BreakpointState) => {
        this.isDesktop = !result.matches;
      });
    this.update();
  }

  update() {
    this.sessionService.updateUser();
    this.subjects = this.user?.subjects;
  }

  subscription(id: number) {
    // @ts-ignore
    let subscription: SubscriptionInterface = {subjectId: id, userId: this.user.id};
    this.subscriptionService.subscribeUser(subscription)
      .pipe(take(1))
      .subscribe({
        next: (response) => {
          this.update();
          this.matSnackBar.open('Subscribed', '', {
            duration: 3000,
          });
          return response;
        },
        error: error => this.onError = true,
      });
  }
}
