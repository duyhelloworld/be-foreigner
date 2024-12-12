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
import useAuthStorage from "../hook/AuthStorageHooks";
import useFirstTry from "../hook/FirstTryHooks";
import {
  NavigationProp,
  RouteProp,
  useNavigation,
  useRoute,
} from "@react-navigation/native";
import FirstTryNavigator, {
  FirstTryNavigatorParams,
} from "./navigators/FirstTryNavigator";
import SplashScreen from "../components/common/SplashScreen";
import { Linking } from "react-native";
import { useNotificationStorage } from "../hook/NotificationStorageHook";
import apiClient from "../config/AxiosConfig";
import { ApiResponse, Notification } from "../types/apimodels";
import { ApiResponseCode } from "../types/enum";

export type RootNavigatorParams = {
  HomeNavigator: NavigatorScreenParams<HomeNavigatorParams>;
  FirstTryNavigator: NavigatorScreenParams<FirstTryNavigatorParams>;
  AuthNavigator: NavigatorScreenParams<AuthNavigatorParams>;
  LearnNavigator: NavigatorScreenParams<LearnNavigatorParams>;
  ProfileNavigator: NavigatorScreenParams<ProfileNavigatorParams>;
};

export const useAppNavigation = () => {
  const navigation = useNavigation<NavigationProp<RootNavigatorParams>>();
  return navigation;
};

export function useRootParams<
  N extends keyof RootParams,
  S extends keyof RootParams[N]
>(navigator: N, screen: S) {
  const route = useRoute<RouteProp<RootParams[N], S>>();
  if (!route.params) {
    throw new Error("Unable access undefined params!");
  }
  return route.params!;
}

export type RootParams = {
  AuthNavigator: AuthNavigatorParams;
  FirstTryNavigator: FirstTryNavigatorParams;
  LearnNavigator: LearnNavigatorParams;
  ProfileNavigator: ProfileNavigatorParams;
};

export default function AppNavigation() {
  const authStorage = useAuthStorage();
  const firstTry = useFirstTry();
  const notificationStorage = useNotificationStorage();

  const Stack = createNativeStackNavigator<RootNavigatorParams>();

  const [isSignedIn, setIsSignedIn] = useState(false);
  const [isFirstTry, setIsFristTry] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  async function load() {
    setIsFristTry(await firstTry.isFirstTry());
    setIsSignedIn((await authStorage.getAccessToken()) !== undefined);
    const response = await apiClient.get<ApiResponse>("remind/sync");
    if (response.data.code === ApiResponseCode.OK) {
      await notificationStorage.addAll(response.data.data as Notification[]);
    }
  }

  if (isLoading) {
    return (
      <SplashScreen
        sublabel="Đang tìm kiếm chất xám cho bạn ngày hôm nay!"
        onTask={load}
        onFinish={() => setIsLoading(false)}
      />
    );
  }

  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName={
          isFirstTry
            ? "FirstTryNavigator"
            : isSignedIn
            ? "HomeNavigator"
            : "AuthNavigator"
        }
        screenOptions={{ headerShown: false }}
      >
        <Stack.Screen name="FirstTryNavigator" component={FirstTryNavigator} />
        <Stack.Screen name="AuthNavigator" component={AuthNavigator} />
        <Stack.Screen name="HomeNavigator" component={HomeNavigator} />
        <Stack.Screen name="LearnNavigator" component={LearnNavigator} />
        <Stack.Screen name="ProfileNavigator" component={ProfileNavigator} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
