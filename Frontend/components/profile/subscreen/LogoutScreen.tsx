import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import { useAppNavigation } from "../../../navigation/AppNavigation";
import useAuthStorage from "../../../hook/AuthStorageHooks";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponse } from "../../../types/apimodels";
import { ApiResponseCode } from "../../../types/enum";
import { AppColors } from "../../../types/colors";
import { useUserStorage } from "../../../hook/UserStorageHooks";

const LogoutScreen = () => {
  const navigator = useAppNavigation();
  const authStorage = useAuthStorage();
  const userStorage = useUserStorage();

  const [error, setError] = useState<string[]>();

  useEffect(() => {
    const signOut = async () => {
      const refreshToken = await authStorage.getRefreshToken();
      const response = await apiClient.put<ApiResponse>("auth/sign-out?token=" + refreshToken);
      if (response.data.code === ApiResponseCode.OK) {
        await authStorage.removeTokens();
        await userStorage.clear();
        navigator.navigate("AuthNavigator", {screen: "LoginScreen"});
      } else {
        setError(response.data.data as string[]);
      }
    };
    signOut();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.message}>Đang đăng xuất...</Text>
      {error ? <Text style={styles.error}>{error}</Text> : null}
    </View>
  );
};

export default LogoutScreen;

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
    flex: 1,
  },
  error: {
    color: AppColors.red,
  },
  message: {
    fontWeight: "heavy",
    color: AppColors.white
  },
});
