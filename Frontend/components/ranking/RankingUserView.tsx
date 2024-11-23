import { Image, Pressable, StyleSheet, Text, View } from "react-native";
import React from "react";
import { RankingUser } from "../../types/apimodels";
import { NavigationProp, useNavigation } from "@react-navigation/native";
import { RootNavigatorParams } from "../../navigation/AppNavigation";
import { AppColors } from "../../types/colors";

interface RankingUserViewProp {
  user: RankingUser;
  isMe: boolean;
  showNotification: boolean;
}

const RankingUserView = ({ user, isMe, showNotification }: RankingUserViewProp) => {

  function onPress() {
  }

  return (
    <Pressable
      style={[styles.container, isMe ? styles.isMe : null]}
      onPress={onPress}
    >
      {showNotification && (
        <View style={styles.notification}>
          <Text style={styles.notificationText}>Bạn ở đây</Text>
        </View>
      )}
      <Text style={styles.rankIndex}>{user.userRank}</Text>
      <Image src={user.avatar} style={styles.avatar} />
      <Text style={styles.username}>{user.username}</Text>
      <Text style={styles.exp}>{user.elo}</Text>
    </Pressable>
  );
};

export default RankingUserView;

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    paddingVertical: 12,
    paddingHorizontal: 20,
    minWidth: "90%",
  },
  isMe: {
    backgroundColor: AppColors.lightGreen,
    borderRadius: 20,
  },
  rankIndex: {
    fontSize: 18,
    fontWeight: "bold",
    width: 50,
  },
  username: {
    fontSize: 16,
    paddingLeft: 10,
  },
  avatar: {
    width: 35,
    height: 35,
    borderRadius: 20,
    borderWidth: 2,
    borderColor: AppColors.purple,
  },
  exp: {
    fontSize: 16,
    fontWeight: "bold",
    color: AppColors.blue,
    textAlign: "right",
    width: 70,
  },
  notification: {
    position: "absolute",
    top: -10,
    backgroundColor: AppColors.blue,
    padding: 6,
    borderRadius: 10,
    alignSelf: "center",
  },
  notificationText: {
    color: AppColors.white,
    fontSize: 12,
  },
});
