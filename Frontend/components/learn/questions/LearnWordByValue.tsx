import { StyleSheet, Text, View } from 'react-native'
import React, { useContext } from 'react'
import { LearnScreenContext } from '../LearnScreenHooks';
import { AppColors } from '../../../types/colors';

interface LearnWordByValueProp {
  mean: string;
  value: string;
}

const LearnWordByValue = ({ mean, value }: LearnWordByValueProp) => {
  
  const resultRef = useContext(LearnScreenContext);
  resultRef.current = {
    enabled: true,
    isCorrect: true,
    message: value,
  };

  return (
    <View style={styles.container}>
      <Text style={styles.meanText}>{mean}</Text>
      <Text style={styles.valueText}>{value}</Text>
    </View>
  );
};

export default LearnWordByValue

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
  },
  meanText: {
    color: AppColors.blue,
    fontSize: 40
  },
  valueText: {
    color: AppColors.gray,
    fontSize: 30,
    marginTop: 20,
    lineHeight: 40,
    letterSpacing: 0.5,
  },
});