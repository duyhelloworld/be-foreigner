import React, { useEffect, useRef, useState } from "react";
import {
  Animated,
  Image,
  StyleSheet,
  Text,
  View,
  Easing,
  ViewStyle,
  ActivityIndicator,
} from "react-native";
import { AppColors } from "../../../types/colors";
import BottomButton from "../../common/BottomButton";
import accuracyImage from "../../../assets/accuracy.png";
import appImage from "../../../assets/icon-transparent.png";
import GradientBackground from "../../common/GradientBackground";
import { ApiResponseCode } from "../../../types/enum";
import {
  useAppNavigation,
  useRootParams,
} from "../../../navigation/AppNavigation";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse, Streak } from "../../../types/apimodels";

const BATTERY_WIDTH_CONTAINER = 200;
const BATTERY_WIDTH = 80;
const BATTERY_HEIGHT = 160;

const CompletedLessonScreen = () => {
  const batteryHeight = useRef(new Animated.Value(0)).current;
  const batteryOpacity = useRef(new Animated.Value(0)).current;
  const textOpacity = useRef(new Animated.Value(0)).current;
  const buttonOpacity = useRef(new Animated.Value(0)).current;
  const [animationsComplete, setAnimationsComplete] = useState(false);

  const { accuracy, historyId } = useRootParams(
    "LearnNavigator",
    "CompletedLessonScreen"
  );
  const navigator = useAppNavigation();

  const animationSequence = Animated.sequence([
    Animated.timing(batteryOpacity, {
      toValue: 1,
      duration: 500,
      useNativeDriver: false,
    }),
    Animated.timing(batteryHeight, {
      toValue: (accuracy * 160) / 100,
      duration: 1000,
      easing: Easing.out(Easing.ease),
      useNativeDriver: false,
    }),
    Animated.timing(textOpacity, {
      toValue: 1,
      duration: 500,
      useNativeDriver: false,
    }),
    Animated.timing(buttonOpacity, {
      toValue: 1,
      duration: 500,
      useNativeDriver: false,
    })
  ]);

  useEffect(() => {
    animationSequence.start(() => setAnimationsComplete(true));
  }, []);

  async function onContinuePress() {
    if (!animationsComplete) return;
    setAnimationsComplete(false); 
    const completedResponse = await apiClient.put<ApiResponse>("lesson/exam/complete", {
      historyId,
      accuracy,
    });
    if (completedResponse.data.code === ApiResponseCode.OK) {
      const streakResponse = await apiClient.get<ApiResponse>("user/streak");
      if (streakResponse.data.code === ApiResponseCode.OK) {
        const streak = streakResponse.data.data as Streak;
        if (streak.hasLearned) {
          navigator.navigate("HomeNavigator", { screen: "HomeScreen" });
        } else {
          navigator.navigate("LearnNavigator", {
            screen: "StreakScreen",
            params: { streakDay: streak.streakDays },
          });
        }
      }
    } else {
      alert(completedResponse.data.data as string[]);
    }
  }

  const getBatteryColor = () => {
    if (accuracy < 20) return AppColors.red;
    if (accuracy < 60) return AppColors.orange;
    return AppColors.lightGreen;
  };

  const batteryFillStyle: ViewStyle = {
    width: 80,
    height: batteryHeight,
    backgroundColor: getBatteryColor(),
  };

  return (
    <GradientBackground style={styles.container}>
      <View style={styles.content}>
        <Animated.Image
          source={appImage}
          style={[styles.appImage, { opacity: textOpacity }]}
        />
        <Animated.Text style={[styles.completedText, { opacity: textOpacity }]}>
          Hoàn thành bài học
        </Animated.Text>

        <View style={styles.batteryContainer}>
          <View style={styles.batteryInner}>
            <Image source={accuracyImage} style={styles.flashImage} />
            <Animated.View
              style={[
                styles.batteryFill,
                batteryFillStyle,
                { opacity: batteryOpacity },
              ]}
            />
          </View>
          <Animated.Text
            style={[styles.batteryPercentText, { opacity: textOpacity }]}
          >
            {accuracy}%
          </Animated.Text>
        </View>
      </View>
      <Animated.View
        style={[styles.buttonContainer, { opacity: buttonOpacity }]}
      >
        <BottomButton title="Tiếp tục" onPress={onContinuePress} />
      </Animated.View>
    </GradientBackground>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  content: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
  appImage: {
    width: 200,
    height: 200,
    resizeMode: "contain",
    marginBottom: 20,
  },
  completedText: {
    color: AppColors.white,
    fontWeight: "700",
    fontSize: 40,
    textShadowColor: AppColors.black,
    textShadowOffset: { width: 2, height: -3 },
    textShadowRadius: 2,
    marginBottom: 30,
  },
  batteryContainer: {
    alignItems: "center",
    backgroundColor: AppColors.white,
    borderRadius: 20,
    padding: 20,
    width: BATTERY_WIDTH_CONTAINER,
  },
  flashImage: {
    width: BATTERY_WIDTH,
    height: BATTERY_HEIGHT,
    resizeMode: "center",
    zIndex: 2,
  },
  batteryInner: {
    width: BATTERY_WIDTH,
    height: BATTERY_HEIGHT,
    borderWidth: 2,
    borderColor: AppColors.lightGray,
    borderRadius: 10,
    overflow: "hidden",
    marginBottom: 10,
  },
  batteryFill: {
    position: "absolute",
    bottom: 0,
    left: 0,
    right: 0,
  },
  batteryPercentText: {
    fontSize: 24,
    fontWeight: "800",
    color: AppColors.darkGreen,
    textShadowColor: AppColors.black,
    textShadowOffset: { width: 1, height: 0 },
    textShadowRadius: 1,
  },
  buttonContainer: {
    paddingHorizontal: 20,
    alignItems: "center",
    paddingBottom: 20,
  },
});

export default CompletedLessonScreen;
