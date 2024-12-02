import { LessonStatus, LessonType, QuestionLevel, QuestionType, SubscriptionPlan, UserLevel } from "./enum";

export interface ApiResponse {
  code: number;
  data: Object;
}

export interface Notification {
  id: string;
  title: string;
  content: string;
  sendAt: number;
  isRead: boolean;
}

export interface Word {
  id: number;
  value: string;
  mean: string;
  image: string;
  audio: string;
  phonetic?: string;
  examples?: WordExample[];
};

export interface PageResponse<T> {
  totalPage: number;
  items: T[];
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
  elo: number;
  questions: Question[];
  historyId: number;
};

export interface LessonHistory {
  lessonId: number;
  lessonName: string;
  lessonImage: string;
  startAt: string;
  totalTime: string;
  status: LessonStatus;
  accuracy: number;
}

export interface Question {
  type: keyof typeof QuestionType;
  level: QuestionLevel;

  correctOptionMean?: string;
  correctOptionValue?: string;
  correctOptionAudio?: string;
  answerOptions?: AnswerOption[];

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
  value: string;
  image: string;
  audio: string;
  isTrue: boolean;
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
  level: UserLevel;
  plan: SubscriptionPlan;
  streakDays: number;
  isAllowEmail: boolean;
  isAllowNotification: boolean;
};

export interface Auth {
  refreshToken: string;
  accessToken: string;
};