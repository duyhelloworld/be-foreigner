import React, { useContext, useState } from 'react';
import { StyleSheet, Text, View, TextInput, TouchableOpacity } from 'react-native';
import { LearnScreenContext } from '../LearnScreenHooks';
import { AppColors } from '../../../types/colors';

interface GiveMeanEnterValueProps {
  meaning: string;
  correctWord: string;
}

const GiveMeanEnterValue = ({ meaning, correctWord }: GiveMeanEnterValueProps) => {
  const [enteredWord, setEnteredWord] = useState('');
  const resultRef = useContext(LearnScreenContext);

  const isCorrect = JSON.stringify(enteredWord.toLowerCase().trim()) === JSON.stringify(correctWord.toLowerCase());
  resultRef.current = {
    enabled: true,
    isCorrect: isCorrect,
    message: isCorrect ? '' : `Từ chính xác phải là"${correctWord}".`
  };

  return (
    <View style={styles.container}>
      <View style={styles.meaningContainer}>
        <Text style={styles.meaningText}>{meaning}</Text>
      </View>
      <TextInput
        style={styles.input}
        value={enteredWord}
        onChangeText={setEnteredWord}
        placeholder="Nhập từ ở đây"
        placeholderTextColor={AppColors.gray}
        autoCapitalize="none"
        autoCorrect={false}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 20,
  },
  meaningContainer: {
    backgroundColor: AppColors.white,
    padding: 20,
    borderRadius: 10,
    marginBottom: 20,
    width: '100%',
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 5 },
    shadowOpacity: 0.2,
    shadowRadius: 5,
    elevation: 5,
  },
  meaningText: {
    fontSize: 18,
    color: AppColors.black,
    textAlign: 'center',
  },
  instructionText: {
    fontSize: 18,
    marginBottom: 20,
    textAlign: 'center',
  },
  input: {
    width: '100%',
    height: 50,
    borderColor: AppColors.gray,
    borderWidth: 1,
    borderRadius: 10,
    paddingHorizontal: 15,
    fontSize: 18,
    color: AppColors.black,
    marginBottom: 20,
  },
  submitButton: {
    backgroundColor: AppColors.green,
    paddingVertical: 12,
    paddingHorizontal: 30,
    borderRadius: 25,
  },
  submitButtonDisabled: {
    backgroundColor: AppColors.gray,
  },
  submitButtonText: {
    color: AppColors.white,
    fontSize: 18,
    fontWeight: 'bold',
  },
  resultText: {
    marginTop: 20,
    fontSize: 18,
    fontWeight: 'bold',
  },
});

export default GiveMeanEnterValue;