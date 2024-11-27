import { Pressable, StyleSheet, Text, TextStyle, View, ViewStyle } from 'react-native'
import React from 'react'
import { AppColors } from '../../types/colors';

interface SubmitButtonViewProp {
  title: string;
  onPress: () => void;
  style?: ViewStyle;
  titleStyle?: TextStyle;
}

const SubmitButtonView = ({title, onPress, style, titleStyle}: SubmitButtonViewProp) => {
  return (
    <Pressable
      style={({ pressed }) => [
        style ?? styles.button,
        pressed && styles.buttonPressed,
      ]}
      onPress={onPress}
    >
      <Text style={titleStyle ?? styles.buttonText}>{title}</Text>
    </Pressable>
  );
}

export default SubmitButtonView

const styles = StyleSheet.create({
  button: {
    position: "absolute",
    bottom: 20,
    backgroundColor: AppColors.green,
    borderRadius: 8,
    paddingVertical: 12,
    marginVertical: 10,
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