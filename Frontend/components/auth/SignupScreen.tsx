import React, { useState } from "react";
import {
  View,
  Text,
  Pressable,
  StyleSheet,
  Image,
  KeyboardAvoidingView,
  ScrollView,
} from "react-native";
import googleIcon from "../../assets/google-icon.png";
import facebookIcon from "../../assets/facebook-icon.png";
import appleIcon from "../../assets/apple-icon.png";
import { AppColors } from "../../types/colors";
import { useAppNavigation } from "../../navigation/AppNavigationHooks";
import AppIconView from "./AppIconView";
import SubmitButtonView from "./SubmitButtonView";
import InputTextView from "./InputTextView";
import ImagePickerView from "../common/ImagePickerView";
import {
  ImagePickerAsset,
  MediaTypeOptions,
  launchImageLibraryAsync,
  requestMediaLibraryPermissionsAsync,
} from "expo-image-picker";
import { ApiResponse, Auth } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode, ContentType } from "../../types/enum";
import useAuthStorage from "../../storage/AuthStorageHooks";

const SignupScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [fullname, setFullname] = useState("");
  const [avatar, setAvatar] = useState<ImagePickerAsset>();

  const [usernameError, setUsernameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const navigator = useAppNavigation();
  const authStorage = useAuthStorage();

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

  async function handleSignup() {
    if (validateForm()) {
      const formData = new FormData();
      formData.append("username", username);
      formData.append("password", password);
      formData.append("email", email);
      formData.append("fullname", fullname);

      if (avatar) {
        let avatarForm : FormDataValue = {
          uri: avatar.uri,
          name: avatar.fileName ?? avatar.uri.split(".")[1],
          type: "image/*"
        }
        formData.append("avatar", avatarForm);
      }

      const response = await apiClient.postForm<ApiResponse>(
        "auth/sign-up",
        formData,
        {
          headers: {
            "Content-Type": ContentType.FORM_DATA,
          },
        }
      );
      if (response.data.code === ApiResponseCode.OK) {
        await authStorage.saveTokens(response.data.data as Auth);
        navigator.navigate("AuthNavigator", { screen: "SetupScreen" });
      } else {
        console.log("Signup error", response.data);
      }
    }
  }

  function handleLogin() {
    navigator.navigate("AuthNavigator", { screen: "LoginScreen" });
  }

  async function handleSelectAvatar() {
    const hasLibraryPermission = await requestMediaLibraryPermissionsAsync();
    if (hasLibraryPermission) {
      let result = await launchImageLibraryAsync({
        allowsEditing: true,
        quality: 1,
        mediaTypes: MediaTypeOptions.Images,
      });
      if (!result.canceled) {
        setAvatar(result.assets[0]);
      }
    }
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
            placeholder="Tài khoản (*)"
            value={username}
            setValue={setUsername}
            error={usernameError}
          />
          <InputTextView
            placeholder="Email (*)"
            value={email}
            setValue={setEmail}
            error={emailError}
          />
          <InputTextView
            placeholder="Họ và tên"
            value={fullname}
            setValue={setFullname}
          />
          <InputTextView
            placeholder="Mật khẩu (*)"
            value={password}
            secure
            setValue={setPassword}
            error={passwordError}
          />
          <View style={styles.avatarContainer}>
            <ImagePickerView
              label="Chọn ảnh đại diện"
              onPress={handleSelectAvatar}
              icon="images"
            />
            {avatar && (
              <Image
                source={{ uri: avatar.uri }}
                style={styles.avatarPreview}
              />
            )}
          </View>
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
  signupForm: {
    marginVertical: 20,
  },
  avatarContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginVertical: 10,
    justifyContent: "center",
  },
  avatarPreview: {
    width: 70,
    height: 70,
    borderRadius: 30,
    marginLeft: 10,
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
