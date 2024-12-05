import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useContext, useEffect, useState } from "react";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../../types/colors";
import { LearnScreenContext } from "../LearnScreenHooks";
import { Sound } from "expo-av/build/Audio";
import { AnswerOption } from "../../../types/apimodels";

interface GiveAudioChooseWordViewProp {
  correctOptionMean: string;
  correctOptionAudio: string | Sound;
  answerOptions: AnswerOption[];
}

const GiveAudioChooseWordView = ({
  answerOptions, correctOptionMean, correctOptionAudio
}: GiveAudioChooseWordViewProp) => {
  const [pressedIndex, setPressedIndex] = useState<number>();
  const resultRef = useContext(LearnScreenContext);

  async function play() {
    if (typeof correctOptionAudio === 'string') {
      let { sound } = await Sound.createAsync({ uri: correctOptionAudio });
      await sound.playAsync();
    } else {
      await correctOptionAudio.playAsync();
    }
  }

  const onOptionPress = (currentIndex: number) => {
    if (pressedIndex === currentIndex) {
      setPressedIndex(undefined);
    } else {
      setPressedIndex(currentIndex);
    }
    resultRef.current = {
      enabled: true,
      isCorrect: answerOptions[currentIndex].isTrue,
      message: answerOptions[currentIndex].isTrue 
        ? correctOptionMean 
        : `${answerOptions.filter(a => a.isTrue)[0].value} mới là đáp án đúng`,
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
        data={answerOptions}
        renderItem={({ item, index }) => (
          <Pressable
            style={[
              styles.answer,
              pressedIndex === index ? styles.answerPressed : null,
            ]}
            onPress={() => onOptionPress(index)}
          >
            <Text style={styles.answerValue}>{item.value}</Text>
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
