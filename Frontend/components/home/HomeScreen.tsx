import { StyleSheet, Text, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { sampleTopics, sampleWord, sampleWords } from '../../utils/InitData';
import TodayWordView from './todayword/TodayWordView';
import ListTopicView from './topics/ListTopicView';
import AppIndicator from '../common/AppIndicator';

const HomeScreen = () => {
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    fetch("https://jsonplaceholder.typicode.com/todo")
      .then((data) => {
        console.log(data.json());
        setIsLoading(false);
    });
  }, []);
  
  return (
    <View>
      {isLoading ? (
        <AppIndicator visible={isLoading} />
      ) : (
        <View>
          <TodayWordView word={sampleWord()} />
          <ListTopicView topics={sampleTopics()} title="Bộ thẻ gần đây" />
          <ListTopicView topics={sampleTopics()} title="Bộ thẻ yêu thích" />
        </View>
      )}
    </View>
  );
}

export default HomeScreen

const styles = StyleSheet.create({})