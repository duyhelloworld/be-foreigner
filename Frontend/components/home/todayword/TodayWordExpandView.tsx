import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useState } from "react";
import { Ionicons } from "@expo/vector-icons";

interface TodayWordExpandViewProp {
  title: string;
  mean?: string;
  examples?: Example[];
}

const TodayWordExpandView = (prop: TodayWordExpandViewProp) => {
  const [isVisible, setIsVisible] = useState(false);

  return (
    <View>
      <Pressable onPress={() => setIsVisible(!isVisible)} style={styles.button}>
        <Text style={styles.title}>{prop.title}</Text>
        <Ionicons
          name="play"
          style={{ transform: [{ rotate: isVisible ? "90deg" : "0deg" }] }}
        />
      </Pressable>

      {isVisible ? (
        <View>
          {prop.mean ? <Text style={styles.mean}>{prop.mean}</Text> : null}
          {prop.examples && <FlatList
            data={prop.examples}
            renderItem={({ item }) => (
              <View>
                <Text style={styles.sentense}>- {item.sentense}</Text>
                <Text style={styles.sentenseMean}>{item.mean}</Text>
              </View>
            )}
          />}
        </View>
      ) : null}
    </View>
  );
};

export default TodayWordExpandView;

const styles = StyleSheet.create({
  dropDown: {
    marginLeft: 30,
  },
  data: {
    flex: 1,
  },
  title: {
    width: 55,
  },
  button: {
    flexDirection: "row",
    marginBottom: 5,
    alignItems: "center",
    paddingHorizontal: 5,
  },
  close: {
    tintColor: "#D00",
  },
  sentense: {
    fontSize: 20,
  },
  mean: {
    fontSize: 30,
  },
  sentenseMean: {
    fontSize: 13,
  }
});
