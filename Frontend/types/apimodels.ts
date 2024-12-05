import { Sound } from "expo-av/build/Audio";
import { LeaderBoardType, LessonStatus, LessonType, QuestionLevel, QuestionType, SubscriptionPlan, UserLevel } from "./enum";

export interface ApiResponse {
  code: number;
  data: Object;
}

export interface Notification {
  id: number;
  title: string;
  content: string;
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
  elo: number;
  startAt: string;
  totalTime: string;
  accuracy: number;
  status: keyof typeof LessonStatus;
}

export interface Question {
  type: keyof typeof QuestionType;
  level: QuestionLevel;
  index: number;

  correctOptionMean?: string;
  correctOptionValue?: string;
  correctOptionAudio?: string | Sound;
  answerOptions?: AnswerOption[];

  sentenseMeaning?: string;
  sentenseAudio?: string | Sound;
  sentenseWords?: string[];
  unrelatedWords?: string[];
};

export interface LeaderBoard {
  users: LeaderBoardUser[],
  type: keyof typeof LeaderBoardType
}

export interface LeaderBoardUser {
  elo: number;
  userRank: number;
  username: string;
  avatar: string;
  userId: number;
};

export interface AnswerOption {
  value: string;
  image: string;
  audio: string | Sound;
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
  isAllowEmail: boolean;
  isAllowNotification: boolean;
};

export interface Auth {
  refreshToken: string;
  accessToken: string;
};