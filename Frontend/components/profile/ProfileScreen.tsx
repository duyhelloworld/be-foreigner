import React, { useCallback, useEffect, useState } from "react";
import {
  Image,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from "react-native";
import ProfileScreenButton from "./ProfileScreenButton";
import { AppColors } from "../../types/colors";
import { UserInfo } from "../../types/apimodels";
import { useUserStorage } from "../../storage/UserStorageHooks";
import ProfileFooterView from "./ProfileFooterView";

const ProfileScreen = () => {
  const [user, setUser] = useState<UserInfo>();
  const userStorage = useUserStorage();

  useEffect(() => {
    async function loadUser() {
      const current = await userStorage.getInfo();
      setUser(current);
    }
    loadUser();
  }, []);

  return (
    <View style={styles.container}>
      <ScrollView style={styles.scrollView}>
        <View style={styles.topContainer}>
          <Image
            source={
              { uri: user?.avatar } ??
              require("../../assets/default-avatar.jpg")
            }
            style={styles.avatar}
          />
          <Text style={styles.username}>{user?.username}</Text>
        </View>
        <View style={styles.bottomContainer}>
          <ProfileScreenButton
            label="Nâng cấp tài khoản "
            targetScreen="UpgradePlanScreen"
            backgroundColor={AppColors.yellow}
            textColor={AppColors.black}
            icon="crown"
            iconColor={AppColors.yellow}
            hightlightText
            showBadge
          />
          <ProfileScreenButton
            label="Lịch sử học bài"
            targetScreen="ChangePasswordScreen"
          />
          <ProfileScreenButton
            label="Thống kê kết quả học"
            targetScreen="ChangePasswordScreen"
          />
          <ProfileScreenButton
            label="Cập nhật thông tin"
            targetScreen="UpdateProfileScreen"
          />
          <ProfileScreenButton
            label="Cài đặt thông báo"
            targetScreen="NotificationSetttingScreen"
          />
          <ProfileScreenButton
            label="Đăng xuất"
            targetScreen="LogoutScreen"
            backgroundColor={AppColors.red}
            textColor={AppColors.white}
            hightlightText
          />
        </View>
        <ProfileFooterView
          companyName="Duy Nến Dev"
          email="duy0184466@huce.edu.vn"
          releaseYear={2024}
          facebookUrl="https://facebook.com/duyhelloworld"
          githubUrl="https://github.com/duyhelloworld"
        />
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  scrollView: {
    flex: 1,
  },
  topContainer: {
    backgroundColor: AppColors.green,
    flexDirection: "row",
    alignItems: "flex-end",
    padding: 10,
  },
  bottomContainer: {
    flexDirection: "column",
    paddingHorizontal: 20,
    paddingVertical: 10,
    backgroundColor: AppColors.white,
  },
  username: {
    fontWeight: "700",
    color: AppColors.white,
    maxWidth: "40%",
    fontSize: 23,
  },
  avatar: {
    width: 200,
    height: 200,
    borderRadius: 100,
    maxWidth: "60%",
  },
});

export default ProfileScreen;
