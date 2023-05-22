import {Component, OnInit} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {Router} from "@angular/router";
import {PostInterface} from "../../interfaces/post.interface";
import {MatSnackBar} from "@angular/material/snack-bar";
import {take} from "rxjs";

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {
  posts!: PostInterface[];
  error: boolean = false;
  sortDirection: 'ascending' | 'descending' = 'ascending';

  constructor(
    private postsService: PostsService,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.postsService.getAllPostsByUser()
      .pipe(take(1))
      .subscribe({
        next: (value: PostInterface[]) => {
          this.posts = value;
        },
        error: err => this.matSnackBar.open('Posts list is empty', '', {
          duration: 3000,
        })
      });
  }


  orderByDAte() {
    this.sortDirection =
      this.sortDirection === 'ascending' ? 'descending' : 'ascending';
    this.posts = this.posts.reverse();
  }

  newPost() {
    this.router.navigateByUrl(`posts/create`);
  }
}
