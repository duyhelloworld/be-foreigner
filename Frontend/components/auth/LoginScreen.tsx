import React, { useState } from "react";
import {
  View,
  Text,
  TextInput,
  Pressable,
  StyleSheet,
  Image,
  KeyboardAvoidingView,
  ScrollView,
} from "react-native";
import googleIcon from "../../assets/google-icon.png";
import facebookIcon from "../../assets/facebook-icon.png";
import appleIcon from "../../assets/apple-icon.png";
import { AppColors } from "../../types/Colors";
import { useAppNavigation } from "../../hook/AppNavigationHooks";
import AppIconView from "./AppIconView";
import SubmitButtonView from "./SubmitButtonView";
import InputTextView from "./InputTextView";

interface LoginScreenProp {}

const LoginScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const navigator = useAppNavigation();

  const validateForm = () => {
    let isValid = true;
    if (!username) {
      setUsernameError("Tài khoản không được để trống");
      isValid = false;
    } else {
      setUsernameError("");
    }
    if (!password) {
      setPasswordError("Mật khẩu không được để trống");
      isValid = false;
    } else {
      setPasswordError("");
    }
    return isValid;
  };

  function handleLogin() {
    if (validateForm()) {
      // Proceed with login
      console.log("Login with", username, password);
    }
  }

  function handleSignup() {
    navigator.navigate("AuthNavigator", { screen: "SignupScreen" });
  }

  return (
    <KeyboardAvoidingView style={styles.keyboardContainer} behavior="padding">
      <ScrollView
        contentContainerStyle={styles.container}
        keyboardShouldPersistTaps="handled"
      >
        <AppIconView />

        <InputTextView
          text="Tài khoản"
          value={username}
          setValue={setUsername}
          error={usernameError}
        />
        <InputTextView
          text="Mật khẩu"
          value={password}
          setValue={setPassword}
          error={passwordError}
          secure
        />
        <View style={styles.loginForm}>
          <SubmitButtonView title="Đăng nhập" onPress={handleLogin} />
        </View>

        <View style={styles.oauthContainer}>
          <Text style={styles.oauthText}>Hoặc đăng nhập với:</Text>
          <View style={styles.oauthButtons}>
            <Pressable style={styles.oauthButton}>
              <Image source={googleIcon} style={styles.oauthIcon} />
              <Text style={styles.oauthButtonText}>Google</Text>
            </Pressable>
            <Pressable style={styles.oauthButton}>
              <Image source={facebookIcon} style={styles.oauthIcon} />
              <Text style={styles.oauthButtonText}>Facebook</Text>
            </Pressable>
            <Pressable style={styles.oauthButton}>
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
  loginForm: {
    marginVertical: 20,
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
    backgroundColor: AppColors.lightGray,
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
