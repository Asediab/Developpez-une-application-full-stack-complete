import {SubjectInterface} from "../../subjects/interfaces/subject.interface";

export interface UserInterface {
  id?: number;
  email: string;
  firstName: string;
  admin?: boolean;
  subjects?: SubjectInterface[];
  createdAt: Date;
  updatedAt?: Date;
}
