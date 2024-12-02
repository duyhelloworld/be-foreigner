import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import { useAppNavigation } from "../../navigation/AppNavigation";
import useAuthStorage from "../../hook/AuthStorageHooks";
import apiClient from "../../config/AxiosConfig";
import { ApiResponse } from "../../types/apimodels";
import { ApiResponseCode } from "../../types/enum";
import { AppColors } from "../../types/colors";

const LogoutScreen = () => {
  const navigator = useAppNavigation();
  const authStorage = useAuthStorage();

  const [error, setError] = useState<string[]>();

  useEffect(() => {
    const signOut = async () => {
      const response = await apiClient.put<ApiResponse>("auth/sign-out");
      if (response.data.code === ApiResponseCode.OK) {
        await authStorage.removeTokens();
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
