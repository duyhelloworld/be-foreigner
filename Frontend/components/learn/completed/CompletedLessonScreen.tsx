import { Animated, Image, StyleSheet, Text, View, useAnimatedValue } from "react-native";
import React, { useEffect } from "react";
import { AppColors } from "../../../types/colors";
import {
  useAppNavigation,
  useAppParams,
} from "../../../navigation/AppNavigationHooks";
import BottomButton from "./BottomButton";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse } from "../../../types/apimodels";
import { ApiResponseCode } from "../../../types/enum";
import accuracyImage from "../../../assets/accuracy.png";
import diamondImage from "../../../assets/diamond.png";
import experienceImage from "../../../assets/experience.png";
import appImage from "../../../assets/icon.png";

const CompletedLessonScreen = () => {
  const { diamonds, accuracy, experiences, lessonId } = useAppParams(
    "LearnNavigator",
    "CompletedLessonScreen"
  );
  const navigator = useAppNavigation();

  useEffect(() => {
    
  }, []);

  async function onContinuePress() {
    const response = await apiClient.put<ApiResponse>("lesson/exam/complete", {
      lessonId,
      accuracy,
    });
    if (response.data.code === ApiResponseCode.OK) {
      navigator.navigate("LearnNavigator", { screen: "StraightScreen" });
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.completedText}>Hoàn thành bài học</Text>

      <Image source={appImage} style={styles.appImage} />

      <View style={styles.statisticContainer}>
        <View style={styles.statisticRow}>
          <Image source={diamondImage} style={styles.statisticImage} />
          <Animated.Text style={styles.statisticText}>
            +{diamonds}
          </Animated.Text>
        </View>

        <View style={styles.statisticRow}>
          <Image source={experienceImage} style={styles.statisticImage} />
          <Text style={styles.statisticText}>+ {experiences}</Text>
        </View>

        <View style={styles.statisticRow}>
          <Image source={accuracyImage} style={styles.statisticImage} />
          <Text style={styles.statisticText}>{accuracy}%</Text>
        </View>
      </View>

      <BottomButton title="Tiếp tục" onPress={onContinuePress} />
    </View>
  );
};

export default CompletedLessonScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
  },
  completedText: {
    color: AppColors.green,
    fontWeight: "700",
    fontSize: 30,
    marginVertical: 30,
  },
  appImage: {
    width: 200,
    height: 200,
    marginTop: 20,
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
    borderColor: AppColors.green,
    padding: 10,
    margin: 5,
  },
  statisticImage: {
    width: 80,
    height: 80,
    resizeMode: "contain",
  },
  statisticText: {
    textAlign: "center",
    fontSize: 15,
    fontWeight: "800",
  },
});
