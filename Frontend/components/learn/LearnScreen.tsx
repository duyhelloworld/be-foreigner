import { StyleSheet, Text, View } from "react-native";
import React, { createContext, useEffect, useRef, useState } from "react";
import { QuestionType } from "../../types/enum";
import GiveMeanChooseWordView from "./questions/GiveMeanChooseWordView";
import GiveWordsRearrangeMeansView from "./questions/GiveWordsRearrangeMeansView";
import MatchWordsToMeansView from "./questions/MatchWordsToMeansView";
import { sampleLesson, sampleLessonDetail } from "../../utils/InitData";
import { Question } from "../../types/apimodels";
import GiveAudioChooseWordView from "./questions/GiveAudioChooseWordView";
import CheckButton from "./CheckButton";
import CorrectBottomSheet from "./bottomsheet/CorrectBottomSheet";
import IncorrectBottomSheet from "./bottomsheet/IncorrectBottomSheet";
import ProgressBar from "../common/ProgressBar";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/Colors";
import { useAppNavigation } from "../../hook/AppNavigationHooks";

export type QuestionResult = {
  enabled: boolean;
  isCorrect: boolean;
  message: string;
};

export const LearnScreenContext = createContext(
  {} as React.MutableRefObject<QuestionResult>
);

const LearnScreen = () => {
  const lesson = sampleLessonDetail();
  const questions = lesson.questions;

  const navigator = useAppNavigation();

  const [currentIndex, setCurrentIndex] = useState(0);
  const [progress, setProgress] = useState(0);
  const [isCompleted, setIsCompleted] = useState(false);
  const [dialogState, setDialogState] = useState<boolean>();

  const resultRef = useRef<QuestionResult>({} as QuestionResult);

  useEffect(() => {
    resultRef.current = {
      enabled: false,
      isCorrect: false,
      message: "",
    };
    setDialogState(undefined);
    setProgress((currentIndex + 1) / questions.length);
    setIsCompleted(currentIndex >= questions.length);
  }, [currentIndex]);

  const getQuestionViewByType = (question: Question) => {
    if (!question) {
      return null;
    }
    switch (question.type) {
      case QuestionType.GIVE_AUDIO_CHOOSE_WORD:
        if (!question.correctOption || !question.incorrectOptions) {
          return null;
        }
        return (
          <GiveAudioChooseWordView
            correctOption={question.correctOption}
            incorrectOptions={question.incorrectOptions}
          />
        );
      case QuestionType.GIVE_MEAN_CHOOSE_WORD:
        if (!question.correctOption || !question.incorrectOptions) {
          return null;
        }
        return (
          <GiveMeanChooseWordView
            correctOption={question.correctOption}
            incorrectOptions={question.incorrectOptions}
          />
        );
      case QuestionType.GIVE_WORDS_REARRANGE_MEANS:
        if (
          !question.mainSentense ||
          !question.unrelatedWords ||
          !question.mainSentenseAudio
        ) {
          return null;
        }
        return (
          <GiveWordsRearrangeMeansView
            mainSentense={question.mainSentense}
            mainSentenseAudio={question.mainSentenseAudio}
            unrelatedWords={question.unrelatedWords}
          />
        );
      case QuestionType.MATCH_WORDS_TO_MEANS:
        return <MatchWordsToMeansView />;
    }
  };

  const onCheckPress = () => {
    const ref = resultRef.current;
    if (!ref.enabled) {
      return;
    }
    setDialogState(ref.isCorrect);
  };

  function nextQuestion() {
    if (!isCompleted) {
      setDialogState(false);
      setCurrentIndex(currentIndex + 1);
    } else {
      navigator.navigate("LearnNavigator", {screen: "LearnScreen", ...lesson });
    }
  }

  return (
    <View style={styles.container}>
      <View style={styles.headingContainer}>
        <Ionicons
          name="close-outline"
          size={30}
          color={AppColors.blue}
        />
        <ProgressBar progress={progress} containerStyle={styles.progressBarContainer}/>
      </View>

      <Text style={styles.questionTitle}>{questions[currentIndex]?.type}</Text>

      <View style={styles.questionContainer}>
        <LearnScreenContext.Provider value={resultRef}>
          {getQuestionViewByType(questions[currentIndex])}
        </LearnScreenContext.Provider>
      </View>

      {dialogState === undefined ? (
        <CheckButton onCheckPress={onCheckPress} />
      ) : dialogState ? (
        <CorrectBottomSheet
          onContinuePress={nextQuestion}
          message={resultRef.current.message}
        />
      ) : (
        <IncorrectBottomSheet
          messageReason={resultRef.current.message}
          onSkipPress={nextQuestion}
        />
      )}
    </View>
  );
};

export default LearnScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  headingContainer: {
    flexDirection: "row",
    alignItems: "center",
  },
  progressBarContainer: {
    width: "90%",
    paddingHorizontal: 10,
    height: 20,
    padding: 3,
    justifyContent: "flex-end",
  },
  questionTitle: {
    fontSize: 20,
    padding: 20,
    fontWeight: "bold",
    textAlign: "center",
  },
  questionContainer: {
    flex: 1,
  },
});
