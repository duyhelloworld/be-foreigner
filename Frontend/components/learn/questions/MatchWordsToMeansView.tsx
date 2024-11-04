import { StyleSheet, Text, View } from 'react-native'
import React, { useContext } from 'react'
import { LearnScreenContext } from '../../../hook/LearnScreenHooks';

interface MatchWordsToMeansViewProp {
  matchings: Map<string, string>;
}

const MatchWordsToMeansView = () => {

  const resultQuestion = useContext(LearnScreenContext);
  
  return (
    <View>
      <Text>MatchWordsToMeansView</Text>
    </View>
  )
}

export default MatchWordsToMeansView

const styles = StyleSheet.create({})