import { FlatList, Image, StyleSheet, Text, View } from "react-native";
import React from "react";
import { AppColors } from "../../../types/colors";
import {
  useAppNavigation,
  useAppParams,
} from "../../../navigation/AppNavigationHooks";
import BottomButton from "./BottomButton";
import TaskItemView from "../../task/TaskItemView";

const CompletedLessonScreen = () => {
  const { diamonds, accuracy, experiences, tasks } = useAppParams(
    "LearnNav",
    "CompletedLessonScreen"
  );
  const navigator = useAppNavigation();

  function onContinuePress() {
    navigator.navigate("LearnNavigator", { screen: "StraightScreen" });
  }

  return (
    <View style={styles.container}>
      <Text style={styles.completedText}>Hoàn thành bài học</Text>

      <View style={styles.statisticContainer}>
        <View style={styles.statisticRow}>
          <Image
            source={require("../../../assets/diamond.png")}
            style={styles.statisticImage}
          />
          <Text style={styles.statisticText}>+ {diamonds}</Text>
        </View>

        <View style={styles.statisticRow}>
          <Image
            source={require("../../../assets/experience.png")}
            style={styles.statisticImage}
          />
          <Text style={styles.statisticText}>+ {experiences}</Text>
        </View>

        <View style={styles.statisticRow}>
          <Image
            source={require("../../../assets/accuracy.png")}
            style={styles.statisticImage}
          />
          <Text style={styles.statisticText}>{accuracy}%</Text>
        </View>
      </View>

      <FlatList
        data={tasks}
        keyExtractor={task => task.name}
        renderItem={({ item }) => <TaskItemView task={item} />}
        style={styles.taskContainer}
      />

      <BottomButton title="Tiếp tục" onPress={onContinuePress} />
    </View>
  );
};

export default CompletedLessonScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  completedText: {
    color: AppColors.green,
    fontWeight: "700",
    fontSize: 30,
    textAlign: "center",
    marginVertical: 30,
  },
  statisticContainer: {
    flexDirection: "row",
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
  taskContainer: {
    marginHorizontal: 10,
    marginVertical: 15,
  },
});
