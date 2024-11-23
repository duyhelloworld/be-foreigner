import { Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useEffect, useState } from "react";
import { AppColors } from "../../../types/colors";
import { LearnScreenContext } from "../LearnScreenHooks";

interface GiveSentenseRearrangWordsViewProp {
  sentenseWords: string[];
  sentenseMeaning: string;
  unrelatedWords?: string[];
}

const GiveSentenseRearrangWordsView = ({
  sentenseMeaning,
  sentenseWords,
  unrelatedWords,
}: GiveSentenseRearrangWordsViewProp) => {
  const resultRef = useContext(LearnScreenContext);
  const [selectedAnswers, setSelectedAnswers] = useState<string[]>([]);
  const [unselectedAnswers, setUnselectedAnswers] = useState<string[]>([]);
  
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
        JSON.stringify(selectedAnswers).trim() === JSON.stringify(sentenseWords).trim(),
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
        <Text style={styles.correctAnswerText}>{sentenseMeaning}</Text>
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
            style={[styles.answer, styles.unselectedAnswer]}
            onPress={() => handleOnUnselectedPress(item)}
          >
            <Text style={styles.answerText}>{item}</Text>
          </Pressable>
        ))}
      </View>
    </View>
  );
};

export default GiveSentenseRearrangWordsView;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginHorizontal: 10,
  },
  correctAnswerContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginLeft: 20,
    marginTop: 10,
  },
  correctAnswerText: {
    fontSize: 20,
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
    marginTop: 20,
    marginBottom: 20,
    width: "100%",
    height: "50%",
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
  unselectedAnswer: {},
});
