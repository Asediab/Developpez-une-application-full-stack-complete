import {MessageInterface} from "./message.interface";

export interface PostInterface {
  id?: number;
  title: string;
  description: string;
  authorId: number;
  authorFirstName: string;
  subjectId: number;
  subject: string;
  createdAt?: Date;
  updatedAt?: Date;
  messages?: MessageInterface[];
}
