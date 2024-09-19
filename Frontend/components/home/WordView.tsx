import React, { useState, useEffect } from "react";
import {
  View,
  Text,
  Pressable,
  StyleSheet,
} from "react-native";
import { Audio } from "expo-av";
import { Ionicons } from "@expo/vector-icons";
import {Image} from "expo-image";

interface WordViewProp {
  words: Word[];
}

const PROGRESSBAR_COLORS = {
  GREEN: "#4CAF50",
  YELLOW: "#FFEE00",
  ORANGE: "#FF4400",
};

const IMAGE_SIZE = {
  width: 100,
  height: 100,
};

const BUTTON_COLOR = {
  next: "#0AF",
  audio: "#07F",
  hint: "#FA0",
  diable: "#888"
};

const WordView = ({ words }: WordViewProp) => {
  const [currentCardIndex, setCurrentCardIndex] = useState(0);
  const [isFlipped, setIsFlipped] = useState(false);
  const [progress, setProgress] = useState(0);
  const [isCompleted, setIsCompleted] = useState(false);
  const wordCount = words.length;

  useEffect(() => {
    setProgress((currentCardIndex + 1) / wordCount);
    setIsCompleted(currentCardIndex === wordCount - 1);
  }, [currentCardIndex]);

  const handleNextCard = () => {
    if (currentCardIndex < wordCount - 1) {
      setCurrentCardIndex(currentCardIndex + 1);
      setIsFlipped(false);
    }
  };

  const handleCompleted = () => {
    alert("Hết câu hỏi rồi nhé bạn");
  };

  const toggleCard = () => {
    setIsFlipped(!isFlipped);
  };

  const handlePlayAudio = async () => {
    const { sound } = await Audio.Sound.createAsync({
      uri: words[currentCardIndex].audio,
    });
    await sound.playAsync();
  };

  const handleHint = () => {
    alert(words[currentCardIndex].hint);
  };

  return (
    <View style={styles.container}>
      <View style={styles.progressBarContainer}>
        <View style={[styles.progressBar, { width: `${progress * 100}%` }]} />
      </View>

      <View style={styles.cardContainer}>
        <Pressable style={styles.card} onPress={toggleCard}>
          {!isFlipped ? (
            <Text style={styles.wordText}>{words[currentCardIndex].value}</Text>
          ) : (
            <View style={styles.cardBack}>
              <Image
                source={{ uri: words[currentCardIndex].image }}
                style={styles.image}
              />
              <View style={styles.meaningContainer}>
                <Text style={styles.meaningText}>
                  {words[currentCardIndex].mean}
                </Text>
                <Ionicons
                  style={styles.audioButton}
                  onPress={handlePlayAudio}
                  name="volume-high"
                  size={24}
                />
              </View>
            </View>
          )}
        </Pressable>

        <View style={styles.buttonContainer}>
          <Ionicons
            style={[styles.button, styles.hintButton]}
            onPress={handleHint}
            name="bulb"
            size={24}
          />
          <Pressable
            style={[
              styles.button,
              styles.nextButton,
              isCompleted && styles.disabledButton,
            ]}
            onPress={!isCompleted ? handleNextCard : handleCompleted}
          >
            <Text style={styles.buttonText}>
              {isCompleted ? "Học xong" : "Tiếp đê bạn"}
            </Text>
          </Pressable>
        </View>
      </View>
    </View>
  );
};

export default WordView;

const styles = StyleSheet.create({
  container: {
    alignItems: "center",
    flex: 1,
  },
  progressBarContainer: {
    width: 320,
    height: 15,
    backgroundColor: "#DDD",
    borderRadius: 10,
    marginVertical: 20,
  },
  progressBar: {
    flex: 1,
    borderRadius: 10,
    backgroundColor: PROGRESSBAR_COLORS.GREEN,
  },
  cardContainer: {
    // code căn giữa, cấm sửa
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: "center",
    alignItems: "center",
  },
  card: {
    width: 300,
    backgroundColor: "#EEE",
    aspectRatio: 1.5,
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 20,
    shadowColor: "#444",
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 3,
    elevation: 10,
  },
  wordText: {
    fontSize: 32,
    fontWeight: "bold",
  },
  cardBack: {
    justifyContent: "center",
    alignItems: "center",
  },
  image: {
    width: IMAGE_SIZE.width,
    height: IMAGE_SIZE.height,
    marginTop: 20,
    borderRadius: 10,
  },
  meaningContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginTop: 10,
  },
  meaningText: {
    fontSize: 18,
  },
  audioButton: {
    padding: 10,
    color: BUTTON_COLOR.audio,
  },
  buttonContainer: {
    flexDirection: "row",
  },
  button: {
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 10,
    marginHorizontal: 10,
    color: "#FFF",
  },
  nextButton: {
    backgroundColor: BUTTON_COLOR.next,
  },
  hintButton: {
    backgroundColor: BUTTON_COLOR.hint,
  },
  disabledButton: {
    backgroundColor: BUTTON_COLOR.diable,
  },
  buttonText: {
    fontSize: 18,
    color: "#FFF",
  },
});
