import React, { useState } from "react";
import {
  Modal,
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  Dimensions,
  ScrollView,
  Pressable,
} from "react-native";
import { UserLevel } from "../../types/enum";
import { AppColors } from "../../types/colors";
import { Ionicons } from "@expo/vector-icons";

const { width } = Dimensions.get("window");
const MODAL_SIZE = width * 0.9;

interface UserLevelPickerModalProps {
  isVisible: boolean;
  onChooseLevel: (level: UserLevel) => void;
  onClose: () => void;
}

const UserLevelPickerModal: React.FC<UserLevelPickerModalProps> = ({
  isVisible = false,
  onChooseLevel,
  onClose,
}) => {
  const [selectedLevel, setSelectedLevel] = useState<UserLevel | null>(null);

  const handleSelectLevel = (level: UserLevel) => {
    setSelectedLevel(level);
  };

  const chooseLevel = () => {
    if (selectedLevel) {
      onChooseLevel(selectedLevel);
    }
  };

  return (
    <Modal transparent visible={isVisible} animationType="fade">
      <View style={styles.container}>
        <View style={styles.modal}>
          <Ionicons
            name="close"
            color="white"
            onPress={onClose}
            size={20}
            style={styles.closeButton}
          />
          <Text style={styles.title}>Chọn trình độ hiện tại của bạn</Text>
          <View style={styles.scrollViewContainer}>
            <ScrollView
              contentContainerStyle={styles.levelContainer}
              showsVerticalScrollIndicator={true}
              style={styles.scrollView}
              scrollIndicatorInsets={{ right: 2 }}
            >
              {Object.values(UserLevel).map((level) => (
                <TouchableOpacity
                  key={level}
                  style={[
                    styles.levelButton,
                    selectedLevel === level && styles.selectedLevel,
                  ]}
                  onPress={() => handleSelectLevel(level)}
                >
                  <Text
                    style={[
                      styles.levelButtonText,
                      selectedLevel === level && styles.selectedLevelText,
                    ]}
                  >
                    {level}
                  </Text>
                </TouchableOpacity>
              ))}
            </ScrollView>
          </View>
          <Pressable
            onPress={chooseLevel}
            style={({ pressed }) => [
              styles.chooseButton,
              selectedLevel === null
                ? { backgroundColor: AppColors.lightGray }
                : pressed
                ? { backgroundColor: AppColors.gray }
                : null,
            ]}
          >
            <Text style={styles.chooseButtonText}>Chọn</Text>
          </Pressable>
        </View>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: AppColors.light,
  },
  modal: {
    width: MODAL_SIZE,
    height: MODAL_SIZE,
    backgroundColor: "white",
    borderRadius: 10,
    padding: 20,
    alignItems: "center",
    justifyContent: "flex-start",
  },
  closeButton: {
    position: "absolute",
    top: -5,
    backgroundColor: AppColors.red,
    right: -5,
    borderColor: AppColors.black,
    borderWidth: 1,
    padding: 2,
    borderRadius: 5,
  },
  title: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 20,
  },
  scrollView: {
    width: "100%",
    paddingRight: 10,
  },
  levelContainer: {
    alignItems: "center",
    width: "100%",
  },
  levelButton: {
    width: "100%",
    padding: 10,
    marginVertical: 5,
    borderRadius: 5,
    borderWidth: 1,
    borderColor: "#ccc",
  },
  selectedLevel: {
    backgroundColor: AppColors.green,
    borderColor: AppColors.darkGreen,
  },
  levelButtonText: {
    textAlign: "center",
  },
  selectedLevelText: {
    color: "white",
  },
  scrollViewContainer: {
    flex: 1,
    width: "100%",
    borderRadius: 10,
    overflow: "hidden",
  },
  chooseButton: {
    paddingHorizontal: 15,
    paddingVertical: 10,
    backgroundColor: AppColors.darkGreen,
    borderRadius: 5,
    width: "50%",
  },
  chooseButtonText: {
    color: "white",
    fontSize: 18,
    textAlign: "center",
  },
});

export default UserLevelPickerModal;
