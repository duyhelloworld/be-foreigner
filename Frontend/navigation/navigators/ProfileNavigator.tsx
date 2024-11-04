import { RouteProp, useRoute } from "@react-navigation/native";
import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import UpdatePasswordScreen from "../../components/profile/UpdateProfileScreen";
import ForgotPasswordScreen from "../../components/profile/ForgotPasswordScreen";
import NotificationSetttingScreen from "../../components/profile/NotificationSetttingScreen";
import LogoutScreen from "../../components/profile/LogoutScreen";
import UserDetailScreen from "../../components/ranking/UserDetailScreen";
import UpgradePlanScreen from "../../components/profile/UpgradePlanScreen";

export type ProfileNavigatorParams = {
  UpdateProfileScreen: undefined;
  ForgotPasswordScreen: undefined;
  NotificationSetttingScreen: undefined;
  LogoutScreen: undefined;
  UserDetailScreen: { username?: string } | undefined;
  UpgradePlanScreen: undefined;
};



const ProfileNavigator = () => {
  const Stack = createNativeStackNavigator<ProfileNavigatorParams>();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen
        name="UpdateProfileScreen"
        component={UpdatePasswordScreen}
      />
      <Stack.Screen
        name="ForgotPasswordScreen"
        component={ForgotPasswordScreen}
      />
      <Stack.Screen
        name="NotificationSetttingScreen"
        component={NotificationSetttingScreen}
      />
      <Stack.Screen name="LogoutScreen" component={LogoutScreen} />
      <Stack.Screen name="UserDetailScreen" component={UserDetailScreen} />
      <Stack.Screen name="UpgradePlanScreen" component={UpgradePlanScreen} />
    </Stack.Navigator>
  );
};

export default ProfileNavigator;
