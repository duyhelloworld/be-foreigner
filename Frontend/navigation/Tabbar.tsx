import { BottomTabBarProps } from "@react-navigation/bottom-tabs";
import {
  View,
  StyleSheet,
  useAnimatedValue,
  LayoutChangeEvent,
  ViewStyle,
  Animated,
} from "react-native";
import TabbarIcon from "./TabbarIcon";
import { HomeNavigatorParams } from "./navigators/HomeNavigator";
import { AppColors } from "../types/colors";
import { useState } from "react";

export default function Tabbar({ state, navigation }: BottomTabBarProps) {
  const [dimensions, setDimensions] = useState({ width: 100, height: 20 });

  const tabPosX = useAnimatedValue(0);
  const buttonWidth = dimensions.width / state.routes.length;

  const onTabbarLayout = (e: LayoutChangeEvent) => {
    setDimensions({
      width: e.nativeEvent.layout.width,
      height: e.nativeEvent.layout.height,
    });
  };

  return (
    <View style={styles.container} onLayout={onTabbarLayout}>
      <Animated.View
        style={[
          styles.buttonPressed,
          {
            height: dimensions.height - 5,
            width: buttonWidth - 25,
            transform: [{ translateX: tabPosX }],
          },
        ]}
      />
      {state.routes.map((route, index) => {
        const isFocused = state.index === index;
        const onPress = () => {
          Animated.spring(tabPosX, {
            toValue: buttonWidth * index,
            useNativeDriver: false,
          }).start();
          const event = navigation.emit({
            type: "tabPress",
            target: route.key,
            canPreventDefault: true,
          });
          if (!isFocused && !event.defaultPrevented) {
            navigation.navigate(route.name, route.params);
          }
        };
        const onLongPress = () => {
          Animated.spring(tabPosX, {
            toValue: buttonWidth * index,
            useNativeDriver: false,
          }).start();

          navigation.emit({
            type: "tabLongPress",
            target: route.key,
          });
        };

        return (
          <TabbarIcon
            key={route.name}
            onPress={onPress}
            onLongPress={onLongPress}
            isFocused={isFocused}
            routeName={route.name as keyof HomeNavigatorParams}
          />
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    flexDirection: "row",
    bottom: 30,
    marginHorizontal: 30,
    justifyContent: "space-between",
    alignItems: "center",
    borderRadius: 20,
    borderWidth: 1,
    backgroundColor: AppColors.darkGreen,
    elevation: 10,
  },
  buttonPressed: {
    position: "absolute",
    backgroundColor: AppColors.light,
    borderRadius: 20,
    marginHorizontal: 12,
  },
});
