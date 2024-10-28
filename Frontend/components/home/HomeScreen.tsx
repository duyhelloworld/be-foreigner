import { FlatList, StyleSheet, View } from "react-native";
import React from "react";
import { sampleLessons, sampleWord } from "../../utils/InitData";
import TodayWordView from "./TodayWordView";
import UserInfoView from "./UserInfoView";

const HomeScreen = () => {
  const lessons = sampleLessons();
  return (
    <View>
      <TodayWordView word={sampleWord()} />
      <FlatList
        data={lessons}
        renderItem={({ item }) => <UserInfoView lesson={item} />}
      />
    </View>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({});
