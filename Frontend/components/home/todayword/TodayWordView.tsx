import { StyleSheet, Text, View } from 'react-native'
import React from 'react';
import TodayWordExpandView from './TodayWordExpandView';

interface TodayWordViewProp {
  word: Word
}

const TodayWordView = (prop: TodayWordViewProp) => {
  const word = prop.word;
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Từ vựng hôm nay là :</Text>
      <Text style={styles.wordValue}>{word.value}</Text>
      <Text style={styles.wordPhonetic}>/ {word.phonetic} /</Text>
      <TodayWordExpandView title='Ý nghĩa' mean={word.mean} />
      <TodayWordExpandView title='Ví dụ' examples={word.examples} />
    </View>
  );
}

export default TodayWordView

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    borderWidth: 2,
    borderColor: "#1C42D9",
    backgroundColor: "#ddd",
    borderRadius: 14,
    paddingHorizontal: 10,
    paddingVertical: 15,
    marginHorizontal: 10,
  },
  title: {
    fontSize: 15,
    justifyContent: "center",
    textAlign: "center",
    color: "#806AE3",
  },
  wordValue: {
    fontSize: 50,
    color: "#00FB54",
    textAlign: "center",
    textShadowColor: "#888",
    textShadowOffset: { width: 2, height: 2 },
    textShadowRadius: 8,
  },
  wordPhonetic: {
    fontSize: 15,
    textAlign: "center",
  },
  
});