import React, { useEffect, useState } from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import HomeScreen from "../../components/home/HomeScreen";
import ProfileScreen from "../../components/profile/ProfileScreen";
import NotificationScreen from "../../components/notification/NotificationScreen";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import LeaderBoardScreen from "../../components/leaderboard/LeaderBoardScreen";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";
import { Alert, BackHandler, Linking } from "react-native";
import { useAppNavigation } from "../AppNavigation";

const ICON_SIZE = 23;
const ICON_SIZE_FOCUSED = 30;
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
        <Ionicons
          name={isFocused ? "home-sharp" : "home-outline"}
          size={isFocused ? ICON_SIZE_FOCUSED : ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
    case "LeaderBoardScreen":
      return (
        <Ionicons
          name={isFocused ? "stats-chart" : "stats-chart-outline"}
          size={isFocused ? ICON_SIZE_FOCUSED : ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
    case "ProfileScreen":
      return (
        <Ionicons
          name={isFocused ? "person-sharp" : "person-outline"}
          size={isFocused ? ICON_SIZE_FOCUSED : ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
    case "NotificationScreen":
      return (
        <Ionicons
          name={isFocused ? "notifications-sharp" : "notifications-outline"}
          size={isFocused ? ICON_SIZE_FOCUSED : ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
  }
};

const Tab = createBottomTabNavigator<HomeNavigatorParams>();

const HomeNavigator = () => {

  const notificationStorage = useNotificationStorage();
  const navigator = useAppNavigation();
  const [unreadCount, setUnreadCount] = useState(0);

  useEffect(() => {
    const fetchNotifications = async () => {
      const notifications = await notificationStorage.getNotifications();
      const unread = notifications.filter((notif) => !notif.read).length;
      setUnreadCount(unread);
    };
    fetchNotifications();
  }, [notificationStorage]);
  
  useEffect(() => {
    const onBackPress = () => {
      Alert.alert(
        "Xác nhận",
        "Bạn có muốn thoát ứng dụng không?",
        [
          { text: "Hủy", style: "cancel" },
          { text: "Thoát", onPress: () => BackHandler.exitApp() },
        ],
        { cancelable: true }
      );
      return true; // Chặn hành vi back mặc định
    };

    BackHandler.addEventListener("hardwareBackPress", onBackPress);

    return () => BackHandler.removeEventListener("hardwareBackPress", onBackPress);
  }, []);

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
