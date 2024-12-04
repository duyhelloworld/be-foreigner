import React from "react";
import { View, Text, StyleSheet, Linking, Platform } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";

interface ProfileFooterViewProps {
  email: string;
  companyName: string;
  releaseYear: number;
  facebookUrl?: string;
  githubUrl?: string;
}

const ProfileFooterView: React.FC<ProfileFooterViewProps> = ({
  email,
  companyName,
  releaseYear,
  facebookUrl,
  githubUrl,
}) => {
  const handleEmailPress = () => {
    Linking.openURL(`mailto:${email}`);
  };

  const handleSocialPress = async (
    url: string,
    appName: "facebook" | "github"
  ) => {
    let appUrl: string;
    if (appName === "facebook") {
      appUrl =
        Platform.OS === "ios"
          ? `fb://profile/${getFacebookId(url)}`
          : `fb://facewebmodal/f?href=${url}`;
    } else {
      appUrl = Platform.OS === "ios" ? `github://` : `com.github.android://`;
    }

    const canOpen = await Linking.canOpenURL(appUrl);
    if (canOpen) {
      await Linking.openURL(appUrl);
    } else {
      await Linking.openURL(url);
    }
  };

  const getFacebookId = (url: string): string => {
    const match = url.match(
      /(?:https?:\/\/)?(?:www\.)facebook\.com\/(?:(?:\w)*#!\/)?(?:pages\/)?(?:[\w\-]*\/)*([\w\-]*)/
    );
    return match ? match[1] : "";
  };

  return (
    <View style={styles.footer}>
      <View style={styles.iconContainer}>
        <Ionicons
          name="mail"
          size={24}
          color={AppColors.gray}
          onPress={handleEmailPress}
          style={styles.icon}
        />
        {facebookUrl && (
          <Ionicons
            name="logo-facebook"
            size={24}
            color={AppColors.blue}
            onPress={() => handleSocialPress(facebookUrl, "facebook")}
            style={styles.icon}
          />
        )}
        {githubUrl && (
          <Ionicons
            name="logo-github"
            size={24}
            color={AppColors.black}
            onPress={() => handleSocialPress(githubUrl, "github")}
            style={styles.icon}
          />
        )}
      </View>
      <Text style={styles.copyright}>
        © {releaseYear}. All rights reserved.
      </Text>
      <Text style={styles.companyName}>{companyName}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  footer: {
    width: '100%',
    padding: 24,
    alignItems: "center",
  },
  iconContainer: {
    flexDirection: "row",
    justifyContent: "center",
    marginBottom: 12,
  },
  icon: {
    marginHorizontal: 8,
  },
  copyright: {
    fontSize: 14,
    textAlign: "center",
    marginBottom: 8,
  },
  companyName: {
    color: AppColors.gray,
    fontSize: 12,
  },
});

export default ProfileFooterView;
