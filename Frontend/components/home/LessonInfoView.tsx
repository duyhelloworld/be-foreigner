import React, { useRef, useEffect } from "react";
import { Image, Pressable, StyleSheet, Text, View, Animated } from "react-native";
import { Lesson } from "../../types/apimodels";
import { BadgeColors } from "../../types/colors";
import { Ionicons } from "@expo/vector-icons";
import { LessonStatus } from "../../types/enum";
import { useAppNavigation } from "../../navigation/AppNavigation";

interface LessonInfoViewProp {
  lesson: Lesson;
  index: number;
}

function getBadgeIcon(status: LessonStatus): keyof typeof Ionicons.glyphMap {
  switch (status) {
    case LessonStatus.COMPLETED:
      return "checkmark-circle";
    case LessonStatus.ONGOING:
      return "play-circle";
    default:
      return "lock-closed";
  }
}

const LessonInfoView = ({ lesson, index }: LessonInfoViewProp) => {
  const navigator = useAppNavigation();
  const badgeIcon = getBadgeIcon(lesson.status);
  const colorIndex = lesson.id % BadgeColors.length;
  const badge = BadgeColors[colorIndex];

  const fadeAnim = useRef(new Animated.Value(0)).current;
  const scaleAnim = useRef(new Animated.Value(0.8)).current;

  useEffect(() => {
    fadeAnim.setValue(0);
    scaleAnim.setValue(0.8);
    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 500,
        delay: index * 100,
        useNativeDriver: true,
      }),
      Animated.spring(scaleAnim, {
        toValue: 1,
        friction: 4,
        tension: 50,
        delay: index * 100,
        useNativeDriver: true,
      }),
    ]).start();
  }, [lesson, index]);

  function onItemPress() {
    navigator.navigate("LearnNavigator", {
      screen: "SplashLearnScreen",
      params: { lessonId: lesson.id },
    });
  }

  return (
    <Animated.View style={{ opacity: fadeAnim, transform: [{ scale: scaleAnim }] }}>
      <Pressable style={[styles.container, { backgroundColor: badge.background }]} onPress={onItemPress}>
        <View style={styles.leftContainer}>
          <Text style={styles.lessonName}>{lesson.name}</Text>
        </View>

        <View style={styles.rightContainer}>
          <Image source={{ uri: lesson.cover }} style={styles.cover} />
        </View>

        <View style={[styles.badgeContainer, { backgroundColor: badge.badge }]}>
          <Ionicons size={20} name={badgeIcon} color="#FFFFFF" style={styles.badge} />
        </View>
      </Pressable>
    </Animated.View>
  );
};

export default LessonInfoView;

const styles = StyleSheet.create({
  container: {
    padding: 15,
    margin: 10,
    borderRadius: 20,
    flexDirection: "row",
    alignItems: "center",
    elevation: 3,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
  },
  leftContainer: {
    flex: 4,
  },
  rightContainer: {
    flex: 2,
    alignItems: 'flex-end',
  },
  badgeContainer: {
    position: "absolute",
    right: -10,
    top: -10,
    borderRadius: 15,
    padding: 5,
  },
  badge: {
    textAlign: "center",
  },
  lessonName: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
  },
  cover: {
    borderRadius: 15,
    width: 70,
    height: 70,
  },
});