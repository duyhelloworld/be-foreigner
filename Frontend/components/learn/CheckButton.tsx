import { Pressable, StyleSheet, Text, View } from "react-native";
import { AppColors } from "../../types/colors";

interface CheckButtonProp {
  onCheckPress: () => void;
}

const CheckButton = ({ onCheckPress }: CheckButtonProp) => {
  return (
    <Pressable style={styles.checkButton} onPress={onCheckPress}>
      <Text>Kiểm tra</Text>
    </Pressable>
  );
};

export default CheckButton;

const styles = StyleSheet.create({
  checkButton: {
    padding: 20,
    marginBottom: "20%",
    borderRadius: 20,
    backgroundColor: AppColors.green,
  },
  checkButtonText: {
    fontSize: 20,
    alignItems: "center",
  },
});
