import { Alert, LogBox, StyleSheet, View } from "react-native";
import { useEffect } from "react";
import AppNavigation from "./navigation/AppNavigation";
import useAuthStorage from "./hook/AuthStorageHooks";
import { setupAxiosClient } from "./config/AxiosConfig";
import * as Network from "expo-network";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {
  FirebaseMessagingTypes,
  getMessaging,
} from "@react-native-firebase/messaging";
import { useNotificationStorage } from "./hook/NotificationStorageHook";

export default function App() {
  LogBox.ignoreLogs(["Require cycles "]);

  const authStorage = useAuthStorage();
  const notificationStorage = useNotificationStorage();
  setupAxiosClient(authStorage);

  async function checkNetwork() {
    const state = await Network.getNetworkStateAsync();
    if (!state.isConnected || !state.isInternetReachable) {
      alert("Lỗi kết nối : Ứng dụng hiện tại đang ngoại tuyến");
    }
  }

  getMessaging().setBackgroundMessageHandler(
    async (remoteMessage: FirebaseMessagingTypes.RemoteMessage) => {
      Alert.alert("Background message", JSON.stringify(remoteMessage));
      if (remoteMessage.messageId) {
        notificationStorage.addNotification({
          id: remoteMessage.messageId,
          title: remoteMessage.notification?.title ?? "Chưa rõ",
          content: remoteMessage.notification?.body ?? "Nội dung trống",
          sendAt: remoteMessage.sentTime ?? 0,
          isRead: false,
        });
      }
    }
  );

  getMessaging().onMessage(
    async (remoteMessage: FirebaseMessagingTypes.RemoteMessage) => {
      Alert.alert("A new FCM message arrived!", JSON.stringify(remoteMessage));

      if (remoteMessage.messageId) {
        notificationStorage.addNotification({
          id: remoteMessage.messageId,
          title: remoteMessage.notification?.title ?? "Chưa rõ",
          content: remoteMessage.notification?.body ?? "Nội dung trống",
          sendAt: remoteMessage.sentTime ?? 0,
          isRead: false,
        });
      }
    }
  );

  async function clearData() {
    await AsyncStorage.clear();
  }

  useEffect(() => {
    checkNetwork();
    // clearData();
  }, []);

  return <AppNavigation />;
}
