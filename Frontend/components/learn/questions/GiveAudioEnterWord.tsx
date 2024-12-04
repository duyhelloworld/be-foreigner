import React, { useContext, useEffect, useState } from 'react';
import { Dimensions, StyleSheet, Text, View, TextInput, TouchableOpacity } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { Sound } from 'expo-av/build/Audio';
import { LearnScreenContext } from '../LearnScreenHooks';
import { AppColors } from '../../../types/colors';

interface GiveAudioEnterWordProps {
  audio: string;
  correctWord: string;
}

const { width } = Dimensions.get('window');

const GiveAudioEnterWord = ({ audio, correctWord }: GiveAudioEnterWordProps) => {
  const [enteredWord, setEnteredWord] = useState('');
  const [isSubmitted, setIsSubmitted] = useState(false);
  const resultRef = useContext(LearnScreenContext);

  useEffect(() => {
    playAudio();
  }, []);

  async function playAudio() {
    const { sound } = await Sound.createAsync({ uri: audio });
    await sound.playAsync();
  }

  const handleSubmit = () => {
    setIsSubmitted(true);
    const isCorrect = enteredWord.toLowerCase() === correctWord.toLowerCase();
    resultRef.current = {
      enabled: true,
      isCorrect: isCorrect,
      message: isCorrect ? 'Correct!' : `Incorrect. The correct word is "${correctWord}".`,
    };
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={playAudio} style={styles.audioButton}>
        <Ionicons name="volume-high" size={width * 0.2} color={AppColors.white} />
      </TouchableOpacity>
      <Text style={styles.instructionText}>Listen and type the word you hear:</Text>
      <TextInput
        style={styles.input}
        value={enteredWord}
        onChangeText={setEnteredWord}
        placeholder="Enter the word"
        placeholderTextColor={AppColors.gray}
        autoCapitalize="none"
        autoCorrect={false}
      />
      <TouchableOpacity 
        style={[styles.submitButton, isSubmitted && styles.submitButtonDisabled]} 
        onPress={handleSubmit}
        disabled={isSubmitted}
      >
        <Text style={styles.submitButtonText}>Submit</Text>
      </TouchableOpacity>
      {isSubmitted && (
        <Text style={[styles.resultText, { color: resultRef.current.isCorrect ? AppColors.green : AppColors.red }]}>
          {resultRef.current.message}
        </Text>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: AppColors.lightGray,
    paddingHorizontal: 20,
  },
  audioButton: {
    backgroundColor: AppColors.green,
    padding: 20,
    borderRadius: width * 0.25,
    marginBottom: 20,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 5 },
    shadowOpacity: 0.2,
    shadowRadius: 5,
    elevation: 5,
  },
  instructionText: {
    fontSize: 18,
    color: AppColors.gray,
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

export default GiveAudioEnterWord;