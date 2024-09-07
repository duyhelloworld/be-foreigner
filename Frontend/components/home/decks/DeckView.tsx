import { FlatList, Image, Pressable, StyleSheet, Text, View } from "react-native";
import React from "react";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { Routes } from "../../../types/global";

interface DeckViewProp {
  decks: Deck[];
  title: string;
}

const DeckView = (prop: DeckViewProp) => {
  const navigator = useNavigation<NavigationProp<Routes>>();

  return (
    <View>
      <Text style={styles.title}>{prop.title}</Text>
      <FlatList
        horizontal
        data={prop.decks}
        renderItem={({ item }) => (
          <Pressable style={styles.deckContainer} 
          onPress={() => navigator.navigate("Learn", {deckId: item.id} )}
          >
            <Image style={styles.deckCover} src={item.cover} />
            <Text style={styles.deckName}>{item.name}</Text>
          </Pressable>
        )}
      />
    </View>
  );
};

export default DeckView;

const styles = StyleSheet.create({
  title: {
    paddingLeft: 10,
    paddingTop: 10,
  },
  deckContainer: {
    margin: 10,
    borderRadius: 5,
    elevation: 14,
    backgroundColor: "#C4FF61",
    padding: 3,
  },
  deckName: {
    textAlign: "center",
  },
  deckCover: {
    width: 100,
    height: 150,
  },
});
