import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import NotificationSetttingScreen from "../../components/profile/subscreen/NotificationSetttingScreen";
import LogoutScreen from "../../components/profile/subscreen/LogoutScreen";
import UpgradePlanScreen from "../../components/profile/subscreen/UpgradePlanScreen";
import ChangePasswordScreen from "../../components/profile/subscreen/ChangePasswordScreen";
import UpdateProfileScreen from "../../components/profile/subscreen/UpdateProfileScreen";
import LessonHistoryScreen from "../../components/profile/subscreen/LessonHistoryScreen";
import StatisticScreen from "../../components/profile/subscreen/StatisticScreen";

export type ProfileNavigatorParams = {
  UpdateProfileScreen: undefined;
  ChangePasswordScreen: undefined;
  NotificationSetttingScreen: undefined;
  LessonHistoryScreen: undefined;
  StatisticScreen: undefined;
  UpgradePlanScreen: undefined;
  LogoutScreen: undefined;
};

const ProfileNavigator = () => {
  const Stack = createNativeStackNavigator<ProfileNavigatorParams>();

  return (
    <Stack.Navigator>
      <Stack.Screen
        name="UpdateProfileScreen"
        component={UpdateProfileScreen}
        options={{ title: "Cập nhật thông tin" }}
      />
      <Stack.Screen
        name="ChangePasswordScreen"
        component={ChangePasswordScreen}
        options={{ title: "Đổi mật khẩu" }}
      />
      <Stack.Screen
        name="NotificationSetttingScreen"
        component={NotificationSetttingScreen}
        options={{ title: "Cài đặt thông báo" }}
      />
      <Stack.Screen
        name="UpgradePlanScreen"
        component={UpgradePlanScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="LogoutScreen"
        component={LogoutScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="LessonHistoryScreen"
        component={LessonHistoryScreen}
        options={{ title: "Lịch sử học bài" }}
      />
      <Stack.Screen
        name="StatisticScreen"
        component={StatisticScreen}
        options={{ title: "Thống kê bài học" }}
      />
    </Stack.Navigator>
  );
};

export default ProfileNavigator;
