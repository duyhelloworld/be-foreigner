import React from "react";
import {  StyleSheet } from "react-native";
import AbstractBottomSheet from "./AbstractBottomSheet";
import { AppColors } from "../../../types/colors";

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
      isCorrect
      button={{
        text: "Tiếp tục",
        onPress: onContinuePress,
      }}
    />
  );
};

const styles = StyleSheet.create({
  
});

export default CorrectBottomSheet;
