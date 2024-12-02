import { FlatList, Image, StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import RankingUserView from "./RankingUserView";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/colors";
import { ApiResponse, Ranking } from "../../types/apimodels";
import { useUserStorage } from "../../hook/UserStorageHooks";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";

const RankingScreen = () => {
  const [ranking, setRanking] = useState<Ranking>();
  const [currentUser, setCurrentUser] = useState<string>();

  const userStorage = useUserStorage();

  const [showNotification, setShowNotification] = useState(true);

  useEffect(() => {
    async function load() {
      const current = await userStorage.getInfo();
      setCurrentUser(current?.username);
    }
    load();
    const timer = setTimeout(() => setShowNotification(false), 1500);
    return () => clearTimeout(timer);
  }, [userStorage]);

  async function loadData() {
    const response = await apiClient.get<ApiResponse>("ranking");
    if (response.data.code === ApiResponseCode.OK) {
      setRanking(response.data.data as Ranking);
    }
  }

  useEffect(() => {
    loadData();
  }, [])

  return (
    <View style={styles.container}>
      <View style={styles.headingContainer}>
        <Text style={styles.title}>Bảng xếp hạng</Text>
        <Ionicons name="reload" color={AppColors.blue} size={30} onPress={loadData} />
      </View>
      <View style={styles.body}>
        <Image
          source={require("../../assets/ranking.png")}
          style={styles.rankingLogo}
        />
        <FlatList
          keyExtractor={(item) => item.username}
          data={ranking?.users}
          renderItem={({ item }) => (
            <RankingUserView
              user={item}
              isMe={item.username === currentUser}
              showNotification={
                item.username === currentUser && showNotification
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
    marginHorizontal: 10,
  },
  headingContainer: {
    flexDirection: "row",
    marginTop: 10,
    justifyContent: "space-between",
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
