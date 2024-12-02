import React, { useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  Switch,
  Pressable,
  ScrollView,
  ActivityIndicator,
} from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import apiClient from "../../config/AxiosConfig";
import { ApiResponse } from "../../types/apimodels";
import { ApiResponseCode } from "../../types/enum";
import BottomButton from "../common/BottomButton";

const NotificationSettingScreen: React.FC = () => {
  const [emailNotifications, setEmailNotifications] = useState(true);
  const [pushNotifications, setPushNotifications] = useState(true);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState("");

  async function saveSettings() {
    setIsLoading(true);
    const response = await apiClient.put<ApiResponse>("user/notification/setting");
    if (response.data.code === ApiResponseCode.OK) {
      setMessage("Lưu cài đặt thành công");
      setIsLoading(false);
    } else {
      setMessage(response.data.data as string[][0])
    }
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.settingContainer}>
        <View style={styles.settingHeader}>
          <View style={styles.settingLabel}>
            <Ionicons name="mail" size={24} color="#3b82f6" />
            <Text style={styles.settingText}>Thông báo qua Email</Text>
          </View>
          <Switch
            value={emailNotifications}
            onValueChange={setEmailNotifications}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={emailNotifications ? "#fff" : "#f4f3f4"}
          />
        </View>
      </View>

      <View style={styles.settingContainer}>
        <View style={styles.settingHeader}>
          <View style={styles.settingLabel}>
            <Ionicons name="notifications" size={24} color="#3b82f6" />
            <Text style={styles.settingText}>Thông báo đẩy</Text>
          </View>
          <Switch
            value={pushNotifications}
            onValueChange={setPushNotifications}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={pushNotifications ? "#fff" : "#f4f3f4"}
          />
        </View>
      </View>

      {isLoading ? <ActivityIndicator size={"large"} color={AppColors.green} /> : <Pressable style={styles.saveButton} onPress={saveSettings}>
        <Text style={styles.saveButtonText}>Lưu Cài Đặt</Text>
      </Pressable>}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  settingContainer: {
    backgroundColor: AppColors.light,
    borderRadius: 8,
    padding: 16,
    marginBottom: 16,
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.23,
    shadowRadius: 2.62,
    elevation: 4,
  },
  settingHeader: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 8,
  },
  settingLabel: {
    flexDirection: "row",
    alignItems: "center",
  },
  settingText: {
    marginLeft: 8,
    fontSize: 16,
    fontWeight: "500",
    color: AppColors.darkGreen,
  },
  timePickerContainer: {
    marginTop: 8,
  },
  timePickerLabel: {
    fontSize: 14,
    color: AppColors.grayDark,
    marginBottom: 4,
  },
  timePicker: {
    borderWidth: 1,
    borderColor: AppColors.green,
    borderRadius: 4,
    padding: 8,
    fontSize: 16,
  },
  saveButton: {
    backgroundColor: AppColors.green,
    padding: 16,
    borderRadius: 8,
    alignItems: "center",
    marginTop: 24,
  },
  saveButtonText: {
    color: AppColors.white,
    fontSize: 18,
    fontWeight: "600",
  },
});

export default NotificationSettingScreen;