import React, { useEffect, useState } from "react";
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
import { AppColors } from "../../../types/colors";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse } from "../../../types/apimodels";
import { ApiResponseCode } from "../../../types/enum";
import { useAppNavigation } from "../../../navigation/AppNavigation";
import { useUserStorage } from "../../../hook/UserStorageHooks";

const NotificationSettingScreen: React.FC = () => {
  const [emailNotification, setEmailNotification] = useState(true);
  const [pushNotification, setPushNotification] = useState(true);
  const [isLoading, setIsLoading] = useState(false);
  const navigator = useAppNavigation();
  const userStorage = useUserStorage();

  async function saveSettings() {
    setIsLoading(true);
    const response = await apiClient.put<ApiResponse>(
      "user/notification/setting"
    );
    if (response.data.code === ApiResponseCode.OK) {
      setIsLoading(false);
      navigator.goBack();
    } else {
      alert(response.data.data as string[][0]);
    }
  }

  useEffect(() => {
    async function load() {
      const user = await userStorage.getInfo();
      if (user) {
        setEmailNotification(user?.isAllowEmail);
        setPushNotification(user?.isAllowNotification);
      }
    }
    load();
  }, []);

  return (
    <ScrollView style={styles.container}>
      <View style={styles.settingContainer}>
        <View style={styles.settingHeader}>
          <View style={styles.settingLabel}>
            <Ionicons name="mail" size={24} color="#3b82f6" />
            <Text style={styles.settingText}>Thông báo qua Email</Text>
          </View>
          <Switch
            value={emailNotification}
            onValueChange={setEmailNotification}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={emailNotification ? "#fff" : "#f4f3f4"}
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
            value={pushNotification}
            onValueChange={setPushNotification}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={pushNotification ? "#fff" : "#f4f3f4"}
          />
        </View>
      </View>

      {isLoading ? (
        <ActivityIndicator size={"large"} color={AppColors.green} />
      ) : (
        <Pressable style={styles.saveButton} onPress={saveSettings}>
          <Text style={styles.saveButtonText}>Lưu Cài Đặt</Text>
        </Pressable>
      )}
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
