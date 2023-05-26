import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {Location} from '@angular/common';
import {RegisterRequest} from "../../interfaces/registerRequest";
import {SessionService} from "../../../../service/session.service";
import {SessionInformation} from "../../interfaces/sessionInformation.interface";
import {take} from "rxjs";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public onError = false;
  public hide = true;
  public form!: FormGroup;
  private REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$/;

  ngOnInit(): void {
    this.form = this.fb.group({
      email: [
        '',
        [
          Validators.required,
          Validators.email
        ]
      ],
      firstName: [
        '',
        [
          Validators.required,
          Validators.min(3),
          Validators.max(20)
        ]
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern(this.REGEX)
        ]
      ]
    });
  }

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService,
              private location: Location) {
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest)
      .pipe(take(1))
      .subscribe({
        next: (response: SessionInformation) => {
          this.sessionService.logIn(response);
          this.router.navigate(['/posts']);
        },
        error: error => this.onError = true,
      });
  }

  public return() {
    this.location.back();
  }
}

