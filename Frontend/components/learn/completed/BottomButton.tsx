import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { AppColors } from '../../../types/Colors';

interface BottomButtonProp {
  onPress: () => void;
  title: string;
}

const BottomButton = ({title, onPress} : BottomButtonProp) => {
  return (
    <Pressable style={({pressed}) => [styles.container, pressed ? styles.pressed : null]} onPress={onPress}>
      <Text style={styles.text}>{title}</Text>
    </Pressable>
  );
}

export default BottomButton

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    bottom: 15,
    backgroundColor: AppColors.darkGreen,
    marginHorizontal: 20,
    width: "90%",
    borderRadius: 20,
    paddingVertical: 20,
  },
  pressed: {
    backgroundColor: AppColors.green,
    opacity: 0.7
  },
  text: {
    fontSize: 20,
    textAlign: "center",
    color: AppColors.white
  }
})