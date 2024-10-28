import { NavigationProp, ParamListBase, RouteProp, useNavigation, useRoute } from "@react-navigation/native";
import { AppParams, RootNavigatorParams } from "../navigation/AppNavigation";

export const useAppNavigation = () => {
  const navigation = useNavigation<NavigationProp<RootNavigatorParams>>();
  return navigation;
};

export function useAppParams<
  N extends keyof AppParams,
  S extends keyof AppParams[N]
>(navigator: N, screen: S) {
  const route = useRoute<RouteProp<AppParams[N], S>>();
  return route.params;
}