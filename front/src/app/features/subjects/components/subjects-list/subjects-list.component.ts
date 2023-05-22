import {Component, OnDestroy, OnInit} from '@angular/core';
import {SubjectsService} from "../../services/subjects.service";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Subscription, take} from "rxjs";
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
export class SubjectsListComponent implements OnInit, OnDestroy {
  isDesktop!: boolean;
  public onError: boolean = false;
  userSubjects!: SubjectInterface[] | undefined;
  subjects!: SubjectInterface[] | undefined;
  user!: UserInterface | undefined;
  private sub: Subscription;

  constructor(
    private subjectService: SubjectsService,
    private subscriptionService: SubscriptionService,
    private breakpointObserver: BreakpointObserver,
    private sessionService: SessionService,
    private matSnackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.update();
    this.sub = this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result: BreakpointState) => {
        this.isDesktop = !result.matches;
      });
  }

  private update() {
    this.sessionService.updateUser();
    this.user = this.sessionService.authUser;
    this.userSubjects = this.user?.subjects;
    this.initSubjects();
  }

  subscription(id: number) {
    // @ts-ignore
    let subscription: SubscriptionInterface = {subjectId: id, userId: this.user.id};
    this.subscriptionService.subscribeUser(subscription)
      .pipe(take(1))
      .subscribe({
        next: (response) => {
          this.matSnackBar.open('Subscribed', '', {
            duration: 3000,
          });
          return response;
        },
        error: error => this.matSnackBar.open('Server error or subscription exists', '', {
          duration: 3000,
        })
      });
    this.update();
  }

  private initSubjects(): void {
    let userSubject: SubjectInterface[] = this.makeUserSubjectsSubscribeTrue(this.userSubjects)
    let allSubjects: SubjectInterface[];
    this.subjectService.getAllSubjects()
      .pipe(take(1))
      .subscribe({
        next: (value) => {
          allSubjects = value;
          this.subjects = this.uniqueObjectArray(userSubject, allSubjects);
        }
      });

  }

  private makeUserSubjectsSubscribeTrue(subjects: SubjectInterface[] | undefined): SubjectInterface[] {
    // @ts-ignore
    return subjects.map((value: SubjectInterface) => {
      value.subscription = true;
      return value;
    });
  }

  private uniqueObjectArray(baseArray: SubjectInterface[], mergeArray: SubjectInterface[]) {
    // we can't compare unique objects within an array ~es6...
    // ...e.g. concat/destructure/Set()
    // so we'll create a mapping of: item.id* for each -> item
    const uniqueMap = new Map();
    const uniqueArray: SubjectInterface[] = [];

    // hash array items by id*
    baseArray.forEach(item => !uniqueMap.has(item.id) && uniqueMap.set(item.id, item))
    mergeArray.forEach(item => !uniqueMap.has(item.id) && uniqueMap.set(item.id, item))

    // hash -> array
    uniqueMap.forEach(item => uniqueArray.push(item))
    return uniqueArray
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
