import React, { useState } from "react";
import {
  View,
  Text,
  Pressable,
  StyleSheet,
  Image,
  KeyboardAvoidingView,
  ScrollView,
  ActivityIndicator,
} from "react-native";
import googleIcon from "../../assets/google-icon.png";
import facebookIcon from "../../assets/facebook-icon.png";
import appleIcon from "../../assets/apple-icon.png";
import { AppColors } from "../../types/colors";
import { useAppNavigation } from "../../navigation/AppNavigation";
import AppIconView from "./AppIconView";
import InputTextView from "./InputTextView";
import apiClient from "../../config/AxiosConfig";
import { ApiResponse, Auth } from "../../types/apimodels";
import { ApiResponseCode } from "../../types/enum";
import useAuthStorage from "../../hook/AuthStorageHooks";
import SubmitButton from "../common/SubmitButton";

const LoginScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [apiError, setApiError] = useState<string[]>([]);
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const navigator = useAppNavigation();
  const authStorage = useAuthStorage();

  const validateForm = () => {
    let isValid = true;
    if (!username) {
      setUsernameError("Tài khoản không được để trống");
      isValid = false;
    } else if (!username.match("^[a-zA-Z0-9]+$")) {
      setUsernameError(
        "Tên tài khoản không hợp lệ. Tài khoản hợp lệ là tên chỉ gồm các chữ cái và số."
      );
      isValid = false;
    } else {
      setUsernameError("");
    }

    if (!password) {
      setPasswordError("Mật khẩu không được để trống");
      isValid = false;
    } else if (!password.match("^[a-zA-Z0-9]{8}$")) {
      setPasswordError(
        "Mật khẩu không hợp lệ. Mật khẩu hợp lệ gồm 8 kí tự không bao gồm dấu cách"
      );
      isValid = false;
    } else {
      setPasswordError("");
    }
    return isValid;
  };

  async function handleLogin() {
    if (validateForm()) {
      setIsLoading(true);
      const response = await apiClient.post<ApiResponse>("auth/sign-in", {
        username: username.trim(),
        password: password.trim(),
      });
      if (response.data.code === ApiResponseCode.OK) {
        await authStorage.saveTokens(response.data.data as Auth);
        setUsernameError("");
        setPasswordError("");
        navigator.navigate("AuthNavigator", { screen: "SetupScreen" });
      } else {
        setApiError(response.data.data as string[]);
      }
      setIsLoading(false);
    }
  }

  function handleSignup() {
    setUsernameError("");
    setPasswordError("");
    navigator.navigate("AuthNavigator", { screen: "SignupScreen" });
  }

  function unhandledLogin() {
    alert("Chức năng này sẽ sớm ra mắt!");
  }

  function handleGoogleSignIn() {}

  return (
    <KeyboardAvoidingView style={styles.keyboardContainer} behavior="padding">
      <ScrollView
        contentContainerStyle={styles.container}
        keyboardShouldPersistTaps="handled"
      >
        <AppIconView />
        <InputTextView
          placeholder="Tài khoản"
          value={username}
          setValue={setUsername}
          error={usernameError}
          inputType="name-phone-pad"
        />
        <InputTextView
          placeholder="Mật khẩu"
          value={password}
          setValue={setPassword}
          error={passwordError}
          showPassword={showPassword}
          toggleShowPassword={() => setShowPassword(!showPassword)}
          secure
        />

        {apiError ? (
          <Text style={styles.errorText}>{apiError.join(" ")}</Text>
        ) : null}

        {isLoading ? (
          <ActivityIndicator size="large" color={AppColors.green} />
        ) : (
          <SubmitButton title="Đăng nhập" onPress={handleLogin} />
        )}

        <View style={styles.oauthContainer}>
          <Text style={styles.oauthText}>Hoặc đăng nhập với:</Text>
          <View style={styles.oauthButtons}>
            <Pressable style={styles.oauthButton} onPress={handleGoogleSignIn}>
              <Image source={googleIcon} style={styles.oauthIcon} />
              <Text style={styles.oauthButtonText}>Google</Text>
            </Pressable>
            <Pressable style={styles.oauthButton} onPress={unhandledLogin}>
              <Image source={facebookIcon} style={styles.oauthIcon} />
              <Text style={styles.oauthButtonText}>Facebook</Text>
            </Pressable>
            <Pressable style={styles.oauthButton} onPress={unhandledLogin}>
              <Image source={appleIcon} style={styles.oauthIcon} />
              <Text style={styles.oauthButtonText}>Apple</Text>
            </Pressable>
          </View>
        </View>

        <View style={styles.signupContainer}>
          <Text style={styles.signupText}>
            Đã có tài khoản?{" "}
            <Text style={styles.signupLink} onPress={handleSignup}>
              Đăng ký ngay
            </Text>
          </Text>
        </View>
      </ScrollView>
    </KeyboardAvoidingView>
  );
};

export default LoginScreen;

const styles = StyleSheet.create({
  keyboardContainer: {
    flex: 1,
  },
  container: {
    flexGrow: 1,
    padding: 20,
    justifyContent: "center",
    backgroundColor: AppColors.white,
  },
  errorText: {
    color: AppColors.red,
    fontSize: 12,
    marginTop: 5,
  },
  oauthContainer: {
    alignItems: "center",
    marginVertical: 20,
  },
  oauthText: {
    fontSize: 16,
    color: AppColors.grayDark,
    marginBottom: 10,
  },
  oauthButtons: {
    flexDirection: "row",
    justifyContent: "space-around",
    width: "100%",
  },
  oauthButton: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: AppColors.light,
    borderRadius: 8,
    paddingVertical: 10,
    paddingHorizontal: 15,
    marginHorizontal: 5,
  },
  oauthIcon: {
    width: 20,
    height: 20,
    marginRight: 8,
  },
  oauthButtonText: {
    fontSize: 14,
    color: AppColors.grayDark,
  },
  signupContainer: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    marginTop: 20,
  },
  signupText: {
    fontSize: 14,
    color: AppColors.grayDark,
  },
  signupLink: {
    fontSize: 14,
    color: AppColors.green,
    fontWeight: "bold",
  },
});
