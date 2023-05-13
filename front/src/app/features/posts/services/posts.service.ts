import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserInterface} from "../../user/interfaces/user.interface";
import {PostInterface} from "../interfaces/post.interface";

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  private pathService = 'api/post';

  constructor(private httpClient: HttpClient) {
  }

  public getAllPostsByUser(user: UserInterface): Observable<PostInterface[]> {
    return this.httpClient.post<PostInterface[]>(`${this.pathService}`, user);
  }

  public createPost(post: PostInterface): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/new`, post);
  }

  public findById(id: number): Observable<PostInterface> {
    return this.httpClient.get<PostInterface>(`${this.pathService}/${id}`);
  }
}
