import React from "react";
import { StyleSheet } from "react-native";
import AbstractBottomSheet from "./AbstractBottomSheet";
import { AppColors } from "../../../types/colors";
import incorrectSoundEffect from "../../../assets/incorrect-answer-effect.mp3";

interface IncorrectBottomSheetProp {
  message: string;
  onSkipPress: () => void;
}

const IncorrectBottomSheet = ({
  message,
  onSkipPress,
}: IncorrectBottomSheetProp) => {
  return (
    <AbstractBottomSheet
      backgroundColor={AppColors.red}
      title="Sai rồi nà!"
      message={message}
      button={{
        text: "Bỏ qua",
        style: [styles.button, styles.skipButton],
        textStyle: styles.buttonText,
        onPress: onSkipPress,
        buttonAudio: incorrectSoundEffect
      }}
    />
  );
};

const styles = StyleSheet.create({
  button: {
    padding: 20,
    borderRadius: 10,
    marginHorizontal: 20,
    marginBottom: 20,
  },
  buttonText: {
    fontSize: 18,
    fontWeight: "bold",
  },
  skipButton: {
    backgroundColor: "#DDD",
  },
  retryButton: {
    backgroundColor: "#0FF",
  },
});

export default IncorrectBottomSheet;
