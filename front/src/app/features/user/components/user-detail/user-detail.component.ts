import {Component, OnInit} from '@angular/core';
import {take} from "rxjs";
import {UserInterface} from "../../interfaces/user.interface";
import {SubjectInterface} from "../../../subjects/interfaces/subject.interface";
import {FormBuilder, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";
import {SessionService} from "../../../auth/services/session.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {RegisterRequest} from "../../../auth/interfaces/registerRequest";
import {SessionInformation} from "../../../auth/interfaces/sessionInformation.interface";
import {SubscriptionService} from "../../services/subscription.service";
import {SubscriptionInterface} from "../../interfaces/subscription.interface";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  public onError = false;
  isDesktop!: boolean;
  subjects!: SubjectInterface[] | undefined;
  user!: UserInterface | undefined;
  public hide = true;
  private REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$/;
  public form = this.fb.group({
    firstName: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(20)
      ]
    ],
    email: ['', [Validators.required, Validators.email]],
    password: [
      '',
      [
        Validators.required,
        Validators.pattern(this.REGEX)
      ]
    ]
  });

  constructor(
    private userService: UserService,
    private subscriptionService: SubscriptionService,
    private breakpointObserver: BreakpointObserver,
    private fb: FormBuilder,
    private sessionService: SessionService,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result: BreakpointState) => {
        if (result.matches) {
          this.isDesktop = false;
        } else {
          this.isDesktop = true;
        }
      });
    this.update();
    this.user = this.sessionService.authUser;
    this.form.setValue({
      // @ts-ignore
      firstName: this.user.firstName,
      // @ts-ignore
      email: this.user.email,
      password: '',
    });
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.userService
      .updateUser(registerRequest)
      .pipe(take(1))
      .subscribe({
        next: (response: SessionInformation) => {
          this.sessionService.setToken(response.token);
          const msg =
            'Your data have been updated';
          this.showNotification(msg, 7000);
        },
        error: error => this.onError = true,
      });
  }

  unSubscribe(id: number) {
    // @ts-ignore
    let subscription: SubscriptionInterface = {subjectId: id, userId: this.user.id};
    this.subscriptionService.unSubscribeUser(subscription)
      .pipe(take(1))
      .subscribe({
        next: (response) => {
          this.update();
          this.showNotification('Unsubscribed', 3000);
          return response;
        },
        error: error => this.onError = true,
      });
  }

  public logOut() {
    this.sessionService.clearData();
    this.router.navigate(['/']);
    this.showNotification('You have been successfully logged out', 3000);
  }

  private showNotification(msg: string, duration: number) {
    this.matSnackBar.open(msg, '', {
      duration: duration,
      panelClass: ['multiline-snackbar'],
    });
  }

  private update() {
    this.sessionService.updateUser();
    this.subjects = this.user?.subjects;
  }
}
