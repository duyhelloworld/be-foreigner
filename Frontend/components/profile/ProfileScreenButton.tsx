import { Pressable, StyleSheet, Text, View } from "react-native";
import { ProfileNavigatorParams } from "../../navigation/navigators/ProfileNavigator";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { RootNavigatorParams } from "../../navigation/AppNavigation";
import { AppColors } from "../../types/colors";
import { MaterialCommunityIcons } from "@expo/vector-icons";

interface ProfileScreenButtonProp {
  targetScreen: keyof ProfileNavigatorParams;
  label: string;
  backgroundColor?: string;
  textColor?: string;
  icon?: keyof typeof MaterialCommunityIcons.glyphMap;
  iconColor?: string;
  hightlightText?: boolean;
}

const ProfileScreenButton = ({
  label,
  targetScreen,
  backgroundColor = AppColors.white,
  textColor = AppColors.black,
  icon,
  iconColor,
  hightlightText = false
}: ProfileScreenButtonProp) => {
  const navigator = useNavigation<NavigationProp<RootNavigatorParams>>();
  return (
    <Pressable
      style={[styles.button, { backgroundColor }]}
      onPress={() =>
        navigator.navigate("ProfileNavigator", { screen: targetScreen })
      }
    >
      <Text style={[styles.buttonText, { color: textColor, fontWeight: hightlightText ? "500" : "300" }]}>{label}</Text>
      <View style={styles.badgeContainer}>
        <MaterialCommunityIcons
          name={icon}
          size={25}
          color={iconColor}
          style={styles.badge}
        />
      </View>
    </Pressable>
  );
};

export default ProfileScreenButton;

const styles = StyleSheet.create({
  button: {
    flexDirection: "row",
    alignItems: "center",
    width: "90%",
    padding: 20,
    borderWidth: 1,
    borderRadius: 10,
    borderColor: AppColors.green,
    marginVertical: 5,
    elevation: 4
  },
  buttonText: {
    fontSize: 20,
  },
  badgeContainer: {
    position: "absolute",
    top: -15,
    right: -15,
  },
  badge: {
    padding: 5,

  }
});
