import { Pressable, StyleSheet, Text, View } from "react-native";
import { ProfileNavigatorParams } from "../../navigation/navigators/ProfileNavigator";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { RootNavigatorParams, useAppNavigation } from "../../navigation/AppNavigation";
import { AppColors } from "../../types/colors";
import { MaterialCommunityIcons } from "@expo/vector-icons";

interface ProfileScreenButtonProp {
  targetScreen?: keyof ProfileNavigatorParams;
  label: string;
  backgroundColor?: string;
  textColor?: string;
  icon?: keyof typeof MaterialCommunityIcons.glyphMap;
  iconColor?: string;
  hightlightText?: boolean;
  showBadge?: boolean;
}

const ProfileScreenButton = ({
  label,
  targetScreen,
  backgroundColor = AppColors.white,
  textColor = AppColors.black,
  icon,
  iconColor,
  hightlightText = false,
  showBadge = false,
}: ProfileScreenButtonProp) => {
  const navigator = useAppNavigation();
  return (
    <Pressable
      style={[styles.button, { backgroundColor }]}
      onPress={() =>{ 
        if (targetScreen) {
          navigator.navigate("ProfileNavigator", { screen: targetScreen })
        } else {
          alert("Chức năng này chưa được triển khai");
        }
      }
      }
    >
      <Text
        style={[
          styles.buttonText,
          { color: textColor, fontWeight: hightlightText ? "500" : "400" },
        ]}
      >
        {label}
      </Text>
      {showBadge ? (
        <View style={styles.badgeContainer}>
          <MaterialCommunityIcons
            name={icon}
            size={25}
            color={iconColor}
            style={styles.badge}
          />
        </View>
      ) : null}
    </Pressable>
  );
};

export default ProfileScreenButton;

const styles = StyleSheet.create({
  button: {
    flexDirection: "row",
    alignItems: "center",
    width: "100%",
    padding: 20,
    borderRadius: 10,
    marginVertical: 8,
    elevation: 2,
    shadowColor: AppColors.black,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
  },
  buttonText: {
    fontSize: 16,
    flex: 1,
  },
  badgeContainer: {
    position: "absolute",
    top: -10,
    right: -5,
    backgroundColor: AppColors.white,
    borderRadius: 15,
    padding: 2,
  },
  badge: {
    padding: 2,
  },
});
