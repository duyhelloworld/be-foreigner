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
import * as ImagePicker from "expo-image-picker";
import { ApiResponse, Auth } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode, UserLevel } from "../../types/enum";
import useAuthStorage from "../../hook/AuthStorageHooks";
import SubmitButton from "../common/SubmitButton";
import UserLevelPickerModal from "./UserLevelPickerScreen";
import { Feather, Ionicons, MaterialCommunityIcons } from "@expo/vector-icons";

const SignupScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [fullname, setFullname] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [avatar, setAvatar] = useState<ImagePicker.ImagePickerAsset>();
  const [isLevelPickerVisisble, setIsLevelPickerVisisble] = useState(false);
  const [level, setLevel] = useState<UserLevel>();
  const [usernameError, setUsernameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");
  const [levelPickerError, setLevelPickerError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

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
    } else if (
      !password.match(/^(?!.*\s)[A-Za-z0-9!@#$%^&*(),.?":{}|<>]{8,}$/)
    ) {
      setPasswordError(
        "Mật khẩu không hợp lệ. Mật khẩu hợp lệ gồm 8 kí tự không bao gồm dấu cách"
      );
      isValid = false;
    } else {
      setPasswordError("");
    }

    if (!confirmPassword) {
      setConfirmPasswordError("Thiếu mật khẩu xác nhận");
      isValid = false;
    } else if (JSON.stringify(password) !== JSON.stringify(confirmPassword)) {
      setConfirmPasswordError("Mật khẩu xác nhận không trùng");
      isValid = false;
    } else {
      setConfirmPasswordError("");
    }

    if (!email) {
      setEmailError("Email không được bỏ trống");
      isValid = false;
    } else if (!email.match(/^[\S]{8,}$/)) {
      setEmailError("Email không hợp lệ. Hãy điền 1 email hợp lệ");
      isValid = false;
    } else {
      setEmailError("");
    }

    if (!level) {
      setLevelPickerError("Bạn chưa chọn trình độ của mình");
      isValid = false;
    } else {
      setLevelPickerError("");
    }

    return isValid;
  };

  async function handleSignup() {
    if (validateForm()) {
      setIsLoading(true);
      const response = await apiClient.post<ApiResponse>("auth/register", {
        username,
        password,
        email,
        fullname,
        avatar: avatar?.base64,
        avatarFilename: avatar?.fileName,
        level: Object.keys(UserLevel).find(
          (key) => UserLevel[key as keyof typeof UserLevel] === level
        ) as keyof typeof UserLevel | undefined,
      });
      if (response.data.code === ApiResponseCode.OK) {
        await authStorage.saveTokens(response.data.data as Auth);
        setIsLoading(false);
        setConfirmPasswordError("");
        setEmailError("");
        setLevelPickerError("");
        setUsernameError("");
        setPasswordError("");
        navigator.navigate("AuthNavigator", { screen: "SetupScreen" });
      } else {
        alert(response.data.data as string[]);
        setIsLoading(false);
      }
    }
  }

  function handleLogin() {
    setConfirmPasswordError("");
    setEmailError("");
    setLevelPickerError("");
    setUsernameError("");
    setPasswordError("");
    navigator.navigate("AuthNavigator", { screen: "LoginScreen" });
  }

  function handleChooseLevel(selectLevel: UserLevel) {
    setLevel(selectLevel);
    setIsLevelPickerVisisble(false);
  }

  async function handleSelectAvatar() {
    const isAllowed = await ImagePicker.getMediaLibraryPermissionsAsync();
    if (isAllowed.status !== ImagePicker.PermissionStatus.GRANTED) {
      await ImagePicker.requestMediaLibraryPermissionsAsync();
    } else {
      let result = await ImagePicker.launchImageLibraryAsync({
        allowsEditing: true,
        quality: 1,
        base64: true,
        aspect: [1, 1],
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
      });
      if (!result.canceled) {
        setAvatar(result.assets[0]);
      }
    }
  }

  function unhandledLogin() {
    alert("Chức năng này sẽ sớm ra mắt!");
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
            showPassword={showPassword}
            toggleShowPassword={() => setShowPassword(!showPassword)}
          />
          <InputTextView
            placeholder="Xác nhận mật khẩu (*)"
            value={confirmPassword}
            secure
            setValue={setConfirmPassword}
            error={confirmPasswordError}
            showPassword={showPassword}
            toggleShowPassword={() => setShowPassword(!showPassword)}
          />
          <View style={styles.avatarContainer}>
            <Pressable
              style={[
                styles.chooseLevelButton,
                { backgroundColor: avatar ? AppColors.green : AppColors.gray },
              ]}
              onPress={handleSelectAvatar}
            >
              <Ionicons
                name="images"
                size={24}
                color={AppColors.light}
                style={styles.chooseLevelButtonIcon}
              />
              <Text style={styles.chooseLevelButtonText}>
                {avatar ? "Đã chọn ảnh đại diện" : "Chọn ảnh đại diện"}
              </Text>
            </Pressable>
            <Pressable
              style={[
                styles.chooseLevelButton,
                { backgroundColor: level ? AppColors.green : AppColors.gray },
              ]}
              onPress={() => setIsLevelPickerVisisble(true)}
            >
              <MaterialCommunityIcons
                name="crown"
                size={24}
                color={AppColors.light}
                style={styles.chooseLevelButtonIcon}
              />
              <Text style={styles.chooseLevelButtonText}>
                {level ? level : "Chọn trình độ"}
              </Text>
            </Pressable>
            <UserLevelPickerModal
              isVisible={isLevelPickerVisisble}
              onChooseLevel={handleChooseLevel}
              onClose={() => setIsLevelPickerVisisble(false)}
            />
            {levelPickerError ? (
              <Text style={styles.errorText}>{levelPickerError}</Text>
            ) : null}
          </View>
          {isLoading ? (
            <ActivityIndicator size="large" color={"green"} />
          ) : (
            <SubmitButton title="Đăng kí" onPress={handleSignup} />
          )}
        </View>

        <View style={styles.oauthContainer}>
          <Text style={styles.oauthText}>Hoặc đăng kí với:</Text>
          <View style={styles.oauthButtons}>
            <Pressable style={styles.oauthButton} onPress={unhandledLogin}>
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

        <View style={styles.loginContainer}>
          <Text style={styles.loginText}>
            Đã có tài khoản?{" "}
            <Text style={styles.loginLink} onPress={handleLogin}>
              Đăng nhập ngay
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
    marginVertical: 5,
    alignItems: "center",
  },
  chooseLevelButton: {
    paddingHorizontal: 15,
    paddingVertical: 12,
    borderRadius: 10,
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
    marginVertical: 10,
    width: "80%",
  },
  chooseLevelButtonText: {
    color: "white",
    fontWeight: "800",
    textAlign: "center",
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
  errorText: {
    color: "red",
  },
  chooseLevelButtonIcon: {
    marginRight: 10,
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
