import { LessonStatus, QuestionType, SubscriptionPlan, UserLevel, WordType } from "./enum";

export interface ApiErrorResponse {
  errorCode: number;
  messages: string[];
}

export function isApiErrorResponse(obj: any): obj is ApiErrorResponse {
  return (
    obj && typeof obj.errorCode === "number" && Array.isArray(obj.messages)
  );
}

export type Word = {
  id: number;
  value: string;
  mean: string;
  image: string;
  audio: string;
  hint?: string;
  phonetic?: string;
  type?: WordType;
  examples?: WordExample[];
};

export type Lesson = {
  id: number;
  name: string;
  cover: string;
  lastLearnQuestion: number;
  totalQuestion: number;
  status: LessonStatus;
};

export type LessonDetail = {
  id: number;
  name: string;
  questions: Question[];
  experiences: number;
  diamonds: number;
};

export type Question = {
  type: QuestionType;
  
  incorrectOptions?: QuestionOption[];

  mainSentense?: string[];
  mainSentenseAudio?: any;
  unrelatedWords?: string[];

  matching?: Map<string, string>;
};

export type Ranking = {
  users: RankingUser[],
  fetchedOn: Date
}

export type RankingUser = {
  rank: number;
  username: string;
  avatar: string;
  experience: number;
};

export type QuestionOption = {
  text: string;
  image: string;
}

export type Task = {
  id: number;
  name: string;
  total: number;
  current: number;
  award: TaskAward
}

export type TaskAward = {
  diamonds?: number;
  experiences?: number;
}

export type WordExample = {
  sentense: string;
  mean: string;
};

export type User = {
  id: string;
  username: string;
  avatar: string;
};

export type UserInfo = {
  id: string;
  username: string;
  avatar: string;
  fullname: string;
  email: string;
  diamond: number;
  experiences: number;
  level: UserLevel;
};

export type Auth = {
  refreshToken: string;
  accessToken: string;
};

export function isAuth(obj: any): obj is Auth {
  return (
    obj && typeof obj.refreshToken === "string" && obj.accessToken === "string"
  );
}
