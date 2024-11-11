import React, { useEffect } from "react";
import {
  View,
  Text,
  StyleSheet,
  Animated,
  useAnimatedValue,
  ImageBackground,
} from "react-native";
import BottomButton from "./BottomButton";
import { AppColors } from "../../../types/colors";
import fireImage from "../../../assets/fire-transparent.png";
import { useAppNavigation } from "../../../navigation/AppNavigationHooks";

interface StreakAnimationProps {
  streakDays?: number;
  currentDay?: number;
}

export default function StreakAnimation({
  streakDays = 7,
  currentDay = 3,
}: StreakAnimationProps) {
  const progressAnim = useAnimatedValue(0);
  const titleOpacity = useAnimatedValue(0);
  const numberOpacity = useAnimatedValue(0);
  const progressOpacity = useAnimatedValue(0);
  
  const navigator = useAppNavigation();

  useEffect(() => {
    Animated.sequence([
      Animated.timing(titleOpacity, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: true,
      }),
      Animated.timing(numberOpacity, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
      }),
      Animated.timing(progressOpacity, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
      }),
      Animated.timing(progressAnim, {
        toValue: currentDay,
        duration: 1500,
        useNativeDriver: false,
      }),
    ]).start();
  }, []);

  const progressSteps = Array.from({ length: streakDays }, (_, i) => i + 1);

  return (
    <View style={styles.container}>
      <Animated.Text style={[styles.title, { opacity: titleOpacity }]}>
        Streak của bạn
      </Animated.Text>

      <View style={styles.animationContainer}>
        <View style={styles.progressContainer}>
          <Animated.View style={[styles.heading, { opacity: numberOpacity }]}>
            <ImageBackground source={fireImage} style={styles.fireImage}>
              <Text style={styles.streakNumber}>{streakDays}</Text>
            </ImageBackground>
          </Animated.View>
          <Animated.View style={[styles.progressBar, {opacity: progressOpacity}]}>
            {progressSteps.map((step) => (
              <Animated.View
                key={step}
                style={[
                  styles.progressStep,
                  {
                    backgroundColor: progressAnim.interpolate({
                      inputRange: [step - 1, step],
                      outputRange: [AppColors.light, AppColors.orange],
                      extrapolate: "clamp",
                    }),
                  },
                ]}
              />
            ))}
          </Animated.View>
          <Animated.View style={[styles.progressLabels, {opacity: progressOpacity}]}>
            <Text style={styles.progressLabel}>Ngày 1</Text>
            <Text style={styles.progressLabel}>Ngày {streakDays}</Text>
          </Animated.View>
        </View>
      </View>
      <BottomButton onPress={() => navigator.navigate("HomeNavigator", {screen: "HomeScreen" })} title="Tiếp tục" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    backgroundColor: AppColors.green,
    padding: 20,
  },
  title: {
    fontSize: 32,
    fontWeight: "bold",
    color: AppColors.white,
    marginTop: 40,
  },

  animationContainer: {
    minWidth: "90%",
    minHeight: "40%",
  },
  progressContainer: {
    position: "absolute",
    left: 0,
    right: 0,
    bottom: 0,
  },
  progressBar: {
    flexDirection: "row",
    justifyContent: "space-between",
    backgroundColor: AppColors.darkGreen,
    borderRadius: 10,
    padding: 6,
  },
  heading: {
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 20,
  },
  fireImage: {
    width: 100,
    height: 100,
    resizeMode: "center",
  },
  streakNumber: {
    fontSize: 80,
    fontWeight: "bold",
    color: AppColors.yellow,
    textShadowColor: AppColors.black,
    textShadowOffset: { width: 2, height: 2 },
    textShadowRadius: 4,
  },
  progressStep: {
    width: `${100 / 7 - 1}%`,
    aspectRatio: 1,
    borderRadius: 50,
  },
  progressLabels: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 8,
  },
  progressLabel: {
    color: AppColors.light,
    fontSize: 15,
    fontWeight: "600",
  },
});
