import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MessageInterface} from "../interfaces/message.interface";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {
  private pathService = 'api/message';

  constructor(private httpClient: HttpClient) {
  }

  public createMessage(message: MessageInterface): Observable<any> {
    return this.httpClient.post<any>(`${this.pathService}`, message);
  }
}
