import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {SessionService} from "../../../../services/session.service";
import {LoginRequest} from "../../interfaces/loginRequest";
import {SessionInformation} from "../../../../interfaces/sessionInformation.interface";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public hide = true;
  public onError = false;
  public form!: FormGroup;
  private REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$/;


  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      email: [
        '',
        [
          Validators.required,
          Validators.email
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

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.logIn(response);
        // TODO navigate and AuthService
        this.router.navigate(['']);
      },
      error: error => this.onError = true,
    });
  }
}
