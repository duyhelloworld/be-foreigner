import React, { useEffect, useState, useRef } from "react";
import {
  FlatList,
  Image,
  StyleSheet,
  Text,
  View,
  Animated,
  ActivityIndicator,
  TouchableOpacity,
} from "react-native";
import { ApiResponse, LeaderBoard } from "../../types/apimodels";
import { useUserStorage } from "../../hook/UserStorageHooks";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode, LeaderBoardType } from "../../types/enum";
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
  const [selectedTab, setSelectedTab] = useState<keyof typeof LeaderBoardType>("WEEKLY");

  const navigator = useNavigation<BottomTabNavigationProp<HomeNavigatorParams>>();
  const userStorage = useUserStorage();
  const fadeAnim = useRef(new Animated.Value(0)).current;
  const translateY = useRef(new Animated.Value(50)).current;

  async function loadData(leaderboardType: keyof typeof LeaderBoardType) {
    setIsLoading(true);
    const current = await userStorage.getInfo();
    setCurrentUser(current?.username);
    const response = await apiClient.get<ApiResponse>(`leaderboard?leaderBoardType=${leaderboardType}`);
    if (response.data.code === ApiResponseCode.OK) {
      const board = response.data.data as LeaderBoard;
      board.users.sort((u1, u2) => u1.userRank - u2.userRank);
      setLeaderBoard(board);
    }
    setIsLoading(false);
  }

  async function refreshData() {
    setIsRefreshing(true);
    await loadData(selectedTab);
    setRefreshKey((prevKey) => prevKey + 1);
    setIsRefreshing(false);
  }

  useEffect(() => {
    loadData(selectedTab);

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
  }, [selectedTab]);

  const renderTabs = () => {
    return (
      <View style={styles.tabsContainer}>
        {Object.keys(LeaderBoardType).map((tabKey) => (
          <TouchableOpacity
            key={tabKey}
            style={[
              styles.tabButton,
              selectedTab === tabKey && styles.activeTabButton,
            ]}
            onPress={() => setSelectedTab(tabKey as keyof typeof LeaderBoardType)}
          >
            <Text
              style={[
                styles.tabText,
                selectedTab === tabKey && styles.activeTabText,
              ]}
            >
              {LeaderBoardType[tabKey as keyof typeof LeaderBoardType]}
            </Text>
          </TouchableOpacity>
        ))}
      </View>
    );
  };

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
        {renderTabs()}
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
  tabsContainer: {
    flexDirection: "row",
    marginBottom: 20,
  },
  tabButton: {
    flex: 1,
    padding: 10,
    backgroundColor: AppColors.gray,
    alignItems: "center",
    marginHorizontal: 5,
    borderRadius: 10,
  },
  activeTabButton: {
    backgroundColor: AppColors.blue,
  },
  tabText: {
    color: "#FFF",
  },
  activeTabText: {
    fontWeight: "bold",
  },
});
