import { Pressable, StyleSheet, Text } from "react-native";
import { ProfileNavigatorParams } from "../../navigation/navigators/ProfileNavigator";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { RootNavigatorParams } from "../../navigation/AppNavigation";
import { AppColors } from "../../types/Colors";

interface ProfileScreenButtonProp {
  screen: keyof ProfileNavigatorParams;
  label: string;
}

const ProfileScreenButton = ({
  label,
  screen,
}: ProfileScreenButtonProp) => {
  const navigator = useNavigation<NavigationProp<RootNavigatorParams>>();
  return (
    <Pressable  
      style={styles.button}
      onPress={() =>
        navigator.navigate("ProfileNavigator", { screen: screen })
      }
    >
      <Text style={styles.buttonText}>{label}</Text>
    </Pressable>
  );
};

export default ProfileScreenButton;

const styles = StyleSheet.create({
  button: {
    padding: 20,
    backgroundColor: AppColors.light,
    borderWidth: 1,
    borderRadius: 5,
  },
  buttonText: {
    fontSize: 20,
    color: AppColors.yellow,
    fontWeight: "400"
  }
})