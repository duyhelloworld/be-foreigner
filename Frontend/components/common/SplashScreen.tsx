import {
  Animated,
  Easing,
  Image,
  StyleSheet,
  View,
  useAnimatedValue,
} from "react-native";
import React, { useEffect } from "react";
import { AppColors } from "../../types/colors";

interface SplashScreenProps {
  onTask: () => Promise<void>; 
  onFinish: () => void; 
  totalTime: number;
}

const SplashScreen = ({onTask, onFinish, totalTime } : SplashScreenProps) => {
  const fadeAnim = useAnimatedValue(0);
  const scaleAnim = useAnimatedValue(0);
  const rotateAnim = useAnimatedValue(0);

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: true,
      }),
      Animated.timing(scaleAnim, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: true,
      }),
      Animated.loop(
        Animated.timing(rotateAnim, {
          toValue: 1,
          duration: 2000,
          easing: Easing.linear,
          useNativeDriver: true,
        })
      ),
    ]).start();

    const timer = setTimeout(async () => {
      await onTask();
      onFinish();
    }, totalTime);

    return () => clearTimeout(timer);
  }, []);

  const spin = rotateAnim.interpolate({
    inputRange: [0, 1],
    outputRange: ["0deg", "360deg"],
  });

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.content,
          {
            opacity: fadeAnim,
            transform: [{ scale: scaleAnim }, { rotate: spin }],
          },
        ]}
      >
        <Image source={require("../../assets/icon.png")} style={styles.image} />
      </Animated.View>
    </View>
  );
};

export default SplashScreen;

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
    width: 200,
    height: 200,
    borderRadius: 100,
    overflow: "hidden",
    backgroundColor: AppColors.darkGreen,
    justifyContent: "center",
    alignItems: "center",
  },
  image: {
    width: "100%",
    height: "100%",
    resizeMode: "cover",
  },
  placeholderText: {
    fontSize: 32,
    fontWeight: "bold",
    color: AppColors.white,
  },
});
