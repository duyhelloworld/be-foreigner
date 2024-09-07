import { StyleSheet, View } from "react-native";
import { AlertNotificationRoot } from "react-native-alert-notification";
import AppNavigation from "./components/navigation/AppNavigation";

export default function App() {
  return (
    <AlertNotificationRoot>
      <View style={{ flex: 1, paddingTop: 30 }}>
        <AppNavigation isFirstTime={false} isLogedIn={false} />
      </View>
    </AlertNotificationRoot>
  );
}

const styles = StyleSheet.create({});