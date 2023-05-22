import {Component, OnInit} from '@angular/core';
import {PostInterface} from "../../interfaces/post.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PostsService} from "../../services/posts.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Location} from '@angular/common';
import {MessageInterface} from "../../interfaces/message.interface";
import {CommentsService} from "../../services/comments.service";
import {take} from "rxjs";
import {SessionService} from "../../../auth/services/session.service";
import {UserInterface} from "../../../user/interfaces/user.interface";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {
  user: UserInterface | undefined;
  post!: PostInterface;
  public form: FormGroup;
  public onError = false;

  constructor(
    private route: ActivatedRoute,
    private postsService: PostsService,
    private sessionService: SessionService,
    private commentsService: CommentsService,
    private router: Router,
    private location: Location,
    private fb: FormBuilder,
    private matSnackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      message: ['', [Validators.required, Validators.minLength(3)]],
    });

    this.user = this.sessionService.authUser;
    this.update();
  }

  showNotification(msg: string, duration: number) {
    this.matSnackBar.open(msg, '', {
      duration: duration,
      panelClass: ['multiline-snackbar'],
    });
  }

  update() {
    const postId = +this.route.snapshot.params['id'];
    this.postsService.findById(postId)
      .pipe(take(1))
      .subscribe({
          next: (value: PostInterface) => {
            this.post = value;
          },
          error: err => {
            this.router.navigate(['/404']);
          }
        }
      );
  }

  public submit(): void {
    const postId = +this.route.snapshot.params['id'];
    const userId: number | undefined = this.user?.id;
    const commentRequest = this.form.value as MessageInterface;
    commentRequest.postId = postId;
    if (typeof userId === "number") {
      commentRequest.authorId = userId;
    }
    this.commentsService
      .createMessage(commentRequest)
      .pipe(take(1))
      .subscribe({
        next: (response: MessageInterface) => {
          this.update();
          this.showNotification('Your comment has been published', 2000);
          return response;
        },
        error: (error) => {
          this.showNotification('Server error, please try again', 2000);
        },
      });
  }

  return() {
    this.location.back();
  }
}
