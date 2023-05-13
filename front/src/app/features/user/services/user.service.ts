import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserInterface} from "../interfaces/user.interface";
import {RegisterRequest} from "../../auth/interfaces/registerRequest";
import {SessionInformation} from "../../auth/interfaces/sessionInformation.interface";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) {
  }

  public getUserById(id: number): Observable<UserInterface> {
    return this.httpClient.get<UserInterface>(`${this.pathService}/${id}`);
  }

  public updateUser(user: RegisterRequest): Observable<SessionInformation> {
    return this.httpClient.put<SessionInformation>(`${this.pathService}`, user);
  }

  public deleteUser(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/${id}`);
  }
}
