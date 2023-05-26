import {Injectable} from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {SessionService} from "../service/session.service";

@Injectable({providedIn: 'root'})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService) {
  }

  public intercept(request: HttpRequest<any>, next: HttpHandler) {
    if (this.sessionService.getToken()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.sessionService.getToken()}`,
        },
      });
    }
    return next.handle(request);
  }
}
