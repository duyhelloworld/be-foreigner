import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { AppColors } from '../../types/Colors';

interface SubmitButtonViewProp {
  title: string;
  onPress: () => void;
}

const SubmitButtonView = ({title, onPress}: SubmitButtonViewProp) => {
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
}

export default SubmitButtonView

const styles = StyleSheet.create({
  button: {
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