import React, { useEffect, useState } from "react";
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
import useAuthStorage from "../storage/AuthStorageHooks";

export type RootNavigatorParams = {
  HomeNavigator: NavigatorScreenParams<HomeNavigatorParams>;

  AuthNavigator: NavigatorScreenParams<AuthNavigatorParams>;

  LearnNavigator: NavigatorScreenParams<LearnNavigatorParams>;

  ProfileNavigator: NavigatorScreenParams<ProfileNavigatorParams>;
};

export type AppParams = {
  AuthNavigator: AuthNavigatorParams;
  LearnNavigator: LearnNavigatorParams;
  ProfileNavigator: ProfileNavigatorParams;
};

export default function AppNavigation() {
  const Stack = createNativeStackNavigator<RootNavigatorParams>();
  const [isSignedIn, setIsSignedIn] = useState<boolean>();

  const authStorage = useAuthStorage();

  useEffect(() => {
    async function checkSignedInAsync() {
      setIsSignedIn(!await authStorage.getAccessToken());
    }
    checkSignedInAsync();
  }, [isSignedIn]);

  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName={isSignedIn ? "HomeNavigator" : "AuthNavigator"}
        screenOptions={{ headerShown: false }}
      >
        <Stack.Screen name="AuthNavigator" component={AuthNavigator} />
        <Stack.Screen name="HomeNavigator" component={HomeNavigator} />
        <Stack.Screen name="LearnNavigator" component={LearnNavigator} />
        <Stack.Screen name="ProfileNavigator" component={ProfileNavigator} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
