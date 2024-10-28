import React from "react";
import {
  BottomTabNavigationOptions,
  createBottomTabNavigator,
} from "@react-navigation/bottom-tabs";
import HomeScreen from "../../components/home/HomeScreen";
import ProfileScreen from "../../components/profile/ProfileScreen";
import RankingScreen from "../../components/ranking/RankingScreen";
import TaskScreen from "../../components/task/TaskScreen";
import Tabbar from "../Tabbar";

export type HomeNavigatorParams = {
  HomeScreen: undefined;
  TaskScreen: undefined;
  RankingScreen: undefined;
  ProfileScreen: undefined;
};

const Tab = createBottomTabNavigator<HomeNavigatorParams>();

const HomeNavigator = () => {
  return (
    <Tab.Navigator
      tabBar={(props) => <Tabbar {...props} />}
      screenOptions={{
        headerShown: false,
      }}
    >
      <Tab.Screen name="HomeScreen" component={HomeScreen} />
      <Tab.Screen name="TaskScreen" component={TaskScreen} />
      <Tab.Screen name="RankingScreen" component={RankingScreen} />
      <Tab.Screen name="ProfileScreen" component={ProfileScreen} />
    </Tab.Navigator>
  );
};

export default HomeNavigator;
