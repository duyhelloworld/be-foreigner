import { Linking, LogBox } from "react-native";
import { useEffect } from "react";
import AppNavigation, { useAppNavigation } from "./navigation/AppNavigation";
import useAuthStorage from "./hook/AuthStorageHooks";
import { setupAxiosClient } from "./config/AxiosConfig";
import messaging, {
  FirebaseMessagingTypes,
} from "@react-native-firebase/messaging";
import { useNotificationStorage } from "./hook/NotificationStorageHook";
import { Notification, NotificationData } from "./types/apimodels";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { getRandomNumber } from "./utils/NumberUtil";

export default function App() {
  LogBox.ignoreLogs([
    "Require cycles ",
    "Non-serializable values were found in the navigation state",
  ]);

  const authStorage = useAuthStorage();
  const notificationStorage = useNotificationStorage();
  setupAxiosClient(authStorage);

  async function addNew(remoteMessage: FirebaseMessagingTypes.RemoteMessage) {
    if (remoteMessage.messageId && remoteMessage.data) {
      notificationStorage.addNotification({
        id: getRandomNumber(0, 999999),
        title: remoteMessage.notification?.title ?? "Chưa rõ",
        content: remoteMessage.notification?.body ?? "Nội dung trống",
        read: false,
        data: remoteMessage.data as NotificationData
      });
    }
  }

  messaging().onMessage(addNew);
  messaging().setBackgroundMessageHandler(addNew);

  useEffect(() => {
    // AsyncStorage.clear();
  }, []);

  return <AppNavigation />;
}
