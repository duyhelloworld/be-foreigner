import {
  Animated,
  Pressable,
  StyleSheet,
  Text,
  View,
  ViewStyle,
  useAnimatedValue,
} from "react-native";
import React, { useEffect, useState } from "react";
import { HomeNavigatorParams } from "./navigators/HomeNavigator";
import { Feather, Ionicons } from "@expo/vector-icons";
import { AppColors } from "../types/colors";

interface TabbarIconProp {
  onPress: () => void;
  onLongPress: () => void;
  isFocused: boolean;
  routeName: keyof HomeNavigatorParams;
}

const ICON_SIZE = 23;
const ICON_COLOR = AppColors.white;
const ICON_COLOR_FOCUSED = AppColors.green;

const getIcon = (screen: keyof HomeNavigatorParams, isFocused: boolean) => {
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
    case "GameScreen":
      return (
        <Ionicons
          name="game-controller"
          size={ICON_SIZE}
          color={isFocused ? ICON_COLOR_FOCUSED : ICON_COLOR}
        />
      );
  }
};

const TabbarIcon = ({
  onLongPress,
  onPress,
  isFocused,
  routeName,
}: TabbarIconProp) => {
  const scale = useAnimatedValue(0);
  
  const animatedStyle = (): Animated.WithAnimatedObject<ViewStyle> => {
    return {
      transform: [
        {
          scale: scale.interpolate({
            inputRange: [0, 1],
            outputRange: [1, 1.2],
          }),
        },
      ],
      bottom: scale.interpolate({inputRange: [0, 1], outputRange: [0, 5]})
    };
  };

  useEffect(() => {
    Animated.spring(scale, {
      toValue: isFocused ? 1 : 0,
      useNativeDriver: false,
    }).start();
  }, [scale, isFocused]);

  return (
    <Pressable
      key={routeName}
      onPress={onPress}
      onLongPress={onLongPress}
      style={styles.iconContainer}
    >
      <Animated.View style={animatedStyle()}>
        {getIcon(routeName, isFocused)}
      </Animated.View>
    </Pressable>
  );
};

export default TabbarIcon;

const styles = StyleSheet.create({
  iconContainer: {
    flex: 1,
    alignItems: "center",
    padding: 12,
  },
});
