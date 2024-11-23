import React, { useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  Switch,
  TextInput,
  TouchableOpacity,
  ScrollView,
} from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";

const NotificationSettingScreen: React.FC = () => {
  const [emailNotifications, setEmailNotifications] = useState(false);
  const [pushNotifications, setPushNotifications] = useState(false);
  const [releaseNotifications, setReleaseNotifications] = useState(false);
  const [emailTime, setEmailTime] = useState("09:00");
  const [pushTime, setPushTime] = useState("12:00");

  const saveSettings = () => {
    console.log("Lưu cài đặt");
    // Thêm logic lưu cài đặt ở đây
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.settingContainer}>
        <View style={styles.settingHeader}>
          <View style={styles.settingLabel}>
            <Ionicons name="mail" size={24} color="#3b82f6" />
            <Text style={styles.settingText}>Thông Báo Qua Email</Text>
          </View>
          <Switch
            value={emailNotifications}
            onValueChange={setEmailNotifications}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={emailNotifications ? "#fff" : "#f4f3f4"}
          />
        </View>
        {emailNotifications && (
          <View style={styles.timePickerContainer}>
            <Text style={styles.timePickerLabel}>
              Thời Gian Thông Báo Hàng Ngày
            </Text>
            <TextInput
              style={styles.timePicker}
              value={emailTime}
              onChangeText={setEmailTime}
              placeholder="HH:MM"
              keyboardType="numbers-and-punctuation"
            />
          </View>
        )}
      </View>

      <View style={styles.settingContainer}>
        <View style={styles.settingHeader}>
          <View style={styles.settingLabel}>
            <Ionicons name="notifications" size={24} color="#3b82f6" />
            <Text style={styles.settingText}>Thông Báo Đẩy</Text>
          </View>
          <Switch
            value={pushNotifications}
            onValueChange={setPushNotifications}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={pushNotifications ? "#fff" : "#f4f3f4"}
          />
        </View>
        {pushNotifications && (
          <View style={styles.timePickerContainer}>
            <Text style={styles.timePickerLabel}>
              Thời Gian Thông Báo Hàng Ngày
            </Text>
            <TextInput
              style={styles.timePicker}
              value={pushTime}
              onChangeText={setPushTime}
              placeholder="HH:MM"
              keyboardType="numbers-and-punctuation"
            />
          </View>
        )}
      </View>

      <View style={styles.settingContainer}>
        <View style={styles.settingHeader}>
          <View style={styles.settingLabel}>
            <Ionicons name="megaphone" size={24} color="#3b82f6" />
            <Text style={styles.settingText}>
              Thông Báo Về Các Bản Phát Hành
            </Text>
          </View>
          <Switch
            value={releaseNotifications}
            onValueChange={setReleaseNotifications}
            trackColor={{ false: "#767577", true: "#3b82f6" }}
            thumbColor={releaseNotifications ? "#fff" : "#f4f3f4"}
          />
        </View>
      </View>

      <TouchableOpacity style={styles.saveButton} onPress={saveSettings}>
        <Text style={styles.saveButtonText}>Lưu Cài Đặt</Text>
      </TouchableOpacity>
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