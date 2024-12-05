import { Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useEffect, useState } from "react";
import { LearnScreenContext } from "../LearnScreenHooks";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../../types/colors";
import { Sound } from "expo-av/build/Audio";

interface GiveAudioRearrangeWordsProp {
  sentenseAudio: string | Sound;
  sentenseWords: string[];
  unrelatedWords?: string[];
}

const GiveAudioRearrangeWords = ({
  sentenseAudio,
  sentenseWords,
  unrelatedWords,
}: GiveAudioRearrangeWordsProp) => {
  const resultRef = useContext(LearnScreenContext);

  const [selectedAnswers, setSelectedAnswers] = useState<string[]>([]);
  const [unselectedAnswers, setUnselectedAnswers] = useState<string[]>([]);

  async function play() {
    if (typeof sentenseAudio === "string") {
      const { sound } = await Sound.createAsync({ uri: sentenseAudio });
      await sound.playAsync();
    } else {
      await sentenseAudio.playAsync();
    }
  }

  useEffect(() => {
    const shuffedAnswers = (
      unrelatedWords ? [...sentenseWords, ...unrelatedWords] : [...sentenseWords]
    ).sort(() => 0.5 - Math.random());
    setSelectedAnswers([]);
    setUnselectedAnswers(shuffedAnswers);
  }, [sentenseWords]);

  useEffect(() => {
    resultRef.current = {
      enabled: true,
      message: "",
      isCorrect:
        JSON.stringify(selectedAnswers).trim() ===
        JSON.stringify(sentenseWords).trim(),
    };
  }, [selectedAnswers, sentenseWords]);

  function handleOnUnselectedPress(answer: string) {
    setUnselectedAnswers(unselectedAnswers.filter((ans) => ans !== answer));
    setSelectedAnswers([...selectedAnswers, answer]);
  }

  function handleOnSelectedPress(answer: string) {
    setUnselectedAnswers([...unselectedAnswers, answer]);
    setSelectedAnswers(selectedAnswers.filter((ans) => ans !== answer));
  }

  return (
    <View style={styles.container}>
      <View style={styles.correctAnswerContainer}>
        <Ionicons name="volume-high" style={styles.correctAnswerAudioIcon} size={100} onPress={play} />
      </View>

      <View style={styles.selectedAnswerContainer}>
        {selectedAnswers.map((item, index) => (
          <Pressable
            key={index}
            style={[styles.answer, styles.selectedAnswer]}
            onPress={() => handleOnSelectedPress(item)}
          >
            <Text style={styles.answerText}>{item}</Text>
          </Pressable>
        ))}
      </View>

      <View style={styles.unselectedAnswerContainer}>
        {unselectedAnswers.map((item, index) => (
          <Pressable
            key={index}
            style={styles.answer}
            onPress={() => handleOnUnselectedPress(item)}
          >
            <Text style={styles.answerText}>{item}</Text>
          </Pressable>
        ))}
      </View>
    </View>
  );
};

export default GiveAudioRearrangeWords;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginHorizontal: 10,
  },
  correctAnswerContainer: {
    alignItems: "center",
  },
  correctAnswerAudioIcon: {
    color: AppColors.white,
    backgroundColor: AppColors.green,
    padding: 30,
    borderRadius: 100,
    marginBottom: 5,
  },
  answer: {
    padding: 10,
    borderWidth: 2,
    borderColor: AppColors.grayDark,
    borderRadius: 15,
    margin: 5,
  },
  answerText: {
    fontSize: 18,
  },
  selectedAnswerContainer: {
    marginVertical: 20,
    width: "100%",
    height: "30%",
    flexWrap: "wrap",
    flexDirection: "row",
    backgroundColor: AppColors.light,
  },
  selectedAnswer: {
    borderColor: AppColors.green,
  },
  unselectedAnswerContainer: {
    flexWrap: "wrap",
    flexDirection: "row",
  },
});
