import React from "react";
import {
  NavigationContainer,
  NavigatorScreenParams,
} from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import AuthNavigator, { AuthNavigatorParams } from "./navigators/AuthNavigator";
import LearnNavigator, {
  LearnNavigatorParams,
} from "./navigators/LearnNavigator";
import HomeNavigator, { HomeNavigatorParams } from "./navigators/HomeNavigator";
import ProfileNavigator, { ProfileNavigatorParams } from "./navigators/ProfileNavigator";

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
        <Stack.Screen name="HomeNavigator" component={HomeNavigator} />
        <Stack.Screen name="AuthNavigator" component={AuthNavigator} />
        <Stack.Screen name="LearnNavigator" component={LearnNavigator} />
        <Stack.Screen name="ProfileNavigator" component={ProfileNavigator} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
