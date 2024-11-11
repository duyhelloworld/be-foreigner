import {
  Animated,
  Dimensions,
  Easing,
  StyleSheet,
  Text,
  View,
} from "react-native";
import { sampleLesson } from "../../../utils/InitData";
import { useEffect, useRef } from "react";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { AppColors } from "../../../types/colors";
import { LearnNavigatorParams } from "../../../navigation/navigators/LearnNavigator";
import SplashBubbleView from "./SplashBubbleView";

const SplashLearnScreen = () => {
  const lesson = sampleLesson();

  useEffect(() => {}, []);

  const navigator = useNavigation<NavigationProp<LearnNavigatorParams>>();

  const scaleAnim = new Animated.Value(1);
  const colorAnim = new Animated.Value(0);
  const positionAnim = new Animated.Value(0);
  const bubblesAnim = new Animated.Value(0);
  const opacityAnim = new Animated.Value(1);

  useEffect(() => {
    Animated.sequence([
      // Shrink and change color
      Animated.parallel([
        Animated.timing(scaleAnim, {
          toValue: 0.2,
          duration: 1000,
          useNativeDriver: false,
        }),
        Animated.timing(colorAnim, {
          toValue: 1,
          duration: 1000,
          useNativeDriver: false,
        }),
      ]),
      // Rơi xuống và tan biến
       Animated.parallel([
        Animated.timing(positionAnim, {
          toValue: 1,
          duration: 500,
          useNativeDriver: false,
        }),
        Animated.timing(opacityAnim, {
          toValue: 0,
          duration: 1000,
          useNativeDriver: false,
        }),
      ]),
      // Hiệu ứng đổ nước
      Animated.timing(bubblesAnim, {
        toValue: 1,
        duration: 1000,
        easing: Easing.out(Easing.ease),
        useNativeDriver: false,
      }),
    ]).start(() => {
      navigator.navigate("LearnScreen");
    });
  }, []);

  const backgroundColor = colorAnim.interpolate({
    inputRange: [0, 1],
    outputRange: [AppColors.green, AppColors.blue],
  });

  const yPosition = positionAnim.interpolate({
    inputRange: [0, 1],
    outputRange: [0, 200],
  });

  const bubbles = Array(20)
    .fill(0)
    .map((_, index) => (
      <SplashBubbleView key={index} bubblesAnim={bubblesAnim} />
    ));

  return (
    <View style={styles.container}>
      <Text style={styles.slashText}>Đang sọan bài, đợi tớ tí he!</Text>
      <Animated.View
        style={{
          width: 200,
          height: 200,
          borderRadius: 100,
          justifyContent: "center",
          alignItems: "center",
          backgroundColor,
          opacity: opacityAnim,
          transform: [{ scale: scaleAnim }, { translateY: yPosition }],
        }}
      />
      <View style={styles.bubble}>{bubbles}</View>
    </View>
  );
};

export default SplashLearnScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: AppColors.green,
    alignItems: "center",
  },
  slashText: {
    fontSize: 24,
    marginTop: "40%",
    color: AppColors.white,
    marginBottom: 20,
    fontWeight: "bold",
  },
  bubble: {
    justifyContent: "center",
    alignItems: "center",
  },
});
