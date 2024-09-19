import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { sampleTopics, sampleWord, sampleWords } from '../../utils/InitData';
import TodayWordView from './todayword/TodayWordView';
import ListTopicView from './topics/ListTopicView';

const HomeScreen = () => {
  return (
    <View>
      <TodayWordView word={sampleWord()} />
      <ListTopicView topics={sampleTopics()} title="Bộ thẻ gần đây" />
      <ListTopicView topics={sampleTopics()} title="Bộ thẻ yêu thích" />
    </View>
  );
}

export default HomeScreen

const styles = StyleSheet.create({})