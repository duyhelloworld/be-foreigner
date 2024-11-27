import React from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import HomeScreen from "../../components/home/HomeScreen";
import ProfileScreen from "../../components/profile/ProfileScreen";
import RankingScreen from "../../components/ranking/RankingScreen";
import GameScreen from "../../components/game/GameScreen";
import Tabbar from "../Tabbar";

export type HomeNavigatorParams = {
  HomeScreen: undefined;
  GameScreen: undefined;
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
      <Tab.Screen name="GameScreen" component={GameScreen} />
      <Tab.Screen name="RankingScreen" component={RankingScreen} />
      <Tab.Screen
        name="ProfileScreen"
        component={ProfileScreen}
        options={{ tabBarStyle: { display: "none" } }}
      />
    </Tab.Navigator>
  );
};

export default HomeNavigator;
