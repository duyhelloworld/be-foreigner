import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { Routes } from "../../types/global";
import { NavigationContainer } from "@react-navigation/native";
import HomeScreen from "../home/HomeScreen";
import LearnScreen from "../learn/LearnScreen";
import ProfileScreen from "../profile/Profile";
import OnBoardScreen from "../auth/OnBoardScreen";
import LoginScreen from "../auth/LoginScreen";
import { Ionicons } from "@expo/vector-icons";
import SearchScreen from "../search/SearchScreen";
import AppNavigationMainIcon from "./AppNavigationMainIcon";
import { randomTopicId } from "../../utils/InitData";
import MyTopicLibraryScreen from "../mylib/MyTopicLibraryScreen";

interface AppNavigationProp {
  isLogedIn: boolean;
  isFirstTime: boolean;
}

const AppNavigation = (prop: AppNavigationProp) => {
  const Tab = createBottomTabNavigator<Routes>();

  return (
    <NavigationContainer>
      {prop.isFirstTime ? (
        <OnBoardScreen />
      ) : prop.isLogedIn ? (
        <LoginScreen />
      ) : (
        <Tab.Navigator
          screenOptions={{
            headerShown: false,
            tabBarActiveTintColor: "#CC5",
          }}
        >
          <Tab.Screen
            name="Home"
            component={HomeScreen}
            options={{
              tabBarIcon: ({ color }) => <Ionicons name="home" color={color} />,
            }}
          />
          <Tab.Screen
            name="Search"
            component={SearchScreen}
            options={{
              tabBarIcon: ({ color }) => (
                <Ionicons name="search" color={color} />
              ),
            }}
          />
          <Tab.Screen
            name="Learn"
            component={LearnScreen}
            initialParams={{topicId: randomTopicId()}}
            options={{
              tabBarIcon: ({ focused }) => (
                <AppNavigationMainIcon focused={focused} />
              ),
            }}
          />
          <Tab.Screen
            name="MyTopicLibrary"
            component={MyTopicLibraryScreen}
            options={{
              tabBarIcon: ({ color }) => (
                <Ionicons name="library" color={color} />
              ),
            }}
          />
          <Tab.Screen
            name="Profile"
            component={ProfileScreen}
            options={{
              tabBarIcon: ({ color }) => (
                <Ionicons name="person" color={color} />
              ),
            }}
          />
        </Tab.Navigator>
      )}
    </NavigationContainer>
  );
};

export default AppNavigation;

const styles = StyleSheet.create({});
