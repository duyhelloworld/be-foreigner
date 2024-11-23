import { createNativeStackNavigator } from "@react-navigation/native-stack";
import GameScreen from "../../components/game/GameScreen";

export type GameNavigatorParams = {
  GameScreen: undefined;
};

const GameNavigator = () => {
  const Stack = createNativeStackNavigator<GameNavigatorParams>();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="GameScreen" component={GameScreen} />
    </Stack.Navigator>
  );
};

export default GameNavigator;
