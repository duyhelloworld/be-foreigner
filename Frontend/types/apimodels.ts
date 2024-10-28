import { DiffLevel, LessonStatus, QuestionType, SubscriptionPlan, UserLevel, WordType } from "./enum";

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
  
  correctOption?: QuestionOption;
  incorrectOptions?: QuestionOption[];

  mainSentense?: string[];
  mainSentenseAudio?: any;
  unrelatedWords?: string[];

  correctAnswerAudio?: string;

  matching?: Map<string, string>;
};

export type Ranking = {
  users: RankingUser[],
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
  audio: string;
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

export type Topic = {
  id: number;
  name: string;
  coverImage: string;
  desc?: string;
  learnCount: number;
};

export type TopicDetail = {
  id: number;
  name: string;
  description?: string;
  diffLevel: DiffLevel;
  creator: User;
  words: Word[];
};

export type User = {
  id: string;
  username: string;
  avatar: string;
  fullname: string;
  email: string;
  plan: SubscriptionPlan;
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
