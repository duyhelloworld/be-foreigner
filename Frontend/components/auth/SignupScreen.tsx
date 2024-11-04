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
import appIcon from "../../assets/icon-transparent.png";
import googleIcon from "../../assets/google-icon.png";
import facebookIcon from "../../assets/facebook-icon.png";
import appleIcon from "../../assets/apple-icon.png";
import { AppColors } from "../../types/Colors";
import { useAppNavigation } from "../../hook/AppNavigationHooks";
import AppIconView from "./AppIconView";
import SubmitButtonView from "./SubmitButtonView";
import InputTextView from "./InputTextView";

const SignupScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [fullname, setFullname] = useState("");

  const [usernameError, setUsernameError] = useState("");
  const [emailError, setEmailError] = useState("");
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
    if (!email) {
      setEmailError("Email không được để trống");
      isValid = false;
    } else if (!email.includes("@")) {
      setEmailError("Email không hợp lệ");
      isValid = false;
    } else {
      setEmailError("");
    }
    return isValid;
  };

  function handleSignup() {
    if (validateForm()) {
      // Proceed with login
      console.log("singup with", username, password, email);
    }
  }

  function handleLogin() {
    navigator.navigate("AuthNavigator", { screen: "LoginScreen" });
  }

  return (
    <KeyboardAvoidingView style={styles.keyboardContainer} behavior="padding">
      <ScrollView
        contentContainerStyle={styles.container}
        keyboardShouldPersistTaps="handled"
      >
        <AppIconView />

        <View style={styles.signupForm}>
          <InputTextView
            text="Tài khoản"
            value={username}
            setValue={setUsername}
            error={usernameError}
          />
          <InputTextView
            text="Email"
            value={email}
            setValue={setEmail}
            error={emailError}
          />
          <InputTextView
            text="Họ và tên"
            value={fullname}
            setValue={setFullname}
          />

          <InputTextView
            text="Mật khẩu"
            value={password}
            secure
            setValue={setPassword}
            error={passwordError}
          />

          <SubmitButtonView title="Đăng kí" onPress={handleSignup} />
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

        <View style={styles.loginContainer}>
          <Text style={styles.loginText}>
            Chưa có tài khoản?{" "}
            <Text style={styles.loginLink} onPress={handleLogin}>
              Đăng ký ngay
            </Text>
          </Text>
        </View>
      </ScrollView>
    </KeyboardAvoidingView>
  );
};

export default SignupScreen;

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
  logoContainer: {
    alignItems: "center",
    marginBottom: 20,
  },
  imageBackground: {
    justifyContent: "center",
    alignItems: "center",
    marginBottom: "5%",
  },
  title: {
    fontSize: 30,
    color: AppColors.green,
    fontWeight: "bold",
    textShadowColor: AppColors.grayDark,
    textShadowOffset: { width: -1, height: 1 },
    textShadowRadius: 5,
  },
  signupForm: {
    marginVertical: 20,
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
  loginContainer: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    marginTop: 20,
  },
  loginText: {
    fontSize: 14,
    color: AppColors.grayDark,
  },
  loginLink: {
    fontSize: 14,
    color: AppColors.green,
    fontWeight: "bold",
  },
});
