import { Pressable, StyleSheet, Text, View } from "react-native";
import React, { useEffect, useRef, useState } from "react";
import { QuestionType } from "../../types/enum";
import GiveMeanChooseWordView from "./questions/GiveMeanChooseWordView";
import GiveWordsRearrangeMeansView from "./questions/GiveSentenseRearrangWordsView";
import { LessonDetail, Question } from "../../types/apimodels";
import GiveAudioChooseWordView from "./questions/GiveAudioChooseWordView";
import CheckButton from "./CheckButton";
import CorrectBottomSheet from "./bottomsheet/CorrectBottomSheet";
import IncorrectBottomSheet from "./bottomsheet/IncorrectBottomSheet";
import ProgressBar from "../common/ProgressBar";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import { QuestionResult, LearnScreenContext } from "./LearnScreenHooks";
import GiveAudioRearrangeWords from "./questions/GiveAudioRearrangeWords";
import { useAppNavigation, useRootParams } from "../../navigation/AppNavigation";

const renderQuestionView = (question: Question) => {
  switch (question.type) {
    case "GIVE_AUDIO_CHOOSE_WORD":
      return (
        <GiveAudioChooseWordView
          option={question.option!}
          unrelatedOptions={question.unrelatedOptions!}
        />
      );
    case "GIVE_MEAN_CHOOSE_WORD":
      return (
        <GiveMeanChooseWordView
          option={question.option!}
          unrelatedOptions={question.unrelatedOptions!}
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
  const lesson : LessonDetail = JSON.parse(jsonLesson);

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
          lessonId: lesson.id,
          accuracy: Number(accuracy.toFixed(2)),
        },
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

      <Text style={styles.questionTitle}>
        {QuestionType[lesson.questions[currentIndex].type]}
      </Text>

      <View style={styles.questionContainer}>
        <LearnScreenContext.Provider value={resultRef}>
          {renderQuestionView(lesson.questions[currentIndex])}
        </LearnScreenContext.Provider>
      </View>

      {isBottomSheetVisible && (
        <View
          style={styles.overlay}
        />
      )}

      {isCorrect === undefined ? (
        <CheckButton onCheckPress={onCheckPress} />
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
  overlay: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    // Đảm bảo nằm trên các thành phần khác
    zIndex: 1, 
  },
});
