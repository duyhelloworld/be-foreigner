import { RouteProp, useRoute } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import LoginScreen from "../../components/auth/LoginScreen";
import OnBoardScreen from "../../components/onboard/OnBoardScreen";

export type AuthNavigatorParams = {
  OnBoardScreen: undefined;
  LoginScreen: undefined;
};

const AuthNavigator = () => {
  const Stack = createNativeStackNavigator<AuthNavigatorParams>();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="OnBoardScreen" component={OnBoardScreen} />
      <Stack.Screen name="LoginScreen" component={LoginScreen} />
    </Stack.Navigator>
  );
};

export default AuthNavigator;
