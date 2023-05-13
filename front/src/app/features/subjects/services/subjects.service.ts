import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubjectInterface} from "../interfaces/subject.interface";

@Injectable({
  providedIn: 'root'
})
export class SubjectsService {
  private pathService = 'api/subject';

  constructor(private httpClient: HttpClient) {
  }

  public getAllSubjects(): Observable<SubjectInterface[]> {
    return this.httpClient.get<SubjectInterface[]>(`${this.pathService}`);
  }
}
