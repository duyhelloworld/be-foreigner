import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { RouteProp, useRoute } from '@react-navigation/native';
import { Routes } from '../../types/global';

const LearnScreen = () => {

  const params = useRoute<RouteProp<Routes, "Learn">>().params;

  return (
    <View>
      <Text>{params.topicId}</Text>
      <Text>LearnScreen</Text>
    </View>
  )
}

export default LearnScreen

const styles = StyleSheet.create({})