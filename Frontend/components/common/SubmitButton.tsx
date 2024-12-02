import {
  Pressable,
  StyleSheet,
  Text,
  TextStyle,
  View,
  ViewStyle,
} from "react-native";
import React from "react";
import { AppColors } from "../../types/colors";

interface SubmitButtonProp {
  title: string;
  onPress: () => void;
}

const SubmitButton = ({
  title,
  onPress,
}: SubmitButtonProp) => {
  return (
    <Pressable
      style={({ pressed }) => [
        styles.button,
        pressed && styles.buttonPressed,
      ]}
      onPress={onPress}
    >
      <Text style={styles.buttonText}>{title}</Text>
    </Pressable>
  );
};

export default SubmitButton;

const styles = StyleSheet.create({
  button: {
    backgroundColor: AppColors.darkGreen,
    borderRadius: 8,
    paddingVertical: 16,
    marginVertical: 5,
    alignItems: "center",
  },
  buttonPressed: {
    backgroundColor: AppColors.gray,
    opacity: 0.8,
  },
  buttonText: {
    color: AppColors.white,
    fontSize: 16,
    fontWeight: "bold",
  },
});
