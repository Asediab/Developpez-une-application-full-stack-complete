import {Component, Input} from '@angular/core';
import {PostInterface} from "../../interfaces/post.interface";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent {
  @Input() post!: PostInterface;

  constructor(private router: Router) {
  }

  onPostDetail() {
    this.router.navigateByUrl(`posts/${this.post.id}`);
  }
}
