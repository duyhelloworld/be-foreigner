import { Alert, LogBox, View } from "react-native";
import { useEffect } from "react";
import AppNavigation from "./navigation/AppNavigation";
import useAuthStorage from "./storage/AuthStorageHooks";
import { setupAxiosClient } from "./config/AxiosConfig";
import messaging, {
  FirebaseMessagingTypes,
} from "@react-native-firebase/messaging";

export default function App() {
  LogBox.ignoreLogs(["Require cycle:"]);

  const authStorage = useAuthStorage();
  setupAxiosClient(authStorage);

  async function requestUserPermission() {
    const authStatus = await messaging().requestPermission();
    const enabled =
      authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
      authStatus === messaging.AuthorizationStatus.PROVISIONAL;

    if (enabled) {
      console.log("Authorization status:", authStatus);
    }
  }

  async function getAndSaveToken() {
    await messaging().registerDeviceForRemoteMessages();
    const token = await messaging().getToken();
    console.log("Token: ", token);
    return token;
  }

  useEffect(() => {
    requestUserPermission();
    getAndSaveToken();

    const unsubscribe = messaging().onMessage(
      async (remoteMessage: FirebaseMessagingTypes.RemoteMessage) => {
        Alert.alert(
          "A new FCM message arrived!",
          JSON.stringify(remoteMessage)
        );
      }
    );

    messaging().setBackgroundMessageHandler(
      async (remoteMessage: FirebaseMessagingTypes.RemoteMessage) => {
        console.log("Background message", remoteMessage);
      }
    );

    return unsubscribe;
  }, []);

  return (
    <View style={{ flex: 1, paddingTop: 35 }}>
      <AppNavigation />
    </View>
  );
}
