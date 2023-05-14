import {Component, OnInit} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {Router} from "@angular/router";
import {UserInterface} from "../../../user/interfaces/user.interface";
import {SessionService} from "../../../auth/services/session.service";
import {PostInterface} from "../../interfaces/post.interface";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {
  posts!: PostInterface[];
  user!: UserInterface | undefined;
  error: boolean = false;
  sortDirection: 'ascending' | 'descending' = 'ascending';

  constructor(
    private postsService: PostsService,
    private sessionService: SessionService,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.postsService.getAllPostsByUser().subscribe({
      next: (value: PostInterface[]) => {
        this.posts = value;
      },
      error: err => this.matSnackBar.open(err, '', {
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
