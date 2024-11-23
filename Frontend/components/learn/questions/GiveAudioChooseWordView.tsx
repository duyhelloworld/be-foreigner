import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useEffect, useState } from "react";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../../types/colors";
import { LearnScreenContext } from "../LearnScreenHooks";
import { Sound } from "expo-av/build/Audio";
import { AnswerOption } from "../../../types/apimodels";

interface GiveAudioChooseWordViewProp {
  option: AnswerOption;
  unrelatedOptions: AnswerOption[];
}

const GiveAudioChooseWordView = ({
  option,
  unrelatedOptions,
}: GiveAudioChooseWordViewProp) => {
  const answers = [option, ...unrelatedOptions].sort();
  const [pressedIndex, setPressedIndex] = useState<number>();
  const resultRef = useContext(LearnScreenContext);

  async function play() {
    let { sound } = await Sound.createAsync({ uri: option.audio });
    await sound.playAsync();
  }

  const onOptionPress = (currentIndex: number) => {
    if (pressedIndex === currentIndex) {
      setPressedIndex(undefined);
    } else {
      setPressedIndex(currentIndex);
    }

    let isCorrect = answers[currentIndex] === option;
    resultRef.current = {
      enabled: true,
      isCorrect: isCorrect,
      message: isCorrect ? `${option.text}` : "",
    };
  };

  return (
    <View>
      <View style={styles.audioIconContainer}>
        <Ionicons
          size={100}
          name="volume-high"
          style={styles.audioIcon}
          onPress={play}
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
