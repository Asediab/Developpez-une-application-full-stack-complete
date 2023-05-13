import {Component, EventEmitter, Input, Output} from '@angular/core';
import {SubjectInterface} from "../../interfaces/subject.interface";

@Component({
  selector: 'app-subject-card',
  templateUrl: './subject-card.component.html',
  styleUrls: ['./subject-card.component.scss']
})
export class SubjectCardComponent {
  @Input() isDesktop!: boolean;
  @Input() subject!: SubjectInterface;
  @Output() subscribe = new EventEmitter<number>();

  constructor() {
  }

  public subscription() {
    this.subscribe.emit(this.subject.id);
  }
}
