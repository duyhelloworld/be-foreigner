import React, { useEffect } from "react";
import { Image, Pressable, StyleSheet, Text, View, Animated, useAnimatedValue } from "react-native";
import { LeaderBoardUser } from "../../types/apimodels";
import { AppColors } from "../../types/colors";

interface LeaderBoardUserViewProp {
  user: LeaderBoardUser;
  isMe: boolean;
  showNotification: boolean;
  index: number;
}

const LeaderBoardUserView = ({ user, isMe, showNotification, index }: LeaderBoardUserViewProp) => {
  const fadeAnim = useAnimatedValue(0);
  const scaleAnim = useAnimatedValue(0.5);

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 500,
        delay: index * 100,
        useNativeDriver: true,
      }),
      Animated.spring(scaleAnim, {
        toValue: 1,
        friction: 4,
        delay: index * 100,
        useNativeDriver: true,
      }),
    ]).start();
  }, [user, index]);

  function onPress() {
    alert(`${user.username} - Hạng ${user.userRank} -  Chiến lực ${user.elo}`);
  }

  const getRankColor = () => {
    switch (user.userRank) {
      case 1: return AppColors.yellow;
      case 2: return AppColors.gray;
      case 3: return AppColors.bronze;
      default: return AppColors.darkGreen;
    }
  };

  return (
    <Animated.View style={{ opacity: fadeAnim, transform: [{ scale: scaleAnim }] }}>
      <Pressable
        style={[styles.container, {backgroundColor: getRankColor()}]}
        onPress={onPress}
      >
        {showNotification && (
          <View style={styles.notification}>
            <Text style={styles.notificationText}>Bạn ở đây</Text>
          </View>
        )}
        <View style={styles.rankContainer}>
          <Text style={styles.rankIndex}>{user.userRank}</Text>
        </View>
        <Text style={[styles.username, isMe ? styles.isMe : null]}>{user.username}</Text>
        <Image source={{ uri: user.avatar }} style={styles.avatar} />
        <Text style={styles.exp}>{user.elo}</Text>
      </Pressable>
    </Animated.View>
  );
};

export default LeaderBoardUserView;

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    paddingVertical: 12,
    paddingHorizontal: 20,
    minWidth: "90%",
    borderRadius: 20,
    marginVertical: 10,
    elevation: 10,
  },
  isMe: {
    fontWeight: '800'
  },
  rankContainer: {
    width: 40,
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
  },
  rankIndex: {
    fontSize: 20,
    fontWeight: "900",
  },
  username: {
    fontSize: 16,
    paddingLeft: 10,
    flex: 1,
  },
  avatar: {
    width: 40,
    height: 40,
    borderRadius: 20,
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
    top: -15,
    backgroundColor: AppColors.blue,
    padding: 6,
    borderRadius: 10,
    alignSelf: "center",
    zIndex: 1,
  },
  notificationText: {
    color: AppColors.white,
    fontSize: 12,
  },
});