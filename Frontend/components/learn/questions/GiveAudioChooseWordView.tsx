import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useState } from "react";
import { QuestionOption } from "../../../types/apimodels";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../../types/colors";
import { playWordAudio } from "../../../utils/AudioUtil";
import { LearnScreenContext } from "../LearnScreenHooks";

interface GiveAudioChooseWordViewProp {
  correctOption: QuestionOption;
  incorrectOptions: QuestionOption[];
}

const GiveAudioChooseWordView = ({
  correctOption,
  incorrectOptions,
}: GiveAudioChooseWordViewProp) => {
  
  const answers = [correctOption, ...incorrectOptions];
  const [pressedIndex, setPressedIndex] = useState<number>();
  const resultRef = useContext(LearnScreenContext);
  
  const onOptionPress = (currentIndex: number) => {
    
    playWordAudio(answers[currentIndex].audio);

    if (pressedIndex === currentIndex) {
      setPressedIndex(undefined);
    } else {
      setPressedIndex(currentIndex);
    }

    let isCorrect = answers[currentIndex] === correctOption;
    resultRef.current = {
      enabled: true,
      isCorrect: isCorrect,
      message: isCorrect ? `${correctOption.text}` : "",
    };
  };

  return (
    <View>
      <View style={styles.audioIconContainer}>
        <Ionicons
          size={100}
          name="volume-high"
          style={styles.audioIcon}
          onPress={() => playWordAudio(correctOption.audio)}
        />
        <Text style={styles.audioDescription}>Chạm để nghe</Text>
      </View>

      <FlatList
        contentContainerStyle={styles.answerContainer}
        data={answers}
        renderItem={({ item, index }) => (
          <Pressable
            style={[
              styles.answer,
              pressedIndex === index ? styles.answerPressed : null,
            ]}
            onPress={() => onOptionPress(index)}
          >
            <Text style={styles.answerValue}>{item.text}</Text>
          </Pressable>
        )}
      />
    </View>
  );
};

export default GiveAudioChooseWordView;

const styles = StyleSheet.create({
  audioIconContainer: {
    alignItems: "center",
  },
  audioIcon: {
    color: AppColors.white,
    backgroundColor: AppColors.green,
    padding: 30,
    borderRadius: 100,
    marginBottom: 5,
  },
  audioDescription: {
    marginBottom: 25,
  },
  answerContainer: {
    alignItems: "center",
  },
  answer: {
    minWidth: "80%",
    marginBottom: 20,
    padding: 10,
    borderWidth: 2,
    borderColor: AppColors.light,
    borderRadius: 10,
  },
  answerPressed: {
    borderColor: AppColors.green,
  },
  answerValue: {
    textAlign: "center",
  },
});
