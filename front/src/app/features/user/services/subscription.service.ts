import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionInterface} from "../interfaces/subscription.interface";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private pathService = 'api/subscription';

  constructor(private httpClient: HttpClient) {
  }

  public subscribeUser(subscription: SubscriptionInterface): Observable<SubscriptionInterface> {
    return this.httpClient.post<SubscriptionInterface>(`${this.pathService}/sub`, subscription);
  }

  public unSubscribeUser(subscription: SubscriptionInterface): Observable<SubscriptionInterface> {
    return this.httpClient.post<SubscriptionInterface>(`${this.pathService}/unsub`, subscription);
  }
}
