import { Image, StyleSheet, Text, View } from "react-native";
import React from "react";
import appIcon from "../../assets/icon-transparent.png";
import { AppColors } from "../../types/Colors";

const AppIconView = () => {
  return (
    <View style={styles.logoContainer}>
      <Image
        source={appIcon}
        style={styles.imageBackground}
        resizeMode="contain"
      />
      <Text style={styles.title}>Be Foreigner</Text>
    </View>
  );
};

export default AppIconView;

const styles = StyleSheet.create({
  logoContainer: {
    alignItems: "center",
    marginBottom: 20,
  },
  imageBackground: {
    justifyContent: "center",
    alignItems: "center",
    marginBottom: "5%",
  },
  title: {
    fontSize: 30,
    color: AppColors.green,
    fontWeight: "bold",
    textShadowColor: AppColors.grayDark,
    textShadowOffset: { width: -1, height: 1 },
    textShadowRadius: 5,
  },
});
