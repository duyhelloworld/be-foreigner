import React, { useState, useEffect, useRef } from "react";
import {
  View,
  Text,
  StyleSheet,
  Pressable,
  Animated,
  Easing,
  useAnimatedValue,
} from "react-native";
import { Word } from "../../types/apimodels";
import SoundPlayer from "react-native-sound-player";

interface TodayWordViewProps {
  word?: Word;
}

const TodayWordView = ({ word }: TodayWordViewProps) => {
  const [isFlipped, setIsFlipped] = useState(false);
  const flipAnim = useAnimatedValue(0);

  const toggleSound = async () => {
    if (isFlipped && word) {
      SoundPlayer.playUrl(word?.audio)
    } else {
      SoundPlayer.stop()
    }
  };

  const flipCard = () => {
    setIsFlipped(!isFlipped);
    toggleSound();
    Animated.timing(flipAnim, {
      toValue: isFlipped ? 0 : 1,
      duration: 300,
      easing: Easing.linear,
      useNativeDriver: true,
    }).start();
  };

  const frontInterpolate = flipAnim.interpolate({
    inputRange: [0, 1],
    outputRange: ["0deg", "180deg"],
  });

  const backInterpolate = flipAnim.interpolate({
    inputRange: [0, 1],
    outputRange: ["180deg", "360deg"],
  });

  const frontAnimatedStyle = {
    transform: [{ rotateY: frontInterpolate }],
  };

  const backAnimatedStyle = {
    transform: [{ rotateY: backInterpolate }],
  };

  return (
    <View style={styles.container}>
      <Pressable onPress={flipCard} style={styles.cardContainer}>
        <Animated.View style={[styles.card, frontAnimatedStyle]}>
          <View style={styles.cardContent}>
            <Text style={styles.title}>Từ vựng hôm nay là :</Text>
            <Text style={styles.wordValue}>{word?.value}</Text>
            <Text style={styles.wordPhonetic}>{word?.phonetic}</Text>
          </View>
        </Animated.View>
        <Animated.View
          style={[styles.card, styles.cardBack, backAnimatedStyle]}
        >
          <View style={styles.cardContent}>
            <Text style={styles.meaningTitle}>Nghĩa:</Text>
            <Text style={styles.meaningText}>{word?.mean}</Text>
            {word?.examples && word.examples.length > 0 && (
              <View style={styles.exampleContainer}>
                <Text style={styles.exampleTitle}>Ví dụ:</Text>
                <Text style={styles.exampleText}>
                  {word.examples[0].sentense}
                </Text>
                <Text style={styles.exampleMeaning}>
                  {word.examples[0].mean}
                </Text>
              </View>
            )}
          </View>
        </Animated.View>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    marginTop: 20,
    alignItems: "center",
    justifyContent: "center",
    padding: 20,
  },
  cardContainer: {
    width: 300,
    height: 200,
  },
  card: {
    width: "100%",
    height: "100%",
    position: "absolute",
    backfaceVisibility: "hidden",
  },
  cardBack: {
    transform: [{ rotateY: "180deg" }],
  },
  cardContent: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    borderWidth: 2,
    borderColor: "#3B82F6", // blue-500
    backgroundColor: "#FFFFFF",
    borderRadius: 14,
    padding: 15,
  },
  title: {
    fontSize: 16,
    textAlign: "center",
    color: "#7C3AED", // purple-600
    marginBottom: 10,
  },
  wordValue: {
    fontSize: 32,
    color: "#10B981", // green-500
    textAlign: "center",
    marginBottom: 5,
  },
  wordPhonetic: {
    fontSize: 14,
    textAlign: "center",
  },
  meaningTitle: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#7C3AED", // purple-600
    marginBottom: 10,
  },
  meaningText: {
    fontSize: 16,
    color: "#10B981", // green-500
    marginBottom: 10,
    textAlign: "center",
  },
  exampleContainer: {
    marginTop: 10,
  },
  exampleTitle: {
    fontSize: 16,
    fontWeight: "bold",
    color: "#7C3AED",
    marginBottom: 5,
  },
  exampleText: {
    fontSize: 14,
    fontStyle: "italic",
    marginBottom: 5,
    textAlign: "center",
  },
  exampleMeaning: {
    fontSize: 14,
    color: "#4B5563",
    textAlign: "center",
  },
  audioButton: {
    borderRadius: 20,
    borderWidth: 1,
    padding: 5,
    marginTop: 10,
  },
});
export default TodayWordView;
