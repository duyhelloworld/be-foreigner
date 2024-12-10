import { LogBox } from "react-native";
import { useEffect } from "react";
import AppNavigation from "./navigation/AppNavigation";
import useAuthStorage from "./hook/AuthStorageHooks";
import { setupAxiosClient } from "./config/AxiosConfig";
import messaging, {
  FirebaseMessagingTypes,
} from "@react-native-firebase/messaging";
import { useNotificationStorage } from "./hook/NotificationStorageHook";
import { Notification, isRemind } from "./types/apimodels";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { NetworkCheckerProvider } from "./context/NetworkContext";

export default function App() {
  LogBox.ignoreLogs(["Require cycles ", 'Non-serializable values were found in the navigation state', ]);

  const authStorage = useAuthStorage();
  const notificationStorage = useNotificationStorage();
  setupAxiosClient(authStorage);

  async function addNew(remoteMessage: FirebaseMessagingTypes.RemoteMessage) {
    if (remoteMessage.messageId) {
      let notification: Notification = {
        id: remoteMessage.messageId,
        title: remoteMessage.notification?.title ?? "Chưa rõ",
        content: remoteMessage.notification?.body ?? "Nội dung trống",
        isRead: false,
      };
      if (isRemind(remoteMessage.data)) {
        notification.lessonId = remoteMessage.data.lessonId;
      }
      notificationStorage.addNotification(notification);
    }
  }

  messaging().onMessage(addNew);
  messaging().setBackgroundMessageHandler(addNew);
  
  useEffect(() => {
    AsyncStorage.clear();
  }, []);

  return (
    <NetworkCheckerProvider>
      <AppNavigation />
    </NetworkCheckerProvider>
  );
}
