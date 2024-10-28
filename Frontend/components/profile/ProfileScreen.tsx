import { StyleSheet, View } from 'react-native'
import React from 'react'
import ProfileScreenButton from './ProfileScreenButton'

const ProfileScreen = () => {
  return (
    <View style={styles.container}>
      <View style={styles.heading}>
        
      </View>
      <ProfileScreenButton
        label="Quên mật khẩu"
        screen="ForgotPasswordScreen"
      />
      <ProfileScreenButton
        label="Cập nhật thông tin"
        screen="UpdateProfileScreen"
      />
      <ProfileScreenButton
        label="Cài đặt thông báo"
        screen="NotificationSetttingScreen"
      />
      <ProfileScreenButton
        label="Đăng xuất"
        screen="LogoutScreen"
      />
    </View>
  );
}

export default ProfileScreen

const styles = StyleSheet.create({
  container: {

  },
  heading: {

  }
})