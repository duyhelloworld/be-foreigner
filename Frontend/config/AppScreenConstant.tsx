import { Dimensions, Platform } from "react-native";

export const { height: SCREEN_HEIGHT, width: SCREEN_WIDTH } =
  Platform.OS == "android"
    ? Dimensions.get("screen")
    : Dimensions.get("window");
