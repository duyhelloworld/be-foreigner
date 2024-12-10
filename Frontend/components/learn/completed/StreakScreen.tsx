import React, { useState, useEffect, useRef } from "react";
import { Text, StyleSheet, Animated, Easing, Image, useAnimatedValue, Pressable } from "react-native";
import { AppColors } from "../../../types/colors";
import GradientBackground from "../../common/GradientBackground";
import { useAppNavigation, useRootParams } from "../../../navigation/AppNavigation";

const ScreakScreen = () => {
  const [showNextDay, setShowNextDay] = useState(false);
  const [showCongrats, setShowCongrats] = useState(false);
  const streak = useRootParams("LearnNavigator", "StreakScreen").streakDay;

  const fadeAnim = useAnimatedValue(1);
  const moveAnim = useAnimatedValue(0);
  const congratsFadeAnim = useAnimatedValue(0);

  const navigator = useAppNavigation();

  useEffect(() => {
    const nextDayTimer = setTimeout(() => {
      Animated.parallel([
        Animated.timing(fadeAnim, {
          toValue: 0,
          duration: 200,
          useNativeDriver: false,
        }),
      ]).start(() => {
        setShowNextDay(true);
        Animated.parallel([
          Animated.timing(fadeAnim, {
            toValue: 1,
            duration: 500,
            useNativeDriver: false,
          }),
          Animated.timing(moveAnim, {
            toValue: 1,
            duration: 1500,
            useNativeDriver: false,
          }),
        ]).start();
      });
    }, 1000);

    const congratsTimer = setTimeout(() => {
      setShowCongrats(true);
      Animated.timing(congratsFadeAnim, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: false,
      }).start();
    }, 4000);

    return () => {
      clearTimeout(nextDayTimer);
      clearTimeout(congratsTimer);
    };
  }, [streak]);

  const nextDayStyle = {
    opacity: fadeAnim,
    transform: [
      {
        translateY: moveAnim.interpolate({
          inputRange: [0, 1],
          outputRange: [0, -50],
        }),
      },
    ],
  };

  const congratsContainerStyle = {
    opacity: congratsFadeAnim,
  };

  const flameIconStyle = {
    transform: [
      {
        scale: congratsFadeAnim.interpolate({
          inputRange: [0, 1],
          outputRange: [0.5, 1],
        }),
      },
    ],
  };

  const congratsTitleStyle = {
    transform: [
      {
        translateY: congratsFadeAnim.interpolate({
          inputRange: [0, 1],
          outputRange: [20, 0],
        }),
      },
    ],
  };

  const congratsTextStyle = {
    transform: [
      {
        translateY: congratsFadeAnim.interpolate({
          inputRange: [0, 1],
          outputRange: [20, 0],
        }),
      },
    ],
  };

  const buttonStyle = {
    opacity: congratsFadeAnim,
    transform: [
      {
        translateY: congratsFadeAnim.interpolate({
          inputRange: [0, 1],
          outputRange: [50, 0],
        }),
      },
    ],
  };

  const onNext = () => {
    navigator.navigate("HomeNavigator", {screen: "HomeScreen"});
  }

  return (
    <GradientBackground style={styles.container}>
      <Animated.Text style={[styles.dayText, nextDayStyle]}>
        {showNextDay ? streak : streak - 1}
      </Animated.Text>

      {showCongrats && (
        <Animated.View style={[styles.congratsContainer, congratsContainerStyle]}>
          <Animated.Image
            source={require("../../../assets/fire.png")}
            style={[styles.flameIcon, flameIconStyle]}
          />
          <Animated.Text style={[styles.congratsTitle, congratsTitleStyle]}>
            Chúc mừng!
          </Animated.Text>
          <Animated.Text style={[styles.congratsText, congratsTextStyle]}>
            Bạn đã đạt chuỗi {streak} ngày học liên tiếp!
          </Animated.Text>
        </Animated.View>
      )}

      <Animated.View style={[styles.button, buttonStyle]}>
        <Pressable onPress={onNext}>
          <Text style={styles.buttonText}>Tiếp tục</Text>
        </Pressable>
      </Animated.View>
    </GradientBackground>
  );
};

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
  },
  dayText: {
    fontSize: 120,
    fontWeight: "bold",
    color: "white",
  },
  congratsContainer: {
    alignItems: "center",
    marginTop: 20,
  },
  flameIcon: {
    width: 96,
    height: 96,
    marginBottom: 16,
  },
  congratsTitle: {
    fontSize: 32,
    fontWeight: "bold",
    color: "white",
    marginBottom: 8,
  },
  congratsText: {
    fontSize: 18,
    color: "white",
  },
  button: {
    position: "absolute",
    bottom: 20,
    width: "80%",
    backgroundColor: AppColors.darkGreen,
    borderRadius: 8,
    paddingVertical: 14,
    marginVertical: 5,
    alignItems: "center",
  },
  buttonText: {
    color: AppColors.white,
    fontSize: 16,
  },
});

export default ScreakScreen;