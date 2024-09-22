import {
  FlatList,
  Image,
  Pressable,
  StyleSheet,
  Text,
} from "react-native";
import React from "react";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { Routes } from "../../../types/global";
import { MAIN } from "../../../constants/Colors";

interface TopicViewProp {
  topic: Topic;
}

const TopicView = ({topic}: TopicViewProp) => {
  const navigator = useNavigation<NavigationProp<Routes>>();

  return (
    <Pressable
      style={styles.topicContainer}
      onPress={() => navigator.navigate("Learn", { topicId: topic.id })}
    >
      <Image style={styles.topicCover} src={topic.cover} />
      <Text style={styles.topicName}>{topic.name}</Text>
    </Pressable>
  );
};

export default TopicView;

const styles = StyleSheet.create({
  topicContainer: {
    margin: 10,
    borderRadius: 5,
    backgroundColor: MAIN,
    padding: 3,
  },
  topicName: {
    textAlign: "center",
  },
  topicCover: {
    width: 100,
    height: 150,
  },
});
