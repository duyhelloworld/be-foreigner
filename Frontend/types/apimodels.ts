import { LessonStatus, LessonType, QuestionType, SubscriptionPlan, UserLevel, WordType } from "./enum";

export interface ApiResponse {
  code: number;
  data: Object;
}

export interface Word {
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

export interface PageResponse<T> {
  totalPage: number;
  items: T[];
}

export function isError(data: any): data is string[] {
  return Array.isArray(data) && data.every((item) => typeof item === "string");
}


export interface Lesson {
  id: number;
  name: string;
  cover: string;
  type: LessonType;
  status: LessonStatus;
};

export interface LessonDetail {
  id: number;
  name: string;
  questions: Question[];
  experiences: number;
  diamonds: number;
};


export interface Question {
  type: keyof typeof QuestionType;

  option?: AnswerOption;
  unrelatedOptions?: AnswerOption[];

  sentenseMeaning?: string;
  sentenseAudio?: string;
  sentenseWords?: string[];
  unrelatedWords?: string[];
};

export interface Ranking {
  users: RankingUser[],
  fetchedOn: string
}

export interface RankingUser {
  elo: number;
  userRank: number;
  username: string;
  avatar: string;
};

export interface AnswerOption {
  text: string;
  image: string;
  audio: string;
}

export interface WordExample {
  sentense: string;
  mean: string;
};

export interface User {
  id: string;
  username: string;
  avatar: string;
};

export interface UserInfo {
  id: string;
  username: string;
  avatar: string;
  fullname: string;
  email: string;
  diamond: number;
  experiences: number;
  level: UserLevel;
  plan: SubscriptionPlan;
};

export interface Auth {
  refreshToken: string;
  accessToken: string;
};