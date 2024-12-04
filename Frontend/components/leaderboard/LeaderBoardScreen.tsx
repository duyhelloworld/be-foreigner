import React, { useEffect, useState, useRef } from "react";
import {
  FlatList,
  Image,
  StyleSheet,
  Text,
  View,
  Animated,
  ActivityIndicator,
} from "react-native";
import { ApiResponse, LeaderBoard } from "../../types/apimodels";
import { useUserStorage } from "../../hook/UserStorageHooks";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";
import { LinearGradient } from "expo-linear-gradient";
import { AppColors } from "../../types/colors";
import LeaderBoardUserView from "./LeaderBoardUserView";
import { useNavigation } from "@react-navigation/native";
import { BottomTabNavigationProp } from "@react-navigation/bottom-tabs";
import { HomeNavigatorParams } from "../../navigation/navigators/HomeNavigator";

const LeaderBoardScreen = () => {
  const [LeaderBoard, setLeaderBoard] = useState<LeaderBoard>();
  const [currentUser, setCurrentUser] = useState<string>();
  const [isLoading, setIsLoading] = useState(false);
  const [isRefreshing, setIsRefreshing] = useState(false);
  const [showNotification, setShowNotification] = useState(true);
  const [refreshKey, setRefreshKey] = useState(0);

  const navigator = useNavigation<BottomTabNavigationProp<HomeNavigatorParams>>();
  const userStorage = useUserStorage();
  const fadeAnim = useRef(new Animated.Value(0)).current;
  const translateY = useRef(new Animated.Value(50)).current;

  async function loadData() {
    setIsLoading(true);
    const current = await userStorage.getInfo();
    setCurrentUser(current?.username);
    const response = await apiClient.get<ApiResponse>("leaderboard");
    if (response.data.code === ApiResponseCode.OK) {
      const board = response.data.data as LeaderBoard;
      board.users.sort((u1, u2) => u1.userRank - u2.userRank);
      setLeaderBoard(board);
    }
    setIsLoading(false);
  }

  async function refreshData() {
    setIsRefreshing(true);
    await loadData();
    setRefreshKey((prevKey) => prevKey + 1);
    setIsRefreshing(false);
  }

  useEffect(() => {
    loadData();

    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: true,
      }),
      Animated.spring(translateY, {
        toValue: 0,
        friction: 4,
        useNativeDriver: true,
      }),
    ]).start();

    const timer = setTimeout(() => setShowNotification(false), 1500);
    return () => clearTimeout(timer);
  }, []);

  return (
    <LinearGradient
      colors={[AppColors.darkGreen, AppColors.green, AppColors.lightGreen]}
      style={styles.container}
    >
      <Animated.View
        style={[
          styles.body,
          { opacity: fadeAnim, transform: [{ translateY }] },
        ]}
      >
        <Image
          source={require("../../assets/ranking.png")}
          style={styles.LeaderBoardLogo}
        />
        <Text style={styles.title}>Bảng xếp hạng</Text>
        {isLoading ? (
          <ActivityIndicator />
        ) : (
          <FlatList
            onScroll={(e) => {
              const scrollY: number = e.nativeEvent.contentOffset.y;
              navigator.setOptions({
                tabBarStyle: { display: scrollY > 0 ? "flex" : "none" },
              });
            }}
            keyExtractor={(item) => item.userRank.toString()}
            data={LeaderBoard?.users}
            refreshing={isRefreshing}
            onRefresh={refreshData}
            renderItem={({ item, index }) => (
              <LeaderBoardUserView
                key={`${refreshKey}-${item.username}`}
                user={item}
                isMe={item.username === currentUser}
                showNotification={
                  item.username === currentUser && showNotification
                }
                index={index}
              />
            )}
          />
        )}
      </Animated.View>
    </LinearGradient>
  );
};

export default LeaderBoardScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 10,
  },
  body: {
    alignItems: "center",
    marginTop: 40,
    flex: 1,
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
    color: "#FFF",
    marginBottom: 20,
    textShadowOffset: { width: -1, height: 1 },
    textShadowRadius: 10,
  },
  LeaderBoardLogo: {
    width: 200,
    height: 200,
    marginBottom: 20,
  },
});
