import { Alert, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useEffect, useRef, useState } from "react";
import { QuestionType } from "../../types/enum";
import GiveMeanChooseWordView from "./questions/GiveMeanChooseWordView";
import GiveWordsRearrangeMeansView from "./questions/GiveSentenseRearrangWordsView";
import { LessonDetail, Question } from "../../types/apimodels";
import GiveAudioChooseWordView from "./questions/GiveAudioChooseWordView";
import CorrectBottomSheet from "./bottomsheet/CorrectBottomSheet";
import IncorrectBottomSheet from "./bottomsheet/IncorrectBottomSheet";
import ProgressBar from "../common/ProgressBar";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import { QuestionResult, LearnScreenContext } from "./LearnScreenHooks";
import GiveAudioRearrangeWords from "./questions/GiveAudioRearrangeWords";
import {
  useAppNavigation,
  useRootParams,
} from "../../navigation/AppNavigation";
import BottomButton from "../common/BottomButton";
import LearnWordByValue from "./questions/LearnWordByValue";
import LearnWordByAudio from "./questions/LearnWordByAudio";

const renderQuestionView = (question: Question) => {
  switch (question.type) {
    case "LEARN_BY_AUDIO":
      return <LearnWordByAudio
        audio={question.correctOptionAudio!}
        mean={question.correctOptionMean!}
        value={question.correctOptionValue!}
      />
    case "LEARN_BY_WORD":
      return <LearnWordByValue
        mean={question.correctOptionMean!}
        value={question.correctOptionValue!}
      />
    case "GIVE_AUDIO_CHOOSE_WORD":
      return (
        <GiveAudioChooseWordView
          answerOptions={question.answerOptions!}
          correctOptionMean={question.correctOptionMean!}
          correctOptionAudio={question.correctOptionAudio!}
        />
      );
    case "GIVE_MEAN_CHOOSE_WORD":
      return (
        <GiveMeanChooseWordView
          answerOptions={question.answerOptions!}
          correctOptionMean={question.correctOptionMean!}
          correctOptionAudio={question.correctOptionAudio!}
          level={question.level}
        />
      );
    case "GIVE_SENTENSE_REARRANGE_WORDS":
      return (
        <GiveWordsRearrangeMeansView
          sentenseMeaning={question.sentenseMeaning!}
          sentenseWords={question.sentenseWords!}
          unrelatedWords={question.unrelatedWords}
        />
      );
    case "GIVE_AUDIO_REARRANGE_WORDS":
      return (
        <GiveAudioRearrangeWords
          sentenseAudio={question.sentenseAudio!}
          sentenseWords={question.sentenseWords!}
        />
      );
  }
};

const LearnScreen = () => {
  const { jsonLesson } = useRootParams("LearnNavigator", "LearnScreen");
  const lesson: LessonDetail = JSON.parse(jsonLesson);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [progress, setProgress] = useState(0);
  const [isCorrect, setIsCorrect] = useState<boolean>();
  const [accuracy, setAccuracy] = useState(100);
  const [isBottomSheetVisible, setBottomSheetVisible] = useState(false);

  const navigator = useAppNavigation();
  const resultRef = useRef<QuestionResult>({} as QuestionResult);

  useEffect(() => {
    resultRef.current = {
      enabled: false,
      isCorrect: false,
      message: "",
    };
    setIsCorrect(undefined);
    setProgress((currentIndex + 1) / lesson.questions.length);
  }, [currentIndex]);

  function onCheckPress() {
    const ref = resultRef.current;
    if (!ref.enabled) {
      return;
    }
    setIsCorrect(ref.isCorrect);
    setBottomSheetVisible(true);
    if (!ref.isCorrect) {
      setAccuracy(accuracy - accuracy / lesson.questions.length);
    }
  }

  function nextQuestion() {
    if (currentIndex + 1 < lesson.questions.length) {
      setCurrentIndex(currentIndex + 1);
      setBottomSheetVisible(false);
    } else {
      // Nếu index vượt => completed
      navigator.navigate("LearnNavigator", {
        screen: "CompletedLessonScreen",
        params: {
          historyId: lesson.historyId,
          accuracy: Number(accuracy.toFixed(0)),
        },
      });
    }
  }

  function onExit() {
    Alert.prompt("Bạn có muốn thoát không?", "", [
      { text: "OK", onPress: () => 
        navigator.navigate("HomeNavigator", { screen: "HomeScreen" }) },
      { text: "Hủy" },
    ])
  }

  return (
    <View style={styles.container}>
      <View style={styles.headingContainer}>
        <Ionicons
          name="close-outline"
          size={35}
          style={styles.closeButton}
          color={AppColors.grayDark}
          onPress={onExit}
        />
        <ProgressBar
          progress={progress}
          containerStyle={styles.progressBarContainer}
        />
      </View>

      <Text style={styles.questionTitle}>
        {QuestionType[lesson.questions[currentIndex].type]}
      </Text>

      <View style={styles.questionContainer}>
        <LearnScreenContext.Provider value={resultRef}>
          {renderQuestionView(lesson.questions[currentIndex])}
        </LearnScreenContext.Provider>
      </View>

      {isBottomSheetVisible && <View style={styles.overlay} />}

      {isCorrect === undefined ? (
        <BottomButton title="Kiểm tra" onPress={onCheckPress} style={styles.bottomButton} />
      ) : isCorrect ? (
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
    marginHorizontal: 10,
    height: 20,
    justifyContent: "flex-end",
  },
  questionTitle: {
    fontSize: 20,
    padding: 20,
    fontWeight: "700",
    textAlign: "center",
  },
  questionContainer: {
    flex: 1,
  },
  overlay: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: AppColors.gray,
    opacity: 0.6,
    zIndex: 1,
  },
  bottomButton: {
    marginHorizontal: "10%"
  }
});
