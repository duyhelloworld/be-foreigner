import React, { useEffect, useState } from "react";
import { Dimensions, Image, StyleSheet, Text, View } from "react-native";
import ProfileScreenButton from "./ProfileScreenButton";
import { AppColors } from "../../types/colors";
import { UserInfo } from "../../types/apimodels";
import { useUserStorage } from "../../hook/UserStorageHooks";
import ProfileFooterView from "./ProfileFooterView";
import {  Ionicons, MaterialCommunityIcons } from "@expo/vector-icons";
import { SubscriptionPlan, UserLevel } from "../../types/enum";

const ProfileScreen = () => {
  const [user, setUser] = useState<UserInfo>();
  const userStorage = useUserStorage();
  
  useEffect(() => {
    async function loadUser() {
      const current = await userStorage.getInfo();
      setUser(current);
    }
    loadUser();
  }, [userStorage]);

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <View style={styles.avatarContainer}>
          <Image
            source={
              user
                ? { uri: user.avatar }
                : require("../../assets/default-avatar.jpg")
            }
            style={styles.avatar}
          />
          <Text style={styles.userName}>
            {user?.fullname || user?.username}
          </Text>
        </View>
        <View style={styles.infoCard}>
          <View style={styles.infoRow}>
            <Ionicons name="mail" size={20} color={AppColors.darkGreen} />
            <Text style={styles.infoText}>{user?.email}</Text>
          </View>
          <View style={styles.infoRow}>
            <MaterialCommunityIcons
              name="crown"
              size={20}
              color={AppColors.darkGreen}
            />
            <Text style={styles.infoText}>
              {
                SubscriptionPlan[
                  user?.plan as unknown as keyof typeof SubscriptionPlan
                ]
              }
            </Text>
          </View>
          <View style={styles.infoRow}>
            <MaterialCommunityIcons
              name="cards-playing-diamond-outline"
              size={24}
              color={AppColors.darkGreen}
            />
            <Text style={styles.infoText}>
              {UserLevel[user?.level as unknown as keyof typeof UserLevel]}
            </Text>
          </View>
        </View>
      </View>

      <View>
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
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    backgroundColor: AppColors.darkGreen,
    paddingTop: 40,
    paddingHorizontal: 20,
    paddingBottom: 30,
    borderBottomLeftRadius: 30,
    borderBottomRightRadius: 30,
  },
  avatarContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 20,
  },
  avatar: {
    width: 80,
    height: 80,
    borderRadius: 20,
    borderWidth: 2,
    borderColor: AppColors.white,
  },
  userName: {
    fontSize: 24,
    fontWeight: "600",
    color: AppColors.white,
    marginLeft: 15,
  },
  infoCard: {
    backgroundColor: AppColors.white,
    borderRadius: 15,
    padding: 15,
    marginHorizontal: 10,
  },
  infoRow: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 10,
  },
  infoText: {
    marginLeft: 10,
    fontSize: 16,
    color: AppColors.darkGreen,
  },
  bottomContainer: {
    flexDirection: "column",
    paddingHorizontal: 20,
    paddingVertical: 10,
    backgroundColor: AppColors.white,
  },
});

export default ProfileScreen;
