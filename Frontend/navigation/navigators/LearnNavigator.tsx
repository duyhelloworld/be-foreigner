import { RouteProp, useRoute } from "@react-navigation/native";
import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import SplashLearnScreen from "../../components/learn/splash/SplashLearnScreen";
import LearnScreen from "../../components/learn/LearnScreen";
import CompletedLessonScreen from "../../components/learn/CompletedLessonScreen";

export type LearnNavigatorParams = {
  SplashLearnScreen: { lessonId: number };
  LearnScreen: undefined;
  CompletedLessonScreen: {
    name: string;
    diamonds: number;
    experiences: number;
  };
};

export function useLearnRoute<T extends keyof LearnNavigatorParams>(current: T): RouteProp<LearnNavigatorParams, T>["params"] {
  const route = useRoute<RouteProp<LearnNavigatorParams, T>>();
  return route.params;
}

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
    </Stack.Navigator>
  );
};

export default LearnNavigator;