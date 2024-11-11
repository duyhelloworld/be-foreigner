import { FlatList, Image, StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import { sampleRanking } from "../../utils/InitData";
import RankingUserView from "./RankingUserView";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";

const RankingScreen = () => {
  const { users } = sampleRanking();
  const current = users[2];

  const [showNotification, setShowNotification] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => setShowNotification(false), 1500);
    return () => clearTimeout(timer);
  }, []);

  return (
    <View style={styles.container}>
      <View style={styles.headingContainer}>
        <Image source={{ uri: current.avatar }} style={styles.avatar} />
        <Text style={styles.title}>Bảng xếp hạng</Text>
        <Ionicons name="reload" color={AppColors.blue} size={30} />
      </View>
      <View style={styles.body}>
        <Image
          source={require("../../assets/ranking.png")}
          style={styles.rankingLogo}
        />
        <FlatList
          keyExtractor={(item) => item.username}
          data={users}
          renderItem={({ item }) => (
            <RankingUserView
              user={item}
              isMe={item.username === current.username}
              showNotification={
                item.username === current.username && showNotification
              }
            />
          )}
        />
      </View>
    </View>
  );
};

export default RankingScreen;

const styles = StyleSheet.create({
  container: {
    marginHorizontal: 20,
  },
  headingContainer: {
    flexDirection: "row",
    marginTop: 10,
    justifyContent: "space-between",
  },
  avatar: {
    width: 40,
    height: 40,
    borderRadius: 20,
  },
  title: {
    fontSize: 20,
    fontWeight: "600",
  },
  body: {
    alignItems: "center",
    marginTop: 10,
  },
  rankingLogo: {
    width: "50%",
    height: 180,
    marginBottom: 20,
  },
});
