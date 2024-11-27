import { createNativeStackNavigator } from "@react-navigation/native-stack";
import WelcomeScreen from "../../components/onboard/WelcomeScreen";
import IntroduceScreen from "../../components/onboard/IntroduceScreen";

export type FirstTryNavigatorParams = {
  WelcomeScreen: undefined;
  IntroduceScreen: undefined;
};

const FirstTryNavigator = () => {

  const Stack = createNativeStackNavigator<FirstTryNavigatorParams>();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="WelcomeScreen" component={WelcomeScreen} />
      <Stack.Screen name="IntroduceScreen" component={IntroduceScreen} />
    </Stack.Navigator>
  );
};

export default FirstTryNavigator;
