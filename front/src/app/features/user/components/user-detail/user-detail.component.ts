import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription, take} from "rxjs";
import {UserInterface, UserUpdate} from "../../interfaces/user.interface";
import {SubjectInterface} from "../../../subjects/interfaces/subject.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";
import {SessionService} from "../../../auth/services/session.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SessionInformation} from "../../../auth/interfaces/sessionInformation.interface";
import {SubscriptionService} from "../../services/subscription.service";
import {SubscriptionInterface} from "../../interfaces/subscription.interface";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit, OnDestroy {
  public onError = false;
  isDesktop!: boolean;
  subjects!: SubjectInterface[] | undefined;
  user: UserInterface | undefined;
  public form: FormGroup;
  public hide = true;
  private sub: Subscription;
  private REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$/;

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

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  ngOnInit(): void {
    this.update();
    this.initForm();
    this.sub = this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result: BreakpointState) => {
        if (result.matches) {
          this.isDesktop = false;
        } else {
          this.isDesktop = true;
        }
      });

    this.form.setValue({
      // @ts-ignore
      firstName: this.user.firstName,
      // @ts-ignore
      email: this.user.email,
      password: '',
    });
  }

  public submit(): void {
    const registerRequest = this.form.value as UserUpdate;
    registerRequest.id = <number>this.user?.id;
    registerRequest.subjects = this.user?.subjects;
    registerRequest.createdAt = <Date>this.user?.createdAt;
    console.log(registerRequest);
    this.userService
      .updateUser(registerRequest)
      .pipe(take(1))
      .subscribe({
        next: (response: SessionInformation) => {
          this.sessionService.setToken(response.token);
          this.showNotification('Your data have been updated', 7000);
        },
        error: error => this.showNotification('Server side error', 3000)
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
        error: error => this.showNotification('Server side error or a subscription doesn\'t exist', 3000)
      });
  }

  private initForm(): void {
    this.form = this.fb.group({
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
    this.user = this.sessionService.authUser;
    console.log(this.user)
    this.subjects = this.makeUserSubjectsSubscribeTrue(this.user?.subjects);
  }

  private makeUserSubjectsSubscribeTrue(subjects: SubjectInterface[] | undefined): SubjectInterface[] {
    // @ts-ignore
    return subjects.map((value: SubjectInterface) => {
      value.subscription = true;
      return value;
    });
  }
}
