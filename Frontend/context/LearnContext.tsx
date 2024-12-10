import { createContext, useRef } from "react";

export type QuestionResult = {
  enabled: boolean;
  isCorrect: boolean;
  message: string;
}

export const LearnContext = createContext(
  {} as React.MutableRefObject<QuestionResult>
);

export const useQuestionRef = () => useRef<QuestionResult>({} as QuestionResult);