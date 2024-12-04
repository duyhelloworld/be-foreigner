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
      <Text style={styles.valueText}>{value}</Text>
      <Text style={styles.meanText}>{mean}</Text>
    </View>
  );
};

export default LearnWordByValue

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
    flex: 0.9
  },
  valueText: {
    color: AppColors.blue,
    fontSize: 40
  },
  meanText: {
    color: AppColors.gray,
    fontSize: 30,
    marginTop: 20,
  },
});