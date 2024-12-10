import React, { useEffect, useState } from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import HomeScreen from "../../components/home/HomeScreen";
import ProfileScreen from "../../components/profile/ProfileScreen";
import NotificationScreen from "../../components/notification/NotificationScreen";
import { Feather, Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import LeaderBoardScreen from "../../components/leaderboard/LeaderBoardScreen";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";

const ICON_SIZE = 23;
const ICON_COLOR = AppColors.darkGreen;
const ICON_COLOR_FOCUSED = AppColors.green;

export type HomeNavigatorParams = {
  HomeScreen: undefined;
  NotificationScreen: undefined;
  LeaderBoardScreen: undefined;
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
    case "LeaderBoardScreen":
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

  const notificationStorage = useNotificationStorage();
  const [unreadCount, setUnreadCount] = useState(0);

  useEffect(() => {
    const fetchNotifications = async () => {
      const notifications = await notificationStorage.getNotifications();
      const unread = notifications.filter((notif) => !notif.isRead).length;
      setUnreadCount(unread);
    };
    fetchNotifications();
  }, [notificationStorage]);
  
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
        name="LeaderBoardScreen"
        component={LeaderBoardScreen}
        options={{
          tabBarIcon: (prop) => getTabbarIcon("LeaderBoardScreen", prop.focused),
        }}
      />
      <Tab.Screen
        name="NotificationScreen"
        component={NotificationScreen}
        options={{
          tabBarIcon: (prop) => getTabbarIcon("NotificationScreen", prop.focused),
          tabBarBadge: unreadCount > 0 ? unreadCount : undefined
        }}
      />
      <Tab.Screen
        name="ProfileScreen"
        component={ProfileScreen}
        options={{
          tabBarIcon: (prop) => getTabbarIcon("ProfileScreen", prop.focused),
        }}
      />
    </Tab.Navigator>
  );
};

export default HomeNavigator;
