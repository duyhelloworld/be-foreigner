import { Pressable, StyleSheet, Text, View } from "react-native";
import React from "react";
import { AppColors } from "../../types/colors";
import { Ionicons } from "@expo/vector-icons";

interface ImagePickerViewProp {
  label: string;
  onPress: () => void;
  icon: keyof typeof Ionicons.glyphMap;
}

const ImagePickerView = ({ label, onPress, icon }: ImagePickerViewProp) => {
  return (
    <View style={styles.container}>
      <Pressable style={styles.button} onPress={onPress}>
        <Ionicons name={icon} size={20} color="white" style={styles.icon} />
        <Text style={styles.buttonText}>{label}</Text>
      </Pressable>
    </View>
  );
};

export default ImagePickerView;

const styles = StyleSheet.create({
  container: {
    alignItems: "center",
    marginVertical: 20,
  },
  button: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: AppColors.gray,
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderRadius: 10,
  },
  icon: {
    marginRight: 8,
  },
  buttonText: {
    color: "white",
    fontSize: 16,
  },
});