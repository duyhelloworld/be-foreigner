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
      isCorrect={false}
      button={{
        text: "Bỏ qua",
        onPress: onSkipPress,
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
});

export default IncorrectBottomSheet;
