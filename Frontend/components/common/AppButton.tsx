import {
  Button,
  Image,
  ImageSourcePropType,
  ImageStyle,
  Pressable,
  StyleProp,
  StyleSheet,
  Text,
  TextStyle,
  View,
  ViewStyle,
} from "react-native";
import React from "react";
import { Ionicons } from "@expo/vector-icons";

interface AppButtonProp {
  title?: string;
  titleStyle?: StyleProp<TextStyle>;
  style?: StyleProp<ViewStyle>;
  disabled?: boolean;
  onPress?: () => void;
  onPressOut?: () => void;
  onHoverIn?: () => void;
  onLongPress?: () => void;
}

const AppButton = ({
  onPress,
  onPressOut,
  onHoverIn,
  onLongPress,
  style,
  title,
  titleStyle,
  disabled = false,
}: AppButtonProp) => {

  const buttonStyles = getButtonStyles(style, disabled);

  return (
    <View style={styles.container}>
      <Pressable
        onPress={onPress}
        onPressOut={onPressOut}
        onHoverIn={onHoverIn}
        onLongPress={onLongPress}
        style={buttonStyles}
      >
        {title && <Text style={[styles.defaultTitle, titleStyle]}>{title}</Text>}
      </Pressable>
    </View>
  );
};

export default AppButton;

const getButtonStyles = (
  customStyle: StyleProp<ViewStyle>,
  disabled: boolean
): StyleProp<ViewStyle> => {
  let baseStyles = { ...styles.defaultButton, customStyle };
  if (disabled) {
    return {
      ...baseStyles,
      opacity: 0.4,
    };
  }
  return baseStyles;
};

const styles = StyleSheet.create({
  container: {
    margin: 5,
    justifyContent: "center",
    alignItems: "center",
    padding: 15,
  },
  defaultTitle: {
    color: "#444",
  },
  defaultButton: {
    backgroundColor: "#00FB54",
    borderRadius: 10,
    borderWidth: 0.5,
    borderColor: "#888",
    paddingHorizontal: 10,
    paddingVertical: 7,
    shadowOffset: { width: 0, height: 5},
    elevation: 10,
  },
});
