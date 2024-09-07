import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import React, { useState } from "react";
import { Ionicons } from "@expo/vector-icons";

interface TodayWordExpandViewProp {
  title: string;
  data: string[] | string;
  isPrimary?: boolean;
}

const TodayWordExpandView = ({title, data, isPrimary = false} : TodayWordExpandViewProp) => {
  const [isVisible, setIsVisible] = useState(false);

  return (
    <View>
      <Pressable onPress={() => setIsVisible(!isVisible)} style={styles.button}>
        <Text style={styles.title}>{title}</Text>
        <Ionicons
          name="play"
          style={{ transform: [{ rotate: isVisible ? "90deg" : "0deg" }] }}
        />
      </Pressable>

      {isVisible ? (
        <View>
          {isPrimary ? <Text style={styles.item}>{data}</Text> : 
          (
            <FlatList
              data={data}
              renderItem={({ item }) => (
                <Text style={styles.item}>- {item}</Text>
              )}
            />
          )}
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
  item: {
    fontSize: 20,
  },
});
