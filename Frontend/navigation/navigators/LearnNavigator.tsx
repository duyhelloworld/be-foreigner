import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import LearnScreen from "../../components/learn/LearnScreen";
import CompletedLessonScreen from "../../components/learn/completed/CompletedLessonScreen";
import StraightScreen from "../../components/learn/completed/StraightScreen";
import SplashLearnScreen from "../../components/learn/SplashLearnScreen";

export type LearnNavigatorParams = {
  SplashLearnScreen: { lessonId: number };
  LearnScreen: {jsonLesson: string };
  CompletedLessonScreen: {
    lessonId: number;
    accuracy: number;
  };
  StraightScreen: undefined;
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
        name="StraightScreen"
        component={StraightScreen}
      />
    </Stack.Navigator>
  );
};

export default LearnNavigator;