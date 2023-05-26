import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {RegisterComponent} from './register.component';
import {AuthService} from "../../services/auth.service";
import {SessionService} from "../../../../service/session.service";
import {Router} from "@angular/router";
import {RouterTestingModule} from "@angular/router/testing";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {By} from "@angular/platform-browser";
import {throwError} from "rxjs";
import {RegisterRequest} from "../../interfaces/registerRequest";

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authService: AuthService;
  let sessionService: SessionService;
  let router: Router;

  let requestValid: RegisterRequest = {
    email: 'user@user.com',
    firstName: 'User',
    password: 'userPass'
  };
  let requestInValid: RegisterRequest = {
    email: 'user.com',
    firstName: '',
    password: ''
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      providers: [
        AuthService, SessionService, {provide: Router, useValue: {navigate: jest.fn()}}
      ],
      imports: [
        RouterTestingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    sessionService = TestBed.inject(SessionService);
    authService = TestBed.inject(AuthService);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  test('should create', () => {
    expect(component).toBeTruthy();
  });

  test('Submit button is enabled and the form is valid when the form is filled', fakeAsync(async () => {
    let email = component.form.controls['email'];
    let firstname = component.form.controls['firstName'];
    let lastName = component.form.controls['lastName'];
    let password = component.form.controls['password'];

    email.setValue(requestValid.email);
    firstname.setValue(requestValid.firstName);
    password.setValue(requestValid.password);

    fixture.detectChanges();
    const button = fixture.debugElement.query(
      By.css('button[type="submit"]')
    );

    expect(button.nativeElement.disabled).toBeFalsy();
    expect(component.form.valid).toBeTruthy();
  }));

  test('Submit button is disabled and the form is invalid when the form isn\'t filled', fakeAsync(async () => {
    const button = fixture.debugElement.query(
      By.css('button[type="submit"]')
    );
    expect(button.nativeElement.disabled).toBeTruthy();
    expect(component.form.valid).toBeFalsy();
  }));

  test('See an error when Send a request with invalid RegisterRequest', async () => {
    let routerSpy = jest.spyOn(router, 'navigate');
    jest.spyOn(authService, 'register').mockReturnValue(throwError(() => new Error('')));

    authService.register(requestInValid).subscribe();

    let object = fixture.debugElement.query(By.css('.register-form'));
    object.triggerEventHandler('submit', undefined);

    fixture.detectChanges();

    let error = fixture.debugElement.query(By.css('.error')).nativeElement;
    expect(error).toBeTruthy();
    expect(routerSpy).not.toBeCalled();
  });

  test('Redirect to /login when Send a request with valid RegisterRequest', async () => {
    //jest.spyOn(authService, 'register').mockReturnValue(of(undefined));
    jest.spyOn(sessionService, 'logIn');
    jest.spyOn(component, 'submit');

    authService.register(requestValid).subscribe();

    let object = fixture.debugElement.query(By.css('.register-form'));
    object.triggerEventHandler('submit', undefined);

    fixture.detectChanges();

    expect(component.submit).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });

  test('Invalid firstName', async () => {
    const firstname = component.form.controls['firstName'];
    firstname.setValue(requestValid.firstName);
    fixture.detectChanges();
    expect(firstname.valid).toBeTruthy();
    firstname.setValue(requestInValid.firstName);
    fixture.detectChanges();
    expect(firstname.valid).toBeFalsy();
  });

  test('Invalid email', async () => {
    const email = component.form.controls['email'];
    email.setValue(requestValid.email);
    fixture.detectChanges();
    expect(email.valid).toBeTruthy();
    email.setValue(requestInValid.email);
    fixture.detectChanges();
    expect(email.valid).toBeFalsy();
  });

  test('Invalid password', async () => {
    const password = component.form.controls['password'];
    password.setValue(requestValid.password);
    fixture.detectChanges();
    expect(password.valid).toBeTruthy();
    password.setValue(requestInValid.password);
    fixture.detectChanges();
    expect(password.valid).toBeFalsy();
  });
});
