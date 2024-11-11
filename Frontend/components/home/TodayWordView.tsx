import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react';
import { Word } from '../../types/apimodels';
import { AppColors } from '../../types/colors';
import { playWordAudio } from '../../utils/AudioUtil';

interface TodayWordViewProp {
  word: Word
}

const TodayWordView = ({word} : TodayWordViewProp) => {

  function onPress() {
    playWordAudio(word.audio);
  }

  return (
    <Pressable style={styles.container} onPress={onPress} >
      <Text style={styles.title}>Từ vựng hôm nay là :</Text>
      <Text style={styles.wordValue}>{word.value}</Text>
      <Text style={styles.wordPhonetic}>/{word.phonetic}/</Text>
    </Pressable>
  );
}

export default TodayWordView

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    borderWidth: 2,
    borderColor: AppColors.blue,
    backgroundColor: AppColors.light,
    borderRadius: 14,
    paddingHorizontal: 10,
    paddingVertical: 15,
    marginHorizontal: 10,
  },
  title: {
    fontSize: 15,
    justifyContent: "center",
    textAlign: "center",
    color: AppColors.purple,
  },
  wordValue: {
    fontSize: 50,
    color: AppColors.green,
    textAlign: "center",
    textShadowColor: AppColors.grayDark,
    textShadowOffset: { width: 2, height: 2 },
    textShadowRadius: 8,
  },
  wordPhonetic: {
    fontSize: 15,
    textAlign: "center",
  },
});