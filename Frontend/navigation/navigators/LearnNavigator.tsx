import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import LearnScreen from "../../components/learn/LearnScreen";
import CompletedLessonScreen from "../../components/learn/completed/CompletedLessonScreen";
import StreakScreen from "../../components/learn/completed/StreakScreen";
import SplashLearnScreen from "../../components/learn/SplashLearnScreen";
import { LessonDetail } from "../../types/apimodels";

export type LearnNavigatorParams = {
  SplashLearnScreen: { lessonId: number };
  LearnScreen: {lesson: LessonDetail };
  CompletedLessonScreen: {
    historyId: number;
    accuracy: number;
  };
  StreakScreen: {streakDay: number};
};

const LearnNavigator = () => {
  const Stack = createNativeStackNavigator<LearnNavigatorParams>();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="SplashLearnScreen" component={SplashLearnScreen} />
      <Stack.Screen name="LearnScreen" component={LearnScreen} />
      <Stack.Screen
        name="CompletedLessonScreen"
        component={CompletedLessonScreen}
      />
      <Stack.Screen 
        name="StreakScreen"
        component={StreakScreen}
      />
    </Stack.Navigator>
  );
};

export default LearnNavigator;