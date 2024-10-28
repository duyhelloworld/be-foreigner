import { Animated } from "react-native";
import { AppColors } from "../../../types/Colors";

interface SplashBubbleViewProp {
  bubblesAnim: Animated.Value;
}

export default function SplashBubbleView({ bubblesAnim }: SplashBubbleViewProp) {
  const randomX = Math.random() * 200 - 100;
  const randomY = Math.random() * 100 - 50;
  const randomSize = Math.random() * 20 + 10;
  return (
    <Animated.View
      style={{
        position: "absolute",
        width: randomSize,
        height: randomSize,
        borderRadius: randomSize / 2,
        backgroundColor: AppColors.blue,
        transform: [
          {
            translateX: bubblesAnim.interpolate({
              inputRange: [0, 1],
              outputRange: [0, randomX],
            }),
          },
          {
            translateY: bubblesAnim.interpolate({
              inputRange: [0, 1],
              outputRange: [0, randomY],
            }),
          },
          {
            scale: bubblesAnim.interpolate({
              inputRange: [0, 0.5, 1],
              outputRange: [0, 1, 0],
            }),
          },
        ],
      }}
    />
  );
}
