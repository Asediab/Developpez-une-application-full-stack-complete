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

export interface UserUpdate {
  id: number;
  email: string;
  firstName: string;
  password: string
  admin?: boolean;
  subjects?: SubjectInterface[];
  createdAt: Date;
  updatedAt?: Date;
}
