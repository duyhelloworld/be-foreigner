import {
  Animated,
  Dimensions,
  Platform,
  Pressable,
  TextStyle,
  ViewStyle,
  useAnimatedValue,
} from "react-native";
import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useRef, useState } from "react";
import correctSoundEffect from "../../../assets/correct-answer-effect.mp3";
import incorrectSoundEffect from "../../../assets/incorrect-answer-effect.mp3";
import { AppColors } from "../../../types/colors";
import SoundPlayer from "react-native-sound-player";

const { height: SCREEN_HEIGHT, width: SCREEN_WIDTH } =
  Platform.OS === "android"
    ? Dimensions.get("screen")
    : Dimensions.get("window");

const BOTTOM_SHEET_MAX_HEIGHT = SCREEN_HEIGHT * 0.25;
const BOTTOM_SHEET_MIN_HEIGHT = 0;
const MAX_UPWARD_TRANSLATE_Y =
  BOTTOM_SHEET_MIN_HEIGHT - BOTTOM_SHEET_MAX_HEIGHT;
const MAX_DOWNWARD_TRANSLATE_Y = 0;

interface AbstractBottomSheetProp {
  title: string;
  backgroundColor: string;
  isCorrect: boolean;
  message: string;
  button: {
    text: string;
    onPress: () => void;
  };
}

const AbstractBottomSheet = ({
  title,
  backgroundColor,
  message,
  button,
  isCorrect,
}: AbstractBottomSheetProp) => {
  const animatedValue = useAnimatedValue(0);
  const lastGestureDy = useRef(0);

  const springAnimation = (toValue: number) => {
    lastGestureDy.current = toValue;
    Animated.spring(animatedValue, {
      toValue: lastGestureDy.current,
      useNativeDriver: false,
    }).start();
  };

  useEffect(() => {
    SoundPlayer.playAsset(isCorrect ? correctSoundEffect : incorrectSoundEffect);
    springAnimation(MAX_UPWARD_TRANSLATE_Y);
  }, []);

  const animatedStyle = () => {
    return {
      backgroundColor: backgroundColor,
      transform: [
        {
          translateY: animatedValue.interpolate({
            inputRange: [MAX_UPWARD_TRANSLATE_Y, MAX_DOWNWARD_TRANSLATE_Y],
            outputRange: [MAX_UPWARD_TRANSLATE_Y, MAX_DOWNWARD_TRANSLATE_Y],
          }),
        },
      ],
    };
  };

  const onClose = () => {
    springAnimation(MAX_DOWNWARD_TRANSLATE_Y);
    button.onPress();
  };

  return (
    <Animated.View style={[styles.bottomSheet, animatedStyle()]}>
      <View style={styles.titleContainer}>
        <Text style={styles.title}>{title}</Text>
        <Text style={styles.message}>{message}</Text>
      </View>

      <Pressable style={styles.buttonStyle} onPress={onClose}>
        <Text style={styles.buttonText}>{button.text}</Text>
      </Pressable>
    </Animated.View>
  );
};

export default AbstractBottomSheet;

const styles = StyleSheet.create({
  bottomSheet: {
    position: "absolute",
    width: "100%",
    justifyContent: "center",
    height: BOTTOM_SHEET_MAX_HEIGHT,
    bottom: BOTTOM_SHEET_MIN_HEIGHT - BOTTOM_SHEET_MAX_HEIGHT,
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    zIndex: 2,
  },
  titleContainer: {
    marginTop: 20,
    marginBottom: 10,
    paddingHorizontal: 10,
  },
  message: {
    fontSize: 16,
  },
  test: {
    flex: 1,
    borderWidth: 1,
    borderColor: "red",
  },
  buttonText: {
    fontWeight: "600",
    fontSize: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    color: "white",
    marginBottom: 20,
  },
  buttonStyle: {
    maxHeight: "70%",
    backgroundColor: AppColors.white,
    padding: 20,
    borderRadius: 10,
  },
  continueButtonText: {
    fontSize: 18,
    fontWeight: "bold",
    textAlign: 'center',
  },
});
