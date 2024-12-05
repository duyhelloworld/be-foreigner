import React from "react";
import { ApiResponse, LessonDetail } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";
import SplashScreen from "../common/SplashScreen";
import {
  useAppNavigation,
  useRootParams,
} from "../../navigation/AppNavigation";
import { Sound } from "expo-av/build/Audio";

const SplashLearnScreen = () => {
  const { lessonId } = useRootParams("LearnNavigator", "SplashLearnScreen");
  const navigator = useAppNavigation();
  let lesson: LessonDetail | undefined;

  const handleTask = async () => {
    const response = await apiClient.get<ApiResponse>(
      `lesson/exam/${lessonId}`
    );
    if (response.data.code === ApiResponseCode.OK) {
      // const gotLesson = response.data.data as LessonDetail;
      // for (let question of gotLesson.questions) {
      //   if (
      //     question.correctOptionAudio &&
      //     typeof question.correctOptionAudio === "string"
      //   ) {
      //     question.correctOptionAudio = (
      //       await Sound.createAsync({ uri: question.correctOptionAudio })
      //     ).sound;
      //   }
      //   if (
      //     question.answerOptions &&
      //     question.type === "GIVE_AUDIO_CHOOSE_WORD"
      //   ) {
      //     question.answerOptions.map(
      //       async (a) =>
      //         (a.audio = (await Sound.createAsync({ uri: a.audio })).sound)
      //     );
      //   }
      // }
      lesson = response.data.data as LessonDetail;
    } else {
      alert(response.data.data as string[]);
    }
  };

  const handleFinish = () => {
    navigator.navigate("LearnNavigator", {
      screen: "LearnScreen",
      params: { jsonLesson: JSON.stringify(lesson) },
    });
  };

  return (
    <SplashScreen
      onTask={handleTask}
      onFinish={handleFinish}
      label="Đang chuẩn bị bài học....."
      sublabel="Mỗi ngày hãy dành vài phút học bài nhé"
      totalTime={2000}
    />
  );
};

export default SplashLearnScreen;
