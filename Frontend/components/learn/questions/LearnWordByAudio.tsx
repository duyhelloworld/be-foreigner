import { Dimensions, StyleSheet, Text, View } from "react-native";
import React, { useContext, useEffect } from "react";
import { LearnScreenContext } from "../LearnScreenHooks";
import { AppColors } from "../../../types/colors";
import { Ionicons } from "@expo/vector-icons";
import { Sound } from "expo-av/build/Audio";

interface LearnWordByAudioProp {
  audio: string;
  mean: string;
  value: string;
}

const { width } = Dimensions.get("window");

const LearnWordByAudio = ({ audio, mean, value }: LearnWordByAudioProp) => {
  const resultRef = useContext(LearnScreenContext);
  resultRef.current = {
    enabled: true,
    isCorrect: true,
    message: mean,
  };

  useEffect(() => {
    playAudio();
  }, [audio])
  
  async function playAudio() {
    const { sound } = await Sound.createAsync({ uri: audio });
    await sound.playAsync();
  }

  return (
    <View style={styles.container}>
      <Ionicons name="volume-high" 
        size={width * 0.4} 
        style={styles.audioIcon} 
        onPress={playAudio} 
      />
      <Text style={styles.valueText}>{value}</Text>
    </View>
  );
};

export default LearnWordByAudio;

const styles = StyleSheet.create({
  container: {
    alignItems: "center",
    justifyContent: "center",
    flex: 0.9
  },
  audioIcon: {
    color: AppColors.white,
    backgroundColor: AppColors.green,
    padding: 30,
    alignItems: 'center',
    borderRadius: width * 0.5,
    shadowColor: "#000",
    shadowOffset: { width: 2, height: 10 },
    shadowOpacity: 0.3,
    shadowRadius: 10,
    elevation: 10,
  },
  valueText: {
    color: AppColors.gray,
    fontSize: 30,
    marginTop: 20,
    lineHeight: 40,
    letterSpacing: 0.5,
  },
});