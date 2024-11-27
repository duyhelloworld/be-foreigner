import React, { useEffect } from "react";
import {
  View,
  Image,
  StyleSheet,
  Animated,
  Easing,
  Dimensions,
  useAnimatedValue,
} from "react-native";
import appIcon from "../../assets/icon-transparent.png";
import { AppColors } from "../../types/colors";
import BottomButton from "../common/BottomButton";
import { useAppNavigation } from "../../navigation/AppNavigation";

const { width } = Dimensions.get("window");

const WelcomeScreen = () => {
  const fadeAnim = useAnimatedValue(0);
  const scaleAnim = useAnimatedValue(0.5);
  const titleAnim = useAnimatedValue(-20);
  const subtitleAnim = useAnimatedValue(0);
  const rotateAnim = useAnimatedValue(0);
  
  const navigator = useAppNavigation();

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
      }),
      Animated.timing(scaleAnim, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
      }),
      Animated.timing(titleAnim, {
        toValue: 0,
        delay: 500,
        duration: 300,
        useNativeDriver: true,
      }),
      Animated.timing(subtitleAnim, {
        toValue: 1,
        delay: 1000,
        duration: 1000,
        useNativeDriver: true,
      }),
    ]).start();

    Animated.loop(
      Animated.sequence([
        Animated.timing(rotateAnim, {
          toValue: 1,
          duration: 1000,
          easing: Easing.linear,
          useNativeDriver: true,
        }),
        Animated.timing(rotateAnim, {
          toValue: -1,
          duration: 1000,
          easing: Easing.linear,
          useNativeDriver: true,
        }),
        Animated.timing(rotateAnim, {
          toValue: 0,
          duration: 1000,
          easing: Easing.linear,
          useNativeDriver: true,
        }),
      ])
    ).start();
  }, []);

  const spin = rotateAnim.interpolate({
    inputRange: [-1, 0, 1],
    outputRange: ["-10deg", "0deg", "10deg"],
  });

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.content,
          { opacity: fadeAnim, transform: [{ scale: scaleAnim }] },
        ]}
      >
        <Animated.View style={{ transform: [{ rotate: spin }] }}>
          <Image source={appIcon} style={styles.image} />
        </Animated.View>
        <Animated.Text
          style={[styles.title, { transform: [{ translateY: titleAnim }] }]}
        >
          Chào mừng bạn!
        </Animated.Text>
        <Animated.Text style={[styles.subtitle, { opacity: subtitleAnim }]}>
          Hãy khám phá ứng dụng của chúng tôi
        </Animated.Text>
      </Animated.View>
      <BottomButton
        title="OK"
        onPress={() =>
          navigator.navigate("FirstTryNavigator", { screen: "IntroduceScreen" })
        }
      />
    </View>
  );
};

export default WelcomeScreen;
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: AppColors.green,
    alignItems: "center",
    justifyContent: "center",
    padding: 16,
  },
  content: {
    alignItems: "center",
  },
  image: {
    width: width * 0.5,
    height: width * 0.5,
    marginBottom: 32,
    resizeMode: "contain",
  },
  title: {
    fontSize: 32,
    fontWeight: "bold",
    color: AppColors.white,
    marginBottom: 16,
    textAlign: "center",
  },
  subtitle: {
    fontSize: 18,
    color: AppColors.white,
    textAlign: "center",
  },
});
