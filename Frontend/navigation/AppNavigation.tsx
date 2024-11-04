import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import AuthNavigator, { AuthNavigatorParams } from "./navigators/AuthNavigator";
import HomeNavigator, { HomeNavigatorParams } from "./navigators/HomeNavigator";
import {
  NavigationContainer,
  NavigatorScreenParams,
} from "@react-navigation/native";
import LearnNavigator, {
  LearnNavigatorParams,
} from "./navigators/LearnNavigator";
import ProfileNavigator, {
  ProfileNavigatorParams,
} from "./navigators/ProfileNavigator";

export type RootNavigatorParams = {
  HomeNavigator: NavigatorScreenParams<HomeNavigatorParams>;

  AuthNavigator: NavigatorScreenParams<AuthNavigatorParams>;

  LearnNavigator: NavigatorScreenParams<LearnNavigatorParams>;

  ProfileNavigator: NavigatorScreenParams<ProfileNavigatorParams>;
};

export type AppParams = {
  RootNav: RootNavigatorParams;
  AuthNav: AuthNavigatorParams;
  LearnNav: LearnNavigatorParams;
  ProfileNav: ProfileNavigatorParams;
};

export default function AppNavigation() {
  const Stack = createNativeStackNavigator<RootNavigatorParams>();

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="AuthNavigator" component={AuthNavigator} />
        <Stack.Screen name="HomeNavigator" component={HomeNavigator} />
        <Stack.Screen name="LearnNavigator" component={LearnNavigator} />
        <Stack.Screen name="ProfileNavigator" component={ProfileNavigator} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
