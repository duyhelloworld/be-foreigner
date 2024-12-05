import { Image, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useState } from "react";
import { AppColors } from "../../../types/colors";
import { LearnScreenContext } from "../LearnScreenHooks";
import { AnswerOption } from "../../../types/apimodels";
import { QuestionLevel } from "../../../types/enum";
import { Sound } from "expo-av/build/Audio";

interface GiveMeanChooseWordViewProp {
  correctOptionMean: string;
  correctOptionAudio: string | Sound;
  answerOptions: AnswerOption[];
  level: QuestionLevel;
}

const GiveMeanChooseWordView = ({
  answerOptions, correctOptionAudio, correctOptionMean,
  level,
}: GiveMeanChooseWordViewProp) => {
  const resultRef = useContext(LearnScreenContext);
  const [selectedIndex, setSelectedIndex] = useState<number>();

  async function onOptionPress(currentIndex: number) {
    if (selectedIndex === currentIndex) {
      setSelectedIndex(undefined);
    } else {
      setSelectedIndex(currentIndex);
    }
    if (level === QuestionLevel.EASY) {
      if (typeof correctOptionAudio === 'string') {
        let { sound } = await Sound.createAsync({ uri: correctOptionAudio });
        await sound.playAsync();
      } else {
        await correctOptionAudio.playAsync();
      }
    }

    resultRef.current = {
      enabled: true,
      isCorrect: answerOptions[currentIndex].isTrue,
      message: answerOptions[currentIndex].isTrue
        ? ""
        : `${answerOptions.filter(a => a.isTrue)[0].value} mới là đáp án mà`,
    };
  };

  return (
    <View style={styles.container}>
      <View style={styles.correctAnswerContainer}>
        <Text style={styles.correctAnswerText}>{correctOptionMean}</Text>
      </View>

      <View style={styles.answersContainer}>
        {answerOptions.map((answer, index) => (
          <Pressable
            key={index}
            style={[
              styles.answer,
              index === selectedIndex ? styles.answerPressed : null,
            ]}
            onPress={() => onOptionPress(index)}
          >
            <Image style={styles.answerImage} source={{ uri: answer.image }} />
            <Text style={styles.answerValue}>{answer.value}</Text>
          </Pressable>
        ))}
      </View>
    </View>
  );
};

export default GiveMeanChooseWordView;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  correctAnswerContainer: {
    flexDirection: "row",
    maxWidth: "40%",
    marginLeft: "5%",
    alignItems: "center",
  },
  correctAnswerText: {
    marginBottom: "3%",
    color: AppColors.green,
    fontSize: 23,
  },
  answersContainer: {
    flex: 1,
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "space-between",
  },
  answer: {
    width: "45%",
    alignItems: "center",
    marginHorizontal: "2%",
    marginVertical: "1%",
    padding: "1%",
    borderWidth: 3,
    borderRadius: 10,
    borderColor: AppColors.light,
  },
  answerPressed: {
    backgroundColor: AppColors.light,
    borderColor: AppColors.green,
  },
  answerImage: {
    width: "100%",
    height: "60%",
    borderRadius: 10,
    marginBottom: 10,
  },
  answerValue: {
    textAlign: "center",
  },
});
