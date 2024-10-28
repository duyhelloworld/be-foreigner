import {
  Animated,
  Pressable,
  TextStyle,
  ViewStyle,
  useAnimatedValue,
} from "react-native";
import { SCREEN_HEIGHT } from "../../../config/AppScreenConstant";
import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useRef } from "react";
import { playAudio } from "../../../utils/AudioUtil";

const BOTTOM_SHEET_MAX_HEIGHT = SCREEN_HEIGHT * 0.25;
const BOTTOM_SHEET_MIN_HEIGHT = 0;
const MAX_UPWARD_TRANSLATE_Y =
  BOTTOM_SHEET_MIN_HEIGHT - BOTTOM_SHEET_MAX_HEIGHT;
const MAX_DOWNWARD_TRANSLATE_Y = 0;

type Button = {
  text: string;
  style: ViewStyle[] | ViewStyle;
  textStyle: TextStyle;
  pressAudio: any;
  onPress: () => void;
};

interface AbstractBottomSheetProp {
  title: string;
  backgroundColor: string;
  button: Button;
  message: string;
}

const AbstractBottomSheet = ({
  title,
  backgroundColor,
  message,
  button,
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

  const onOpen = () => {
    springAnimation(MAX_UPWARD_TRANSLATE_Y);
    playAudio(button.pressAudio)
  };

  const onClose = () => {
    springAnimation(MAX_DOWNWARD_TRANSLATE_Y);
    button.onPress();
  };

  useEffect(() => {
    onOpen();
  }, []);

  return (
    <Animated.View style={[styles.bottomSheet, animatedStyle()]}>
      <View style={styles.titleContainer}>
        <Text style={styles.title}>{title}</Text>
        {message && <Text style={styles.message}>{message}</Text>}
      </View>

      {button && (
        <Pressable style={button.style} onPress={onClose}>
          <Text style={button.textStyle}>{button.text}</Text>
        </Pressable>
      )}
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
  title: {
    fontSize: 24,
    fontWeight: "bold",
    color: "white",
    marginBottom: 20,
  },
});
