import { StyleSheet, Text, View } from "react-native";
import React, { useContext, useEffect, useState } from "react";
import { LearnContext } from "../../../context/LearnContext";
import { AppColors } from "../../../types/colors";
import { Ionicons } from "@expo/vector-icons";
import SoundPlayer from "react-native-sound-player";

interface LearnWordByAudioProp {
  audio: string;
  mean: string;
  value: string;
}

const LearnWord = ({ audio, mean, value }: LearnWordByAudioProp) => {
  const resultRef = useContext(LearnContext);
  resultRef.current = {
    enabled: true,
    isCorrect: true,
    message: mean,
  };

  useEffect(() => {
    play();
  }, [])

  function play() {
    SoundPlayer.playUrl(audio);
  }

  return (
    <View style={styles.container}>
      <Ionicons name="volume-high" 
        size={40}
        style={styles.audioIcon} 
        onPress={play} 
      />
      <Text style={styles.valueText}>{value}</Text>
    </View>
  );
};

export default LearnWord;

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
    borderRadius: 40,
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