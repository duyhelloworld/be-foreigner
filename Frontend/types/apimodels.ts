import {
  LeaderBoardType,
  LessonStatus,
  LessonType,
  QuestionLevel,
  QuestionType,
  SubscriptionPlan,
  UserLevel,
} from "./enum";

export interface ApiResponse {
  code: number;
  data: Object;
}

export interface Notification {
  id: string;
  title: string;
  content: string;
  isRead: boolean;
  lessonId?: number;
}

export interface Remind {
  lessonId: number;
}

export function isRemind(obj: any): obj is Remind {
  return (
    typeof obj === "object" && obj !== null && typeof obj.lessonId === "number"
  );
}

export interface Word {
  id: number;
  value: string;
  mean: string;
  image: string;
  audio: string;
  phonetic?: string;
  examples?: WordExample[];
}

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
}

export interface LessonDetail {
  id: number;
  name: string;
  elo: number;
  questions: Question[];
  historyId: number;
}

export interface LessonHistory {
  historyId: number;
  lessonId: number;
  lessonName: string;
  lessonImage: string;
  elo: number;
  startedAt: string;
  completedAt: string;
  totalTime: string | null;
  accuracy: number | null;
  status: keyof typeof LessonStatus;
}

export interface Streak {
  hasLearned: boolean;
  streakDays: number;
}

export interface Question {
  type: keyof typeof QuestionType;
  level: keyof typeof QuestionLevel;
  index: number;

  correctOptionMean?: string;
  correctOptionValue?: string;
  correctOptionAudio?: string;
  answerOptions?: AnswerOption[];

  sentenseMeaning?: string;
  sentenseAudio?: string;
  sentenseWords?: string[];
  unrelatedWords?: string[];
}

export interface LeaderBoard {
  users: LeaderBoardUser[];
  type: keyof typeof LeaderBoardType;
}

export interface LeaderBoardUser {
  elo: number;
  userRank: number;
  username: string;
  avatar: string;
  userId: number;
}

export interface AnswerOption {
  value: string;
  image: string;
  audio: string;
  isTrue: boolean;
}

export interface WordExample {
  sentense: string;
  mean: string;
}

export interface UserInfo {
  id: string;
  username: string;
  avatar: string;
  fullname: string;
  email: string;
  level: UserLevel;
  streakDays: number;
  plan: SubscriptionPlan;
  isAllowEmail: boolean;
  isAllowNotification: boolean;
}

export interface Auth {
  refreshToken: string;
  accessToken: string;
}
