import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { sampleDecks, sampleWord } from '../../utils/InitData';
import TodayWordView from './todayword/TodayWordView';
import DeckView from './decks/DeckView';

const HomeScreen = () => {
  return (
    <View>
      <TodayWordView word={sampleWord()} />
      <DeckView decks={sampleDecks()} title="Bộ thẻ gần đây" />
      <DeckView decks={sampleDecks()} title="Bộ thẻ yêu thích" />
    </View>
  );
}

export default HomeScreen

const styles = StyleSheet.create({})