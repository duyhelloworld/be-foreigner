import React, { useEffect, useState } from "react";
import {
  View,
  StyleSheet,
  Animated,
  Easing,
  useAnimatedValue,
} from "react-native";
import {
  useAppNavigation,
  useRootParams,
} from "../../../navigation/AppNavigation";
import { AppColors } from "../../../types/colors";
import BottomButton from "../../common/BottomButton";

const StreakScreen = () => {
  const navigator = useAppNavigation();

  const [currentNumber, setCurrentNumber] = useState(0);
  const [showCongrats, setShowCongrats] = useState(false);
  const circleAnimation = useAnimatedValue(0);
  const numberAnimation = useAnimatedValue(0);
  const congratsAnimation = useAnimatedValue(0);
  const streakDay = useRootParams("LearnNavigator", "StreakScreen").streakDay;

  useEffect(() => {
    if (currentNumber < streakDay) {
      const timer = setTimeout(() => {
        setCurrentNumber((prev) => {
          const next = Math.min(prev + 1, streakDay);
          Animated.timing(circleAnimation, {
            toValue: next / streakDay,
            duration: 50,
            useNativeDriver: true,
            easing: Easing.linear,
          }).start();
          return next;
        });
      });
      return () => clearTimeout(timer);
    } else if (currentNumber === streakDay) {
      setTimeout(() => setShowCongrats(true), 1000);
    }
  }, [currentNumber, streakDay, circleAnimation]);

  useEffect(() => {
    if (showCongrats) {
      Animated.parallel([
        Animated.timing(numberAnimation, {
          toValue: 1,
          duration: 500,
          useNativeDriver: true,
        }),
        Animated.timing(congratsAnimation, {
          toValue: 1,
          duration: 500,
          delay: 300,
          useNativeDriver: true,
        }),
      ]).start();
    }
  }, [showCongrats, numberAnimation, congratsAnimation]);

  const circlePath = circleAnimation.interpolate({
    inputRange: [0, 1],
    outputRange: [0, Math.PI * 2 * 45],
  });

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.numberContainer,
          {
            transform: [
              {
                translateY: numberAnimation.interpolate({
                  inputRange: [0, 1],
                  outputRange: [0, -50],
                }),
              },
              {
                scale: numberAnimation.interpolate({
                  inputRange: [0, 1],
                  outputRange: [1, 0.8],
                }),
              },
            ],
          },
        ]}
      >
        <Animated.Text
          style={[
            styles.number,
            {
              opacity: numberAnimation.interpolate({
                inputRange: [0, 0.5, 1],
                outputRange: [1, 0, 1],
              }),
              transform: [
                {
                  scale: numberAnimation.interpolate({
                    inputRange: [0, 0.5, 1],
                    outputRange: [1, 0.5, 1],
                  }),
                },
              ],
            },
          ]}
        >
          {currentNumber}
        </Animated.Text>
      </Animated.View>
      <Animated.Text
        style={[
          styles.congratsText,
          {
            opacity: congratsAnimation,
            transform: [
              {
                translateY: congratsAnimation.interpolate({
                  inputRange: [0, 1],
                  outputRange: [20, 0],
                }),
              },
            ],
          },
        ]}
      >
        Chúc mừng bạn đã đạt được {streakDay} ngày streak!
      </Animated.Text>

      <BottomButton
        title="Tiếp tục"
        onPress={() =>
          navigator.navigate("HomeNavigator", { screen: "HomeScreen" })
        }
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: AppColors.green,
  },
  numberContainer: {
    position: "relative",
    width: 200,
    height: 200,
    justifyContent: "center",
    alignItems: "center",
  },
  number: {
    position: "absolute",
    fontSize: 64,
    fontWeight: "bold",
    color: "white",
  },
  congratsText: {
    marginTop: 20,
    fontSize: 18,
    fontWeight: "600",
    color: "white",
    textAlign: "center",
  },
});

export default StreakScreen;
