import { Pressable, StyleSheet, Text, TextStyle, View, ViewStyle } from 'react-native'
import React from 'react'
import { AppColors } from '../../types/colors';

interface BottomButtonProp {
  title: string;
  onPress: () => void;
  style?: ViewStyle;
  titleStyle?: TextStyle;
}

const BottomButton = ({title, onPress, style, titleStyle}: BottomButtonProp) => {
  return (
    <Pressable
      style={({ pressed }) => [
        style, styles.button,
        pressed && styles.buttonPressed,
      ]}
      onPress={onPress}
    >
      <Text style={[titleStyle, styles.buttonText]}>{title}</Text>
    </Pressable>
  );
}

export default BottomButton

const styles = StyleSheet.create({
  button: {
    position: "absolute",
    bottom: 20,
    width: "80%",
    backgroundColor: AppColors.darkGreen,
    borderRadius: 8,
    paddingVertical: 14,
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