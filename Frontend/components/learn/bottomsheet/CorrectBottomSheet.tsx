import React from "react";
import {  StyleSheet } from "react-native";
import AbstractBottomSheet from "./AbstractBottomSheet";
import { AppColors } from "../../../types/colors";
import correctSoundEffect from "../../../assets/correct-answer-effect.mp3"

interface CorrectBottomSheetProp {
  message: string;
  onContinuePress: () => void;
}

const CorrectBottomSheet = ({onContinuePress, message} : CorrectBottomSheetProp) => {
  
  return (
    <AbstractBottomSheet
      title="Chính xác"
      message={message}
      backgroundColor={AppColors.green}
      button={{
        text: "Tiếp tục",
        style: styles.continueButton,
        textStyle: styles.continueButtonText,
        onPress: onContinuePress,
        buttonAudio: correctSoundEffect
      }}
    />
  );
};

const styles = StyleSheet.create({
  continueButton: {
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

export default CorrectBottomSheet;
