export interface MessageInterface {
  id?: number;
  message: string;
  authorFirstName: string;
  authorId: number;
  postId: number;
  createdAt: Date;
}
