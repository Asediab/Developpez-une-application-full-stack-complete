import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PostInterface} from "../interfaces/post.interface";

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  private pathService = 'api/post';

  constructor(private httpClient: HttpClient) {
  }

  public getAllPostsByUser(): Observable<PostInterface[]> {
    return this.httpClient.get<PostInterface[]>(`${this.pathService}`);
  }

  public createPost(post: PostInterface): Observable<PostInterface> {
    return this.httpClient.post<PostInterface>(`${this.pathService}/new`, post);
  }

  public findById(id: number): Observable<PostInterface> {
    return this.httpClient.get<PostInterface>(`${this.pathService}/${id}`);
  }
}
