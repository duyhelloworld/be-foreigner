import { FlatList, Image, StyleSheet, View } from "react-native";
import React from "react";
import { sampleRanking } from "../../utils/InitData";
import RankingUserView from "./RankingUserView";

const RankingScreen = () => {
  const { users } = sampleRanking();
  const current = users[2];

  return (
    <View>
      <View style={styles.headingContainer}>
        <Image
          source={{ uri: current.avatar, width: 40, height: 40 }}
          style={styles.currentUserAvatar}
        />
        <Image
          source={require("../../assets/ranking.png")}
          style={styles.rankingLogo}
        />
      </View>
      <FlatList
        data={users}
        renderItem={({ item }) => <RankingUserView user={item} />}
      />
    </View>
  );
};

export default RankingScreen;

const styles = StyleSheet.create({
  headingContainer: {
    alignItems: "center",
    marginHorizontal: 20,
  },
  currentUserAvatar: {
    top: 20,
    left: 20,
  },
  rankingLogo: {
    width: 200,
    height: 180,
  },
});
