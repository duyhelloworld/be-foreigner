import React from "react";
import SplashScreen from "../common/SplashScreen";
import { useUserStorage } from "../../hook/UserStorageHooks";
import { ApiResponse, UserInfo } from "../../types/apimodels";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";
import { useAppNavigation } from "../../navigation/AppNavigation";
import messaging, {
  FirebaseMessagingTypes,
} from "@react-native-firebase/messaging";
import { Alert } from "react-native";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";

const SetupScreen = () => {
  const navigator = useAppNavigation();
  const userStorage = useUserStorage();
  const notificationStorage = useNotificationStorage();

  async function requestUserPushNotificationPermission() {
    const authStatus = await messaging().requestPermission();
    const enabled =
      authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
      authStatus === messaging.AuthorizationStatus.PROVISIONAL;
    if (enabled) {
      await messaging().registerDeviceForRemoteMessages();
      const token = await messaging().getToken();
      const response = await apiClient.post<ApiResponse>("user/notification?token=" + token);
      if (response.data.code === ApiResponseCode.OK) {
        await notificationStorage.init();
      } else {
        Alert.alert("Có lỗi khi đăng kí thông báo");
        return;
      }

      messaging().onMessage(
        async (remoteMessage: FirebaseMessagingTypes.RemoteMessage) => {
          Alert.alert(
            "A new FCM message arrived!",
            JSON.stringify(remoteMessage)
          );

          if (remoteMessage.messageId) {
            notificationStorage.addNotification({
              id: remoteMessage.messageId,
              title: remoteMessage.notification?.title ?? "Chưa rõ",
              content: remoteMessage.notification?.body ?? "Nội dung trống",
              sendAt: remoteMessage.sentTime ?? 0,
              isRead: false,
            })
          }
        }
      )
    }
  }

  const handleTask = async () => {
    await requestUserPushNotificationPermission();
    const response = await apiClient.get<ApiResponse>(`user/my-info`);
    console.log("Check user img: ", response.data.data);
    if (response.data.code === ApiResponseCode.OK) {
      await userStorage.setInfo(response.data.data as UserInfo);
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
      totalTime={2000}
    />
  );
};

export default SetupScreen;
