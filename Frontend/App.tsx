import { LogBox, View } from "react-native";
import AppNavigation from "./navigation/AppNavigation";

export default function App() {
  LogBox.ignoreLogs(["Require cycle:"]);
  return (
    <View style={{ flex: 1, paddingTop: 35 }}>
      <AppNavigation />
    </View>
  );
}