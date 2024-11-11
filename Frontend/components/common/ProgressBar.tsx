import React from "react";
import { View, StyleSheet, ViewStyle, StyleProp } from "react-native";
import { AppColors } from "../../types/colors";

interface ProgressBarProps {
  progress: number; // Giá trị từ 0 đến 1
  containerStyle?: StyleProp<ViewStyle>;
  progressBarStyle?: StyleProp<ViewStyle>;
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: AppColors.white,
    borderWidth: 1,
    borderRadius: 10,
  },
  progressBar: {
    flex: 1,
    borderRadius: 10,
    backgroundColor: AppColors.green,
  },
});

const ProgressBar = ({ progress, containerStyle=styles.container, progressBarStyle=styles.progressBar } : ProgressBarProps) => {
  return (
    <View style={containerStyle}>
      <View style={[styles.progressBar, progressBarStyle, { width: `${progress * 100}%` }]} />
    </View>
  );
};

export default ProgressBar;
