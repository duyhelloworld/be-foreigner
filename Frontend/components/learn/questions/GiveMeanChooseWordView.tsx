import {
  Dimensions,
  Image,
  Pressable,
  StyleSheet,
  Text,
  View,
} from "react-native";
import React, { useContext, useRef, useState } from "react";
import { AppColors } from "../../../types/colors";
import { LearnContext } from "../../../context/LearnContext";
import { AnswerOption } from "../../../types/apimodels";
import { QuestionLevel } from "../../../types/enum";
import { Ionicons } from "@expo/vector-icons";
import SoundPlayer from "react-native-sound-player";

interface GiveMeanChooseWordViewProp {
  correctOptionMean: string;
  correctOptionAudio: string;
  correctOptionValue: string;
  answerOptions: AnswerOption[];
  level: keyof typeof QuestionLevel;
}

const { width, height } = Dimensions.get("screen");

const GiveMeanChooseWordView = ({
  answerOptions,
  correctOptionAudio,
  correctOptionValue,
  correctOptionMean,
  level
}: GiveMeanChooseWordViewProp) => {
  const resultRef = useContext(LearnContext);
  const [selectedIndex, setSelectedIndex] = useState<number>();

  function onCorrectAnsPress() {
    if (level === "EASY") {
      SoundPlayer.playUrl(correctOptionAudio);
    }
  }

  async function onOptionPress(currentIndex: number) {
    if (selectedIndex === currentIndex) {
      setSelectedIndex(undefined);
    } else {
      setSelectedIndex(currentIndex);
    }

    resultRef.current = {
      enabled: true,
      isCorrect: answerOptions[currentIndex].isTrue,
      message: answerOptions[currentIndex].isTrue
        ? ""
        : `${correctOptionValue} mới là đáp án mà`,
    };
  }

  return (
    <View style={styles.container}>
      <Pressable
        style={styles.correctAnswerContainer}
        onPress={onCorrectAnsPress}
      >
        <Ionicons
          name="volume-high"
          color="white"
          size={30}
          style={styles.correctAnswerAudio}
        />
        <Text style={styles.correctAnswerText}>{correctOptionMean}</Text>
      </Pressable>

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
    maxWidth: width * 0.8,
    marginLeft: 20,
    alignItems: "center",
    marginBottom: 20,
  },
  correctAnswerText: {
    color: AppColors.black,
    fontSize: 23,
    textShadowColor: AppColors.green,
    textShadowOffset: { width: 1, height: 1 },
    textShadowRadius: 2,
  },
  correctAnswerAudio: {
    backgroundColor: AppColors.green,
    padding: 10,
    borderRadius: 10,
    marginRight: 15,
  },
  answersContainer: {
    flex: 1,
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "space-around",
  },
  answer: {
    width: width * 0.45,
    height: height * 0.25,
    alignItems: "center",
    padding: 2,
    marginBottom: 20,
    borderWidth: 3,
    borderRadius: 10,
    borderColor: AppColors.lightGray,
  },
  answerPressed: {
    backgroundColor: AppColors.light,
    borderColor: AppColors.green,
  },
  answerImage: {
    width: "100%",
    height: "80%",
    borderRadius: 10,
    marginBottom: 10,
  },
  answerValue: {
    textAlign: "center",
    fontSize: 18,
  },
});
