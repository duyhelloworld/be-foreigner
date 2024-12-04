import React, { useState } from "react";
import {
  View,
  Text,
  Pressable,
  StyleSheet,
  ScrollView,
  ActivityIndicator,
} from "react-native";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse } from "../../../types/apimodels";
import { ApiResponseCode } from "../../../types/enum";
import InputTextView from "../../auth/InputTextView";
import { AppColors } from "../../../types/colors";

const ChangePasswordScreen = () => {
  const [oldPassword, setOldPassword] = useState<string>();
  const [newPassword, setNewPassword] = useState<string>();
  const [confirmPassword, setConfirmPassword] = useState<string>();
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [messages, setMessages] = useState<string[]>([]);
  const [isSuccess, setIsSuccess] = useState<boolean>();
  const [confirmPassError, setConfirmPassError] = useState("");

  const handleSubmit = async () => {
    if (newPassword !== confirmPassword) {
      setConfirmPassError("Mật khẩu xác nhận không trùng");
      const timeoutId = setTimeout(() => {
        setConfirmPassError("");
      }, 2000);
      return () => clearTimeout(timeoutId);
    }
    setIsLoading(true);
    const response = await apiClient.post<ApiResponse>("auth/change-pass", {
      oldPassword,
      newPassword,
    });

    if (response.data.code === ApiResponseCode.OK) {
      setIsSuccess(true);
      setMessages(["Cập nhật thành công"]);
    } else {
      setMessages(response.data.data as string[]);
      setIsSuccess(false);
    }
    setIsLoading(false);
    const timeoutId = setTimeout(() => {
      setMessages([]);
    }, 2000);
    return () => clearTimeout(timeoutId);
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Đổi mật khẩu</Text>
      <View style={styles.form}>
        <View style={styles.inputContainer}>
          <InputTextView
            secure
            value={oldPassword}
            setValue={setOldPassword}
            placeholder="Mật khẩu hiện tại"
          />
        </View>
        <View style={styles.inputContainer}>
          <InputTextView
            value={newPassword}
            setValue={setNewPassword}
            placeholder="Mật khẩu mới"
            secure
          />
        </View>
        <View style={styles.inputContainer}>
          <InputTextView
            value={confirmPassword}
            setValue={setConfirmPassword}
            placeholder="Xác nhận mật khẩu mới"
            error={confirmPassError}
            secure
          />
        </View>

        <Text style={isSuccess ? styles.ok : styles.error}>
          {messages.join("")}
        </Text>

        {isLoading ? (
          <ActivityIndicator color={AppColors.gray} />
        ) : (
          <Pressable style={styles.button} onPress={handleSubmit}>
            <Text style={styles.buttonText}>Lưu</Text>
          </Pressable>
        )}
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    justifyContent: "center",
    padding: 16,
    backgroundColor: "#f5f5f5",
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    textAlign: "center",
    marginBottom: 24,
    color: "#333",
  },
  form: {
    backgroundColor: AppColors.lightGreen,
    borderRadius: 8,
    padding: 16,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 3,
  },
  inputContainer: {
    marginBottom: 16,
  },
  button: {
    backgroundColor: AppColors.green,
    borderRadius: 4,
    paddingVertical: 12,
    alignItems: "center",
  },
  buttonText: {
    color: AppColors.white,
    fontSize: 16,
    fontWeight: "600",
  },
  error: {
    color: AppColors.red,
  },
  ok: {
    color: AppColors.green,
  },
});

export default ChangePasswordScreen;
