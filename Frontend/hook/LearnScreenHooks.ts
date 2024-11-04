import { createContext } from "react";

export type QuestionResult = {
  enabled: boolean;
  isCorrect: boolean;
  message: string;
}

export const LearnScreenContext = createContext(
  {} as React.MutableRefObject<QuestionResult>
);