import React from "react";
import SplashScreen from "../common/SplashScreen";
import { useUserStorage } from "../../hook/UserStorageHooks";
import { ApiResponse, Notification, UserInfo } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode, UserLevel } from "../../types/enum";
import { useAppNavigation } from "../../navigation/AppNavigation";
import messaging, {
} from "@react-native-firebase/messaging";
import { Alert, PermissionsAndroid, Platform } from "react-native";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";

const SetupScreen = () => {
  const navigator = useAppNavigation();
  const userStorage = useUserStorage();
  const notificationStorage = useNotificationStorage();

  async function requestUserPushNotificationPermission() {
    await messaging().requestPermission();
    if (Platform.OS === "android") {
      const grant = await PermissionsAndroid.request(
        "android.permission.POST_NOTIFICATIONS"
      );
      if (grant === "granted") {
        await messaging().registerDeviceForRemoteMessages();
        const token = await messaging().getToken();
        if (token) {
          const response = await apiClient.post<ApiResponse>("user/setup", {
            token: token,
          });
          if (response.data.code === ApiResponseCode.OK) {
            navigator.navigate("HomeNavigator", { screen: "HomeScreen" });
          } else {
            Alert.alert("Có lỗi khi đăng kí thông báo");
            return;
          }
        }
      } else {
        Alert.alert(
          "Quyền bị từ chối",
          "Vui lòng cấp quyền hiển thị thông báo trong cài đặt để hệ thống có thể thông báo chính xác nhất."
        );
      }
    }
  }

  const handleTask = async () => {
    await requestUserPushNotificationPermission();
    const infoResponse = await apiClient.get<ApiResponse>(`user/my-info`);
    if (infoResponse.data.code === ApiResponseCode.OK) {
      await userStorage.setInfo(infoResponse.data.data as UserInfo);
    }
    const remindResponse = await apiClient.get<ApiResponse>("remind/sync");
    if (remindResponse.data.code === ApiResponseCode.OK) {
      await notificationStorage.addAll(remindResponse.data.data as Notification[]);
    }
  };

  const handleFinish = () => {
    navigator.navigate("HomeNavigator", { screen: "HomeScreen" });
  };

  return (
    <SplashScreen
      onTask={handleTask}
      onFinish={handleFinish}
      label="Đợi chút nhé"
      sublabel="Chúng tôi đang thiết lập một số thứ cho bạn"
    />
  );
};

export default SetupScreen;
