import React, { useEffect } from "react";
import {
  Animated,
  Easing,
  Image,
  StyleSheet,
  View,
  Dimensions,
  useAnimatedValue,
} from "react-native";
import { AppColors } from "../../types/colors";
import appIcon from "../../assets/icon-transparent.png";

const { width } = Dimensions.get("window");

interface SplashScreenProps {
  onTask: () => Promise<void>;
  onFinish: () => void;
  totalTime: number;
  label?: string;
  sublabel?: string;
}

const SplashScreen = ({
  onTask,
  onFinish,
  totalTime,
  label = "Đang tải...",
  sublabel = "Chúng tôi luôn cố gắng làm những gì tốt nhất",
}: SplashScreenProps) => {
  const fadeAnim = useAnimatedValue(0);
  const scaleAnim = useAnimatedValue(0.5);
  const rotateAnim = useAnimatedValue(0);
  const titleAnim = useAnimatedValue(-20);
  const subtitleAnim = useAnimatedValue(0);

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

    const timer = setTimeout(async () => {
      await onTask();
      onFinish();
    }, totalTime);

    return () => clearTimeout(timer);
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
          {
            opacity: fadeAnim,
            transform: [{ scale: scaleAnim }],
          },
        ]}
      >
        <Animated.View style={{ transform: [{ rotate: spin }] }}>
          <Image source={appIcon} style={styles.image} />
        </Animated.View>
        <Animated.Text
          style={[styles.title, { transform: [{ translateY: titleAnim }] }]}
        >
          {label}
        </Animated.Text>
        <Animated.Text
          style={[styles.subtitle, { opacity: subtitleAnim }]}
          numberOfLines={0}
        >
          {sublabel}
        </Animated.Text>
      </Animated.View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: AppColors.green,
  },
  content: {
    alignItems: "center",
  },
  image: {
    width: width * 0.5,
    height: width * 0.5,
    resizeMode: "contain",
  },
  title: {
    fontSize: 32,
    fontWeight: "bold",
    color: AppColors.white,
    marginTop: 16,
    marginBottom: 8,
  },
  subtitle: {
    fontSize: 18,
    color: AppColors.white,
  },
});

export default SplashScreen;
