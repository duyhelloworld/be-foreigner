import { createNativeStackNavigator } from "@react-navigation/native-stack";
import LoginScreen from "../../components/auth/LoginScreen";
import SignupScreen from "../../components/auth/SignupScreen";
import SetupScreen from "../../components/auth/SetupScreen";

export type AuthNavigatorParams = {
  SignupScreen: undefined;
  LoginScreen: undefined;
  SetupScreen: undefined;
};

const AuthNavigator = () => {
  const Stack = createNativeStackNavigator<AuthNavigatorParams>();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="LoginScreen" component={LoginScreen} />
      <Stack.Screen name="SignupScreen" component={SignupScreen} />
      <Stack.Screen name="SetupScreen" component={SetupScreen} />
    </Stack.Navigator>
  );
};

export default AuthNavigator;
