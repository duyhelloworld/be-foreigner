import React, { useState } from "react";
import { ApiResponse, LessonDetail } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";
import SplashScreen from "../common/SplashScreen";
import {
  useAppNavigation,
  useRootParams,
} from "../../navigation/AppNavigation";
import { Alert, Image } from "react-native";
import { AxiosError, isAxiosError } from "axios";
import SoundPlayer from "react-native-sound-player";

const SplashLearnScreen = () => {
  const { lessonId, lessonHistoryId } = useRootParams(
    "LearnNavigator",
    "SplashLearnScreen"
  );
  const navigator = useAppNavigation();
  let lessonDetail: LessonDetail | undefined;

  async function preload(lesson: LessonDetail) {
    if (!lesson.questions || lesson.questions.length === 0) {
      return;
    }
    lesson.questions.sort((q1, q2) => q1.index - q2.index);

    const preloadPromises = lesson.questions.map(async (question) => {
      if (question.correctOptionAudio) {
        SoundPlayer.loadUrl(question.correctOptionAudio!);
      }
      if (question.sentenseAudio) {
        SoundPlayer.loadUrl(question.sentenseAudio!);
      }
      if (question.answerOptions) {
        question.answerOptions?.map(async (answer) => {
          SoundPlayer.loadUrl(answer.audio);
          await Image.prefetch(answer.image);
        });
      }
    });
    await Promise.all(preloadPromises);
    console.log("All preloading lesson finished!");
    return lesson;
  }

  const handleTask = async () => {
    try {
      let path = lessonHistoryId
        ? `lesson/exam/history/${lessonHistoryId}`
        : `lesson/exam/${lessonId}` ?? `lesson/exam/1`;
      const response = await apiClient.get<ApiResponse>(path);
      if (response.data.code === ApiResponseCode.OK) {
        let responseLesson = response.data.data as LessonDetail;
        if (
          !responseLesson.questions ||
          responseLesson.questions.length === 0
        ) {
          Alert.alert(
            "ĐÂY LÀ CẢNH BÁO TRONG MÔI TRƯỜNG PHÁT TRIỂN",
            "Bài học '" + responseLesson.name + "' chưa được khởi tạo",
            [
              {
                text: "OK",
                onPress: () =>
                  navigator.navigate("HomeNavigator", { screen: "HomeScreen" }),
              },
            ]
          );
          return;
        }
        lessonDetail = await preload(responseLesson);
      } else {
        alert(response.data.data as string[]);
      }
    } catch (error) {
      console.error("Error when loading lesson: ", error);
      if (isAxiosError(error) && error.code === AxiosError.ETIMEDOUT) {
        Alert.alert(
          "Timeout!!!",
          "Lỗi khi tải tài nguyên bài học. Hãy kiểm tra lại kết nối mạng của bạn"
        );
      } else {
        Alert.alert(
          "🥹 Rất tiếc!",
          "Đã có lỗi xảy ra khi tải bài học. Chân thành xin lỗi bạn 🙏🙏🙏",
          [
            {
              text: "OK",
              onPress: () =>
                navigator.navigate("HomeNavigator", { screen: "HomeScreen" }),
            },
          ]
        );
      }
    }
  };

  const handleFinish = () => {
    if (lessonDetail) {
      navigator.navigate("LearnNavigator", {
        screen: "LearnScreen",
        params: { lesson: lessonDetail },
      });
    }
  };

  return (
    <SplashScreen
      onTask={handleTask}
      onFinish={handleFinish}
      label="Đang chuẩn bị bài học....."
      sublabel="Mỗi ngày hãy dành vài phút học bài nhé"
    />
  );
};

export default SplashLearnScreen;
