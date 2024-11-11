import { Image, Pressable, StyleSheet, Text, View } from "react-native";
import React from "react";
import { Lesson } from "../../types/apimodels";
import ProgressBar from "../common/ProgressBar";
import { AppColors } from "../../types/colors";
import { Ionicons } from "@expo/vector-icons";
import { LessonStatus } from "../../types/enum";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { RootNavigatorParams } from "../../navigation/AppNavigation";
import { sampleTasks } from "../../utils/InitData";

interface UserInfoViewProp {
  lesson: Lesson;
}

interface LessonStatusBadge {
  icon: keyof typeof Ionicons.glyphMap;
  color: string;
}

function getBadge(status: LessonStatus): LessonStatusBadge {
  switch (status) {
    case LessonStatus.COMPLETED:
      return { icon: "trophy", color: AppColors.green };
    case LessonStatus.LOCKED:
      return { icon: "lock-closed-outline", color: AppColors.grayDark };
    case LessonStatus.ONGOING:
      return { icon: "play", color: AppColors.yellow };
  }
}

const UserInfoView = ({ lesson }: UserInfoViewProp) => {
  const { color, icon } = getBadge(lesson.status);

  const navigator = useNavigation<NavigationProp<RootNavigatorParams>>();

  function onItemPress() {
    navigator.navigate("LearnNavigator", {
      screen: "SplashLearnScreen",
      params: { lessonId: lesson.id },
    });
  }

  return (
    <Pressable style={styles.container} onPress={onItemPress}>
      <View style={styles.leftContainer}>
        <View style={styles.heading}>
          <Text style={styles.percent}>
            {lesson.lastLearnQuestion}/{lesson.totalQuestion}
          </Text>
          <ProgressBar
            progress={lesson.lastLearnQuestion / lesson.totalQuestion}
            containerStyle={styles.progressbar}
          />
        </View>
        <Text style={styles.lessonName}>{lesson.name}</Text>
      </View>

      <View style={styles.rightContainer}>
        <Image source={{ uri: lesson.cover }} style={styles.cover} />
      </View>

      <View style={styles.badgeContainer}>
        <Ionicons size={25} name={icon} color={color} style={styles.badge} />
      </View>
    </Pressable>
  );
};

export default UserInfoView;

const styles = StyleSheet.create({
  container: {
    padding: 10,
    margin: 10,
    backgroundColor: AppColors.lightGreen,
    borderRadius: 20,
    flexDirection: "row",
    alignItems: "center",
    elevation: 4,
  },
  leftContainer: {
    flex: 4,
  },
  rightContainer: {
    flex: 2,
  },
  heading: {
    flexDirection: "row",
    alignItems: "center",
    padding: 10,
    justifyContent: "center",
  },
  percent: {
    color: AppColors.black,
    marginRight: 10,
  },
  badgeContainer: {
    position: "absolute",
    right: 0,
    top: "-10%",
    borderWidth: 2,
    backgroundColor: AppColors.light,
    borderRadius: 30,
  },
  badge: {
    padding: 2,
    textAlign: "center",
  },
  progressbar: {
    width: "50%",
    height: "50%",
    backgroundColor: AppColors.white,
    borderRadius: 10,
  },
  lessonName: {
    fontSize: 20,
    textAlign: "center",
  },
  cover: {
    borderRadius: 40,
    width: 70,
    height: 70,
  },
});
