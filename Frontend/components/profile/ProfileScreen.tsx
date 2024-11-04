import { Image, ScrollView, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import ProfileScreenButton from './ProfileScreenButton'
import { useUserStorage } from '../../hook/GlobalStorageHooks'
import { AppColors } from '../../types/Colors'
import { Ionicons } from '@expo/vector-icons'
import SubscriptionPlanView from '../common/SubscriptionPlanView'

const ProfileScreen = () => {

  const user = useUserStorage();

  return (
    <View style={styles.container}>
      <View style={styles.heading}>
        <Image src={user.avatar} style={styles.avatar} />
        <View style={styles.infoContainer}>
          <Text style={styles.username}>{user.username}</Text>
          <Text style={styles.key}>
            Họ tên :<Text style={styles.fullname}>{user.fullname}</Text>
          </Text>
          <Text style={styles.key}>
            Email :<Text style={styles.email}>{user.email}</Text>
          </Text>

          <SubscriptionPlanView plan={user.plan} />

          <View style={styles.providerContainer}>
            <Ionicons
              name="logo-facebook"
              size={25}
              style={styles.providerIcon}
            />
            <Ionicons
              name="logo-google"
              size={25}
              style={styles.providerIcon}
            />
            <Ionicons
              name="logo-github"
              size={25}
              style={styles.providerIcon}
            />
            <Ionicons name="logo-apple" size={25} style={styles.providerIcon} />
          </View>
        </View>
      </View>

      <ScrollView contentContainerStyle={styles.buttonContainer}>
        <ProfileScreenButton
          label="Nâng cấp tài khoản "
          targetScreen="UpgradePlanScreen"
          backgroundColor={AppColors.yellowDark}
          textColor={AppColors.white}
          icon='crown'
          iconColor={AppColors.yellow}
          hightlightText
        />
        <ProfileScreenButton
          label="Quên mật khẩu"
          targetScreen="ForgotPasswordScreen"
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
      </ScrollView>
    </View>
  );
}

export default ProfileScreen

const styles = StyleSheet.create({
  container: {
    marginHorizontal: 10,
    justifyContent: "space-around",
  },
  heading: {
    flexDirection: "row",
    marginTop: 20,
  },
  infoContainer: {
    flexDirection: "column",
    justifyContent: "space-between",
  },
  avatar: {
    width: 100,
    height: 100,
    borderWidth: 2,
    borderCurve: "continuous",
    borderRadius: 200,
  },
  username: {
    fontSize: 15,
    fontFamily: "serif",
    textAlign: "center",
    color: AppColors.blue,
    padding: 10,
    backgroundColor: AppColors.lightGreen,
    opacity: 0.9,
  },
  key: {
    fontWeight: "500",
    fontSize: 16,
    maxWidth: 200,
  },
  fullname: {
    fontWeight: "300",
  },
  email: {
    fontWeight: "300",
  },
  providerContainer: {
    flexDirection: "row",
  },
  providerIcon: {
    padding: 5,
    margin: 2,
  },
  providerCurrent: {},
  buttonContainer: {
    alignItems: 'center'
  }
});