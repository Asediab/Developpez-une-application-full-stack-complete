import {Component, Input} from '@angular/core';
import {MessageInterface} from "../../interfaces/message.interface";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent {
  @Input() message!: MessageInterface;

  constructor() {
  }
}
