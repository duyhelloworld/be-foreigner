import React, { useEffect, useRef } from "react";
import { Animated, Image, StyleSheet, Text, View, Easing, ViewStyle } from "react-native";
import { AppColors } from "../../../types/colors";
import BottomButton from "../../common/BottomButton";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse } from "../../../types/apimodels";
import { ApiResponseCode } from "../../../types/enum";
import accuracyImage from "../../../assets/accuracy.png";
import appImage from "../../../assets/icon-transparent.png";
import {
  useAppNavigation,
  useRootParams,
} from "../../../navigation/AppNavigation";
import GradientBackground from "../../common/GradientBackground";

const CompletedLessonScreen = () => {
  const { accuracy, historyId } = useRootParams(
    "LearnNavigator",
    "CompletedLessonScreen"
  );
  
  const navigator = useAppNavigation();

  async function onContinuePress() {
    const response = await apiClient.put<ApiResponse>("lesson/exam/complete", {
      historyId,
      accuracy,
    });
    if (response.data.code === ApiResponseCode.OK) {
      const streakDay = response.data.data as number;
      navigator.navigate("LearnNavigator", {
        screen: "StreakScreen",
        params: { streakDay },
      });
    }
    else {
      alert(response.data.data as string[]);
    }
  }

  const getBatteryColor = () => {
    if (accuracy < 20) return AppColors.red;
    if (accuracy < 60) return AppColors.orange;
    return AppColors.lightGreen;
  };

  const batteryStyle: ViewStyle = {
    width: 120,
    height: accuracy * 160 / 100,
    borderBottomEndRadius: 20,
    borderBottomStartRadius: 20,
    bottom: 0,
    zIndex: 0,
    position: 'absolute',
    borderTopWidth: 1,
    backgroundColor: getBatteryColor(),
  }

  return (
    <GradientBackground style={styles.container}>
      <Image source={appImage} style={styles.appImage} />
      <Text style={styles.completedText}>Hoàn thành bài học</Text>
      <View style={styles.statisticContainer}>
        <View style={styles.statisticRow}>
          <Image source={accuracyImage} style={styles.statisticImage} />
          <Text style={styles.statisticText}>{accuracy}%</Text>
          <View style={batteryStyle}></View>
        </View>
      </View>
        <BottomButton title="Tiếp tục" onPress={onContinuePress} />
    </GradientBackground>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
  },
  completedText: {
    color: AppColors.white,
    fontWeight: "700",
    fontSize: 40,
    textShadowColor: AppColors.black,
    textShadowOffset: {width: 2, height: -3},
    textShadowRadius: 2,
  },
  appImage: {
    width: 200,
    height: 200,
    marginTop: "15%",
    resizeMode: "contain",
  },
  statisticContainer: {
    flexDirection: "row",
    marginTop: 50,
    justifyContent: "space-between",
  },
  statisticRow: {
    borderRadius: 20,
    borderWidth: 2,
    borderColor: AppColors.lightGray,
    backgroundColor: AppColors.white,
    padding: 10,
    margin: 5,
  },
  statisticImage: {
    width: 100,
    zIndex: 1,
    height: 140,
    resizeMode: "contain",
  },
  statisticText: {
    textAlign: "center",
    fontSize: 15,
    zIndex: 1,
    fontWeight: "800",
  },
});

export default CompletedLessonScreen;
