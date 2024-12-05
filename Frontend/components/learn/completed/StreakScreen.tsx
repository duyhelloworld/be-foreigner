import React, { useEffect, useRef } from "react";
import { View, Text, StyleSheet, Animated, Easing, Image } from "react-native";
import GradientBackground from "../../common/GradientBackground";
import fireImage from "../../../assets/fire.png";
import BottomButton from "../../common/BottomButton";
import { useAppNavigation } from "../../../navigation/AppNavigation";

interface LearningStreakProps {
  streakDay?: number;
}

const StreakScreen: React.FC<LearningStreakProps> = ({ streakDay = 100 }) => {
  const flameOpacity = useRef(new Animated.Value(0)).current;
  const counterValue = useRef(new Animated.Value(0)).current;
  const textOpacity = useRef(new Animated.Value(0)).current;

  const navigator = useAppNavigation();

  useEffect(() => {
    Animated.sequence([
      Animated.timing(flameOpacity, {
        toValue: 1,
        duration: 2000,
        useNativeDriver: true,
      }),
      Animated.timing(counterValue, {
        toValue: streakDay,
        duration: 1500,
        easing: Easing.out(Easing.ease),
        useNativeDriver: true,
      }),
      Animated.timing(textOpacity, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: true,
      }),
    ]).start();
  }, []);

  return (
    <GradientBackground>
      <View style={styles.container}>
        <Animated.View
          style={[styles.flameContainer, { opacity: flameOpacity }]}
        >
          <Animated.Image source={fireImage} style={styles.flame} />
        </Animated.View>
        <Animated.Text style={styles.counter}>
          {counterValue.interpolate({
            inputRange: [0, streakDay],
            outputRange: ["0", streakDay.toString()],
          })}
        </Animated.Text>
        <Animated.Text style={[styles.congratsText, { opacity: textOpacity }]}>
          Chúc mừng chuỗi {streakDay} của bạn
        </Animated.Text>
        <BottomButton
          title="Tiếp tục"
          onPress={() =>
            navigator.navigate("HomeNavigator", { screen: "HomeScreen" })
          }
        />
      </View>
    </GradientBackground>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  flameContainer: {
    width: 200,
    height: 200,
  },
  flame: {
    width: "100%",
    height: "100%",
  },
  counter: {
    fontSize: 48,
    fontWeight: "bold",
    color: "#FFF",
    marginTop: 20,
  },
  congratsText: {
    fontSize: 24,
    fontWeight: "bold",
    textAlign: "center",
    marginTop: 20,
    textShadowColor: "rgba(0, 0, 0, 0.75)",
    textShadowOffset: { width: -1, height: 1 },
    textShadowRadius: 10,
  },
});

export default StreakScreen;
