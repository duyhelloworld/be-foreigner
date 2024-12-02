import { Image, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useState } from "react";
import { Lesson } from "../../types/apimodels";
import { BadgeColors } from "../../types/colors";
import { Ionicons } from "@expo/vector-icons";
import { LessonStatus } from "../../types/enum";
import { useAppNavigation } from "../../navigation/AppNavigation";

interface LessonInfoViewProp {
  lesson: Lesson;
  backgroundColor?: string;
}

function getBadgeIcon(status: LessonStatus): keyof typeof Ionicons.glyphMap {
  switch (status) {
    case LessonStatus.COMPLETED:
      return "trophy";
    case LessonStatus.ONGOING:
      return "play";
    default:
      return "lock-closed";
  }
}

const LessonInfoView = ({ lesson }: LessonInfoViewProp) => {
  const navigator = useAppNavigation();
  const badgeIcon = getBadgeIcon(lesson.status);
  const randomIndex = Math.floor(Math.random() * BadgeColors.length);
  const badge = BadgeColors[randomIndex];

  function onItemPress() {
    navigator.navigate("LearnNavigator", {
      screen: "SplashLearnScreen",
      params: { lessonId: lesson.id },
    });
  }

  return (
    <Pressable style={[styles.container, { backgroundColor: badge.background }]} onPress={onItemPress}>

      <View style={styles.leftContainer}>
        <Text style={styles.lessonName}>{lesson.name}</Text>
      </View>

      <View style={styles.rightContainer}>
        <Image source={{ uri: lesson.cover }} style={styles.cover} />
      </View>

      <View style={styles.badgeContainer}>
        <Ionicons size={25} name={badgeIcon} color={badge.badge} style={styles.badge} />
      </View>
    </Pressable>
  );
};

export default LessonInfoView;

const styles = StyleSheet.create({
  container: {
    padding: 10,
    margin: 10,
    borderRadius: 20,
    flexDirection: "row",
    alignItems: "center",
    elevation: 1,
  },
  leftContainer: {
    flex: 4,
  },
  rightContainer: {
    flex: 2,
  },
  badgeContainer: {
    position: "absolute",
    right: 0,
    top: "-10%",
    borderWidth: 2,
    borderRadius: 30,
  },
  badge: {
    padding: 2,
    textAlign: "center",
  },
  lessonName: {
    fontSize: 20,
    textAlign: "center",
  },
  cover: {
    borderRadius: 20,
    width: 70,
    height: 70,
  },
});
