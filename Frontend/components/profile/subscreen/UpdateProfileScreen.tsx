import React, { useEffect, useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  Image,
  ScrollView,
  Pressable,
  ActivityIndicator,
} from "react-native";
import { AppColors } from "../../../types/colors";
import { ApiResponse, UserInfo } from "../../../types/apimodels";
import { MediaTypeOptions, launchImageLibraryAsync } from "expo-image-picker";
import InputTextView from "../../auth/InputTextView";
import { useUserStorage } from "../../../hook/UserStorageHooks";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponseCode, ContentType } from "../../../types/enum";
import { Ionicons } from "@expo/vector-icons";

export default function UpdateProfileScreen() {
  const [user, setUser] = useState<UserInfo>();
  const [fullname, setFullname] = useState("");
  const [avatar, setAvatar] = useState<string | undefined>();
  const [isChanged, setIsChanged] = useState(false);
  const [messages, setMessages] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isSuccess, setIsSuccess] = useState<boolean>();

  const userStorage = useUserStorage();

  useEffect(() => {
    async function loadUser() {
      const current = await userStorage.getInfo();
      if (current) {
        setUser(current);
        setFullname(current.fullname);
      }
    }
    loadUser();
  }, []);

  async function handleUpdateAvatar() {
    let result = await launchImageLibraryAsync({
      mediaTypes: MediaTypeOptions.Images,
      allowsEditing: true,
      base64: true,
      aspect: [1, 1],
      quality: 1,
    });

    if (!result.canceled) {
      setAvatar(result.assets[0].base64 ?? undefined);
      setIsChanged(true);
    }
  }

  function handleUpdateFullname(text: string) {
    setFullname(text);
    setIsChanged(text !== user?.fullname);
  }

  async function handleSaveChanges() {
    setIsLoading(true);
    let data = {
      fullname: "",
      avatar: "",
    };
    if (fullname) {
      data.fullname = fullname.trim();
    }
    if (avatar) {
      data.avatar = avatar;
    }
    const response = await apiClient.putForm<ApiResponse>("user/my-info", data);
    setIsLoading(false);
    if (response.data.code === ApiResponseCode.OK) {
      setIsChanged(false);
      setUser(response.data.data as UserInfo);
      setMessages(["Cập nhật thành công!"]);
      setIsSuccess(true);
    } else {
      setIsSuccess(false);
      setMessages(response.data.data as string[]);
    }

    const timeoutId = setTimeout(() => {
      setMessages([]);
    }, 3000);

    return () => clearTimeout(timeoutId);
  }

  return (
    <ScrollView>
      <View style={styles.avatarContainer}>
        <Image
          source={
            user
              ? { uri: user.avatar }
              : require("../../../assets/default-avatar.jpg")
          }
          style={styles.avatar}
        />
        <Ionicons
          name="camera"
          size={24}
          color={AppColors.white}
          style={styles.editAvatarButton}
          onPress={handleUpdateAvatar}
        />
        <Text style={styles.username}>{user?.username}</Text>
      </View>

      <View style={styles.textInfoContainer}>
        <Text style={styles.infoTitle}>Email</Text>
        <InputTextView placeholder={user?.email} enable={false} />
      </View>

      <View style={styles.textInfoContainer}>
        <Text style={styles.infoTitle}>Tài khoản</Text>
        <InputTextView placeholder={user?.username} enable={false} />
      </View>

      <View style={styles.textInfoContainer}>
        <Text style={styles.infoTitle}>Họ tên</Text>
        <InputTextView
          placeholder={fullname}
          value={fullname}
          setValue={handleUpdateFullname}
        />
      </View>

      <View style={styles.providerContainer}>
        <Ionicons name="logo-facebook" size={25} style={styles.providerIcon} />
        <Ionicons name="logo-google" size={25} style={styles.providerIcon} />
        <Ionicons name="logo-github" size={25} style={styles.providerIcon} />
        <Ionicons name="logo-apple" size={25} style={styles.providerIcon} />
      </View>

      <Text
        style={[
          styles.message,
          isSuccess ? styles.okMessage : styles.errorMessage,
        ]}
      >
        {messages.join("")}
      </Text>

      {isLoading ? (
        <ActivityIndicator />
      ) : (
        <Pressable
          style={[styles.saveButton, !isChanged && styles.saveButtonDisabled]}
          onPress={handleSaveChanges}
          disabled={!isChanged}
        >
          <Text style={styles.saveButtonText}>Lưu thay đổi</Text>
        </Pressable>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  avatarContainer: {
    marginTop: 20,
    alignItems: "center",
  },
  avatar: {
    width: 200,
    height: 200,
    borderRadius: 200,
  },
  editAvatarButton: {
    position: "absolute",
    right: 90,
    bottom: 50,
    backgroundColor: AppColors.gray,
    color: AppColors.black,
    borderRadius: 20,
    padding: 8,
  },
  username: {
    fontSize: 15,
    fontWeight: "700",
    fontFamily: "serif",
    color: AppColors.blue,
    padding: 10,
  },
  textInfoContainer: {
    marginHorizontal: 20,
  },
  infoTitle: {
    fontWeight: "700",
  },
  saveButton: {
    backgroundColor: AppColors.blue,
    padding: 10,
    borderRadius: 5,
    alignItems: "center",
    marginBottom: 20,
    marginHorizontal: 50,
  },
  saveButtonDisabled: {
    backgroundColor: AppColors.gray,
  },
  saveButtonText: {
    color: AppColors.white,
    fontWeight: "bold",
  },
  providerContainer: {
    flexDirection: "row",
    justifyContent: "center",
  },
  providerIcon: {
    padding: 5,
  },
  message: {
    fontSize: 20,
  },
  errorMessage: {
    color: AppColors.red,
  },
  okMessage: {
    color: AppColors.green,
  },
});
