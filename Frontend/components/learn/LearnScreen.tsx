import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useRef, useState } from "react";
import { QuestionType } from "../../types/enum";
import GiveMeanChooseWordView from "./questions/GiveMeanChooseWordView";
import GiveWordsRearrangeMeansView from "./questions/GiveWordsRearrangeMeansView";
import MatchWordsToMeansView from "./questions/MatchWordsToMeansView";
import { sampleLessonDetail } from "../../utils/InitData";
import { Question } from "../../types/apimodels";
import GiveAudioChooseWordView from "./questions/GiveAudioChooseWordView";
import CheckButton from "./CheckButton";
import CorrectBottomSheet from "./bottomsheet/CorrectBottomSheet";
import IncorrectBottomSheet from "./bottomsheet/IncorrectBottomSheet";
import ProgressBar from "../common/ProgressBar";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import { useAppNavigation } from "../../navigation/AppNavigationHooks";
import {
  QuestionResult,
  LearnScreenContext,
} from "./LearnScreenHooks";

const renderQuestionView = (question: Question) => {
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

const LearnScreen = () => {
  const lesson = sampleLessonDetail();
  const questions = lesson.questions;

  const navigator = useAppNavigation();

  const [currentIndex, setCurrentIndex] = useState(0);
  const [progress, setProgress] = useState(0);
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
  }, [currentIndex]);

  function onCheckPress() {
    const ref = resultRef.current;
    if (!ref.enabled) {
      return;
    }
    setDialogState(ref.isCorrect);
  }

  function nextQuestion() {
    if (currentIndex + 1 < questions.length) {
      setDialogState(false);
      setCurrentIndex(currentIndex + 1);
    } else {
      // Nếu index vượt => completed
      navigator.navigate("LearnNavigator", {
        screen: "CompletedLessonScreen",
        params: { ...lesson },
      });
    }
  }

  return (
    <View style={styles.container}>
      <View style={styles.headingContainer}>
        <Ionicons
          name="close-outline"
          size={35}
          style={styles.closeButton}
          color={AppColors.blue}
        />
        <ProgressBar
          progress={progress}
          containerStyle={styles.progressBarContainer}
        />
      </View>

      <Text style={styles.questionTitle}>{questions[currentIndex].type}</Text>

      <View style={styles.questionContainer}>
        <LearnScreenContext.Provider value={resultRef}>
          {renderQuestionView(questions[currentIndex])}
        </LearnScreenContext.Provider>
      </View>

      {dialogState === undefined ? (
        <CheckButton onCheckPress={onCheckPress} />
      ) : dialogState ? (
        <CorrectBottomSheet
          message={resultRef.current.message}
          onContinuePress={nextQuestion}
        />
      ) : (
        <IncorrectBottomSheet
          message={resultRef.current.message}
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
  closeButton: {
    padding: 10,
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
