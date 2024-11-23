import { Image, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useState } from "react";
import { AppColors } from "../../../types/colors";
import { LearnScreenContext } from "../LearnScreenHooks";
import { AnswerOption } from "../../../types/apimodels";

interface GiveMeanChooseWordViewProp {
  option: AnswerOption;
  unrelatedOptions: AnswerOption[];
}

const GiveMeanChooseWordView = ({
  option,
  unrelatedOptions,
}: GiveMeanChooseWordViewProp) => {
  const resultRef = useContext(LearnScreenContext);
  const answers = [option, ...unrelatedOptions];
  const [selectedIndex, setSelectedIndex] = useState<number>();

  const onOptionPress = (currentIndex: number) => {
    if (selectedIndex === currentIndex) {
      setSelectedIndex(undefined);
    } else {
      setSelectedIndex(currentIndex);
    }

    let isCorrect = answers[currentIndex] === option;
    resultRef.current = {
      enabled: true,
      isCorrect: isCorrect,
      message: isCorrect
        ? `${option.text}`
        : `${option.text} mới là đáp án mà`,
    };
  };

  return (
    <View style={styles.container}>
      <View style={styles.correctAnswerContainer}>
        <Text style={styles.correctAnswerText}>{option.text}</Text>
      </View>

      <View style={styles.answersContainer}>
        {answers.map((answer, index) => (
          <Pressable
            key={index}
            style={[
              styles.answer,
              index === selectedIndex ? styles.answerPressed : null,
            ]}
            onPress={() => onOptionPress(index)}
          >
            <Image style={styles.answerImage} source={{ uri: answer.image }} />
            <Text style={styles.answerValue}>{answer.text}</Text>
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
