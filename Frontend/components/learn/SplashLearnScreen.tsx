import React from "react";
import { ApiResponse, LessonDetail } from "../../types/apimodels";
import {
  useAppNavigation,
  useAppParams,
} from "../../navigation/AppNavigationHooks";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";
import SplashScreen from "../common/SplashScreen";

const SplashLearnScreen = () => {
  const { lessonId } = useAppParams("LearnNavigator", "SplashLearnScreen");
  const navigator = useAppNavigation();
  let lesson: LessonDetail | null = null; 

  const handleTask = async () => {
    const response = await apiClient.get<ApiResponse>(`lesson/exam/${lessonId}`);
    if (response.data.code === ApiResponseCode.OK) {
      lesson = response.data.data as LessonDetail;
    } 
  };

  const handleFinish = () => {
    if (lesson) {
      navigator.navigate("LearnNavigator", {
        screen: "LearnScreen",
        params: { jsonLesson: JSON.stringify(lesson) },
      });
    } 
  };

  return <SplashScreen onTask={handleTask} onFinish={handleFinish} totalTime={3000} />;
};

export default SplashLearnScreen;