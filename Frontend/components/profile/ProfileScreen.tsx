import React, { useEffect, useRef, useState } from "react";
import { Dimensions, Image, StyleSheet, Text, View } from "react-native";
import ProfileScreenButton from "./ProfileScreenButton";
import { AppColors } from "../../types/colors";
import { UserInfo } from "../../types/apimodels";
import { useUserStorage } from "../../hook/UserStorageHooks";
import ProfileFooterView from "./ProfileFooterView";
import GradientBackground from "../common/GradientBackground";

const { width, height } = Dimensions.get("window");

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
      <View style={styles.avatarUserIconContainer}>
        <Image
          source={
            user
              ? { uri: user.avatar }
              : require("../../assets/default-avatar.jpg")
          }
          style={styles.avatar}
        />
        <Text style={styles.textLabel}>{user?.username}</Text>
      </View>

      <GradientBackground>
        <View style={styles.bottomContainer}>
          <ProfileScreenButton
            label="Nâng cấp tài khoản"
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
            targetScreen="LessonHistoryScreen"
          />
          <ProfileScreenButton
            label="Thống kê kết quả học"
            targetScreen="StatisticScreen"
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
          companyName="Duy nến dev"
          email="duy0184466@huce.edu.vn"
          releaseYear={2024}
          facebookUrl="https://facebook.com/duyhelloworld"
          githubUrl="https://github.com/duyhelloworld"
        />
      </GradientBackground>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 30,
  },
  bottomContainer: {
    flexDirection: "column",
    paddingHorizontal: 20,
    paddingVertical: 10,
    backgroundColor: AppColors.white,
  },
  textLabel: {
    fontWeight: "700",
    color: AppColors.white,
    fontSize: 23,
  },
  avatar: {
    width: height * 0.15,
    height: height * 0.15,
    borderRadius: 100,
  },
  avatarUserIconContainer: {
    flexDirection: "row",
    alignItems: "flex-end",
    backgroundColor: AppColors.darkGreen
  },
});

export default ProfileScreen;
