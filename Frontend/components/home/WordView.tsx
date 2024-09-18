import React, { useState, useEffect } from "react";
import {
  View,
  Text,
  Image,
  Pressable,
  StyleSheet,
  ScrollView,
} from "react-native";
import { Audio } from "expo-av";
import { MaterialIcons } from "@expo/vector-icons";

interface WordViewProp {
  words: Word[]
}

const PROGRESSBAR_COLORS = {
  GREEN: "#4CAF50", 
  YELLOW: "#432321",
  ORANGE: "#321342"
};

const WordView = ({words} : WordViewProp) => {
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

  const toggleCard = () => {
    setIsFlipped(!isFlipped);
  };

  const handlePlayAudio = async () => {
    const { sound } = await Audio.Sound.createAsync({
      uri: words[currentCardIndex].audio ?? "",
    });
    await sound.playAsync();
  };

  const handleHint = () => {
    alert(words[currentCardIndex].hint);
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <View style={styles.progressBarContainer}>
        <View style={[styles.progressBar, { width: `${progress * 100}%` }]} />
      </View>
      <View style={styles.cardContainer}>
        <Pressable style={styles.card} onPress={toggleCard}>
          {!isFlipped ? (
            <View style={styles.wordContainer}>
              <Text style={styles.wordText}>
                {words[currentCardIndex].value}
              </Text>
            </View>
          ) : (
            <View style={styles.cardBack}>
              <Image
                source={{ uri: words[currentCardIndex].image }}
                style={styles.image}
                resizeMode="contain"
              />
              <View style={styles.meaningContainer}>
                <Text style={styles.meaningText}>
                  {words[currentCardIndex].mean}
                </Text>
                <Pressable
                  style={styles.audioButton}
                  onPress={handlePlayAudio}
                >
                  <MaterialIcons name="volume-up" size={24} color="#007AFF" />
                </Pressable>
              </View>
            </View>
          )}
        </Pressable>
      </View>
      <View style={styles.buttonContainer}>
        <Pressable
          style={[styles.button, styles.hintButton]}
          onPress={handleHint}
        >
          <MaterialIcons name="lightbulb-outline" size={24} color="#FFFFFF" />
          <Text style={styles.buttonText}>Hint</Text>
        </Pressable>
        <Pressable
          style={[
            styles.button,
            styles.nextButton,
            isCompleted && styles.disabledButton,
          ]}
          onPress={handleNextCard}
          disabled={isCompleted}
        >
          <Text style={styles.buttonText}>
            {isCompleted ? "Completed" : "Next"}
          </Text>
        </Pressable>
      </View>
    </ScrollView>
  );
}

export default WordView;

const styles = StyleSheet.create({
  container: {
    alignItems: "center",
    paddingVertical: 15,
  },
  progressBarContainer: {
    width: "90%",
    height: 10,
    backgroundColor: "#DDD",
    borderRadius: 4,
    overflow: "hidden",
    marginBottom: 20,
  },
  progressBar: {
    flex: 1,
    backgroundColor: PROGRESSBAR_COLORS.GREEN,
  },
  cardContainer: {
    width: "90%",
    aspectRatio: 3 / 2,
    marginBottom: 20,
  },
  card: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 20,
    shadowColor: "#888",
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 3,
    elevation: 10,
  },
  wordContainer: {
    backgroundColor: "#007AFF",
    padding: 20,
    borderRadius: 10,
  },
  wordText: {
    fontSize: 32,
    fontWeight: "bold",
    color: "#FFF",
    textAlign: "center",
  },
  cardBack: {
    width: "100%",
    height: "100%",
    justifyContent: "space-around",
    alignItems: "center",
    padding: 10,
  },
  image: {
    width: "80%",
    height: "50%",
    marginTop: 20,
    borderRadius: 10,
  },
  meaningContainer: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    marginTop: 10,
  },
  meaningText: {
    fontSize: 18,
    textAlign: "center",
    color: "#333333",
    flex: 1,
  },
  audioButton: {
    padding: 10,
  },
  buttonContainer: {
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
  button: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 25,
    marginHorizontal: 10,
  },
  nextButton: {
    backgroundColor: "#007AFF",
  },
  hintButton: {
    backgroundColor: "#FFA500",
  },
  disabledButton: {
    backgroundColor: "#CCCCCC",
  },
  buttonText: {
    color: "#FFFFFF",
    fontSize: 18,
    fontWeight: "bold",
    marginLeft: 5,
  },
});
