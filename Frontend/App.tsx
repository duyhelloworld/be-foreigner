import { LogBox, View } from "react-native";
import AppNavigation from "./navigation/AppNavigation";
import useAuthStorage from "./storage/AuthStorageHooks";
import { setupAxiosClient } from "./config/AxiosConfig";

export default function App() {
  LogBox.ignoreLogs(["Require cycle:"]);
  const authStorage = useAuthStorage();

  setupAxiosClient(authStorage);

  return (
    <View style={{ flex: 1, paddingTop: 35 }}>
      <AppNavigation />
    </View>
  );
}
