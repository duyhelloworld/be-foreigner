import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { RouteProp, useRoute } from '@react-navigation/native';
import { Routes } from '../../types/global';
import WordView from '../home/WordView';
import { sampleWords } from '../../utils/InitData';

const LearnScreen = () => {

  const params = useRoute<RouteProp<Routes, "Learn">>().params;

  return (
    <WordView words={sampleWords()} />
  );
}

export default LearnScreen

const styles = StyleSheet.create({})