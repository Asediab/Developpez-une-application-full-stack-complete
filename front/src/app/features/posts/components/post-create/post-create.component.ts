import {Component, OnInit} from '@angular/core';
import {SubjectInterface} from "../../../subjects/interfaces/subject.interface";
import {FormBuilder, Validators} from "@angular/forms";
import {PostInterface} from "../../interfaces/post.interface";
import {SubjectsService} from "../../../subjects/services/subjects.service";
import {PostsService} from "../../services/posts.service";
import {Router} from "@angular/router";
import {Location} from '@angular/common';
import {MatSnackBar} from "@angular/material/snack-bar";
import {take} from "rxjs";
import {UserInterface} from "../../../user/interfaces/user.interface";

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss']
})
export class PostCreateComponent implements OnInit {
  subjects!: SubjectInterface[];
  user: UserInterface | undefined;
  public onError = false;
  private REGEX_ID: RegExp = /^[1-9][0-9]*$/;

  public form = this.fb.group({
    subjectId: [
      0,
      [Validators.required, Validators.pattern(this.REGEX_ID)],
    ],
    title: ['', [Validators.required, Validators.min(5), Validators.max(100)]],
    description: [
      '',
      [Validators.required, Validators.min(20)],
    ],
  });

  constructor(
    private subjectsService: SubjectsService,
    private postsService: PostsService,
    private fb: FormBuilder,
    private router: Router,
    private matSnackBar: MatSnackBar,
    private location: Location
  ) {
  }

  showNotification(msg: string, duration: number) {
    this.matSnackBar.open(msg, '', {
      duration: duration,
      panelClass: ['multiline-snackbar'],
    });
  }

  public submit(): void {
    const postRequest = this.form.value as PostInterface;
    this.postsService
      .createPost(postRequest)
      .pipe(take(1))
      .subscribe({
        next: (value: PostInterface) => {
          const msg = 'Your article ' + value.title + ' is created!';
          this.showNotification(msg, 2000);
          this.router.navigate(['posts']);
        },
        error: (error) => {

        },
      });
  }

  ngOnInit(): void {
    this.subjectsService.getAllSubjects().subscribe({
        next: (value: SubjectInterface[]) => {
          this.subjects = value;
        },
        error: err => {
        }
      }
    );
  }

  return() {
    this.location.back();
  }
}
