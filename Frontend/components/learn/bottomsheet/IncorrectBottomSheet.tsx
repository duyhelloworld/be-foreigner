import React from "react";
import { StyleSheet } from "react-native";
import AbstractBottomSheet from "./AbstractBottomSheet";
import { AppColors } from "../../../types/colors";

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
        style: styles.button,
        onPress: onSkipPress,
        isCorrect: false
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
    backgroundColor: "#DDD",
  },
  
  retryButton: {
    backgroundColor: "#0FF",
  },
});

export default IncorrectBottomSheet;
