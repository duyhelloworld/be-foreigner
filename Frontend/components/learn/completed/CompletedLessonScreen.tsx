import { Image, StyleSheet, Text, View, useAnimatedValue } from "react-native";
import React  from "react";
import { AppColors } from "../../../types/colors";
import BottomButton from "../../common/BottomButton";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse } from "../../../types/apimodels";
import { ApiResponseCode } from "../../../types/enum";
import accuracyImage from "../../../assets/accuracy.png";
import appImage from "../../../assets/icon.png";
import { useAppNavigation, useRootParams } from "../../../navigation/AppNavigation";

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
      navigator.navigate("LearnNavigator", { screen: "StreakScreen" , params: { streakDay }});
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.completedText}>Hoàn thành bài học</Text>
      <Image source={appImage} style={styles.appImage} />
      <View style={styles.statisticContainer}>
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
