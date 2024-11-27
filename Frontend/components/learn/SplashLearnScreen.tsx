import React, { useState } from "react";
import { ApiResponse, LessonDetail } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";
import SplashScreen from "../common/SplashScreen";
import {
  useAppNavigation,
  useRootParams,
} from "../../navigation/AppNavigation";

const SplashLearnScreen = () => {
  const { lessonId } = useRootParams("LearnNavigator", "SplashLearnScreen");
  const navigator = useAppNavigation();
  const [lesson, setLesson] = useState<LessonDetail | null>();
  const [error, setError] = useState<string>();

  const handleTask = async () => {
    const response = await apiClient.get<ApiResponse>(
      `lesson/exam/${lessonId}`
    );
    if (response.data.code === ApiResponseCode.OK) {
      console.log(response.data.data);
      setLesson(response.data.data as LessonDetail);
    } else {
      setError((response.data.data as string[]).join(" "));
    }
  };

  const handleFinish = () => {
    if (lesson) {
      navigator.navigate("LearnNavigator", {
        screen: "LearnScreen",
        params: { jsonLesson: JSON.stringify(lesson) },
      });
    }
    if (error) {
      alert(error);
      navigator.goBack();
    }
  };

  return (
    <SplashScreen
      onTask={handleTask}
      onFinish={handleFinish}
      totalTime={2000}
    />
  );
};

export default SplashLearnScreen;
