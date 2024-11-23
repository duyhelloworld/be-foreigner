import React, { useEffect } from "react";
import {
  View,
  Text,
  StyleSheet,
  Animated,
  useAnimatedValue,
  Image,
  Easing,
  Dimensions,
} from "react-native";
import BottomButton from "./BottomButton";
import { AppColors } from "../../../types/colors";
import fireImage from "../../../assets/fire.png";
import { useAppNavigation } from "../../../navigation/AppNavigationHooks";
import { LinearGradient } from "expo-linear-gradient";

interface StreakAnimationProps {
  streakDays: number;
}

export default function StreakAnimation({ streakDays }: StreakAnimationProps) {
  const streakOpacity = useAnimatedValue(0);
  const streakPosition = useAnimatedValue(0);
  const congratsOpacity = useAnimatedValue(0);
  const navigator = useAppNavigation();

  streakDays = 10;

  useEffect(() => {
    Animated.sequence([
      Animated.timing(streakOpacity, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: true,
      }),
      Animated.timing(streakPosition, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
        easing: Easing.out(Easing.cubic),
      }),
      Animated.timing(congratsOpacity, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
      }),
    ]).start();
  }, []);

  function onNextPress() {
    navigator.navigate("HomeNavigator", { screen: "HomeScreen" });
  }

  const yPosition = streakPosition.interpolate({
    inputRange: [0, 1],
    outputRange: [0, -Dimensions.get("window").height * 0.2],
  });

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.streakContainer,
          {
            opacity: streakOpacity,
            transform: [{ translateY: yPosition }],
          },
        ]}
      >
        <LinearGradient
          colors={[AppColors.yellow, AppColors.blue]}
          style={styles.aura}
        />
        <Text style={styles.streakNumber}>{streakDays}</Text>
      </Animated.View>
      <Animated.Text
        style={[styles.congratsHightlightText, { opacity: congratsOpacity }]}
      >
        Chúc mừng
      </Animated.Text>
      <Animated.Text
        style={[styles.congratsText, { opacity: congratsOpacity }]}
      >
        {" "}
        chuỗi combo của bạn
      </Animated.Text>
      <BottomButton onPress={onNextPress} title="Tiếp tục" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: AppColors.blue,
  },
  streakContainer: {
    alignItems: "center",
    justifyContent: "center",
  },
  aura: {
    position: "absolute",
    width: 200,
    height: 200,
    borderRadius: 100,
  },
  streakNumber: {
    fontSize: 80,
    fontWeight: "bold",
    color: AppColors.yellow,
    textShadowColor: AppColors.black,
    textShadowOffset: { width: 2, height: 2 },
    textShadowRadius: 4,
  },
  congratsHightlightText: {
    fontSize: 35,
    fontWeight: "bold",
    color: AppColors.orange,
    textShadowColor: AppColors.red,
    textShadowOffset: { width: 2, height: 2 },
    textShadowRadius: 4,
    textAlign: "center",
  },
  congratsText: {
    fontSize: 25,
    fontWeight: "bold",
    color: AppColors.white,
    textAlign: "center",
  },
  continueButton: {
    position: "absolute",
    bottom: 40,
    backgroundColor: AppColors.yellow,
    paddingVertical: 15,
    paddingHorizontal: 30,
    borderRadius: 25,
  },
  continueButtonText: {
    color: AppColors.green,
    fontSize: 18,
    fontWeight: "bold",
  },
});
