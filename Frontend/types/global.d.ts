declare global {

  type Word = {
    id: number;
    value: string;
    mean: string;
    example: string[];
    phonetic?: string;
    audio?: string;
  }

  type Card = {
    id: number;
    word: Word;
    image: string;
  }

  type Deck = {
    id: number;
    name: string;
    cover: string;
  }

  type DeckInfo = {
    id: number;
    name: string;
    description?: string;
    cards: Card[],
    diffLevel: DiffLevel
  }
}

export type Routes = {
  Home: undefined,
  Learn: {deckId: number},
  Profile: undefined,
  OnBoard: undefined,
  MyDeckLibrary: undefined,
  Login: undefined,
  Search: undefined,
}

declare module "*.png";
declare module "*.svg";
declare module "*.jpeg";
declare module "*.jpg";

export {};
