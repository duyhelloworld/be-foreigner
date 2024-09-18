import { DiffLevel, WordType } from "./enum";

declare global {

  type Word = {
    id: number;
    value: string;
    mean: string;
    image?: string;
    audio?: string;
    hint?: string;
    phonetic?: string;
    examples: Example[];
    type?: WordType;
  }

  type Example = {
    sentense: string;
    mean: string;
  }

  type Topic = {
    id: number;
    name: string;
    cover: string;
  }

  type TopicDetail = {
    id: number;
    name: string;
    description?: string;
    diffLevel: DiffLevel;
    words: Word[];
  }
}

export type Routes = {
  Home: undefined,
  Learn: {topicId: number},
  Profile: undefined,
  OnBoard: undefined,
  MyTopicLibrary: undefined,
  Login: undefined,
  Search: undefined,
}

declare module "*.png";
declare module "*.svg";
declare module "*.jpeg";
declare module "*.jpg";

export {};
