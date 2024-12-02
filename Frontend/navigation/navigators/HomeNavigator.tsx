import React from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import HomeScreen from "../../components/home/HomeScreen";
import ProfileScreen from "../../components/profile/ProfileScreen";
import RankingScreen from "../../components/ranking/RankingScreen";
import NotificationScreen from "../../components/notification/NotificationScreen";
import { Feather, Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";

const ICON_SIZE = 23;
const ICON_COLOR = AppColors.darkGreen;
const ICON_COLOR_FOCUSED = AppColors.green;

export type HomeNavigatorParams = {
  HomeScreen: undefined;
  NotificationScreen: undefined;
  RankingScreen: undefined;
  ProfileScreen: undefined;
};

function getTabbarIcon(screen: keyof HomeNavigatorParams, isFocused: boolean ) {
  switch (screen) {
    case "HomeScreen":
      return (
        <Feather
          name="home"
          size={ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
    case "RankingScreen":
      return (
        <Ionicons
          name="stats-chart-outline"
          size={ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
    case "ProfileScreen":
      return (
        <Feather
          name="user"
          size={ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
    case "NotificationScreen":
      return (
        <Ionicons
          name="notifications"
          size={ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
  }
};

const Tab = createBottomTabNavigator<HomeNavigatorParams>();

const HomeNavigator = () => {

  return (
    <Tab.Navigator
      backBehavior="none"
      screenOptions={{
        headerShown: false,
        tabBarShowLabel: false,
      }}
    >
      <Tab.Screen
        name="HomeScreen"
        component={HomeScreen}
        options={{
          tabBarIcon: (prop) => getTabbarIcon("HomeScreen", prop.focused),
        }}
      />
       <Tab.Screen
        name="RankingScreen"
        component={RankingScreen}
        options={{
          tabBarIcon: (prop) => getTabbarIcon("RankingScreen", prop.focused),
        }}
      />
      <Tab.Screen
        name="NotificationScreen"
        component={NotificationScreen}
        options={{
          tabBarIcon: (prop) => getTabbarIcon("NotificationScreen", prop.focused),
        }}
      />
      <Tab.Screen
        name="ProfileScreen"
        component={ProfileScreen}
        options={{
          tabBarStyle: {display: "none"},
          tabBarIcon: (prop) => getTabbarIcon("ProfileScreen", prop.focused),
        }}
      />
    </Tab.Navigator>
  );
};

export default HomeNavigator;
