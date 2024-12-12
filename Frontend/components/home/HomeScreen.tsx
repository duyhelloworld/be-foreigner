import { ActivityIndicator, FlatList, StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import TodayWordView from "./TodayWordView";
import { ApiResponse, Lesson, PageResponse, Word } from "../../types/apimodels";
import { AppColors } from "../../types/colors";
import { ApiResponseCode } from "../../types/enum";
import LessonInfoView from "./LessonInfoView";
import apiClient from "../../config/AxiosConfig";
import GradientBackground from "../common/GradientBackground";
import SoundPlayer from "react-native-sound-player";

const PAGE_SIZE = 6;

const HomeScreen = () => {
  const [lessons, setLessons] = useState<Lesson[]>([]);
  const [todayWord, setTodayWord] = useState<Word>({} as Word);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [isRefreshing, setIsRefreshing] = useState(false);
  const [refreshScrollCount, setRefreshScrollCount] = useState(0);
  const [refreshKey, setRefreshKey] = useState(0);
  
  async function loadWord() {
    const wordResponse = await apiClient.get<ApiResponse>("word/today");
    if (wordResponse.data.code === ApiResponseCode.OK) {
      const word = wordResponse.data.data as Word;
      SoundPlayer.loadUrl(word.audio);
      setTodayWord(word);
    } else {
      alert(wordResponse.data.data as string[]);
    }
  }

  async function loadLesson(pageNumber: number, append = false) {
    if (isLoading) return;
    setIsLoading(true);

    const lessonResponse = await apiClient.get<ApiResponse>(
      `lesson/suggest?pageNumber=${pageNumber}&pageSize=${PAGE_SIZE}`
    );
    if (lessonResponse.data.code === ApiResponseCode.OK) {
      const pageLesson = lessonResponse.data.data as PageResponse<Lesson>;
      setLessons((prev) =>
        append ? [...prev, ...pageLesson.items] : pageLesson.items
      );
      setTotalPage(pageLesson.totalPage);
    } else {
      alert(lessonResponse.data.data as string[]);
    }
    setIsLoading(false);
  }

  useEffect(() => {
    loadWord();
  }, []);

  useEffect(() => {
    loadLesson(currentPage);
  }, [currentPage]);

  async function handleLoadMore() {
    if (currentPage < totalPage && !isLoading) {
      const nextPage = currentPage + 1;
      setCurrentPage(nextPage);
      await loadLesson(nextPage, true);
      setIsLoading(false);
    } else {
      setRefreshScrollCount(refreshScrollCount + 1);
      if (refreshScrollCount > 2) {
        alert("Bạn đã cuộn hết danh sách rồi!");
        setRefreshScrollCount(0);
      }
    }
  }

  async function handleRefresh() {
    setIsRefreshing(true);
    await loadLesson(0);
    setCurrentPage(0);
    setRefreshKey((prevKey) => prevKey + 1);
    setIsRefreshing(false);
  }

  return (
    <GradientBackground>
      <TodayWordView word={todayWord} />
      <FlatList
        data={lessons}
        renderItem={({ item, index }) => (
          <LessonInfoView
            key={`${refreshKey}-${item.id}`}
            lesson={item}
            index={index}
          />
        )}
        keyExtractor={(item) => `${refreshKey}-${item.id}`}
        onEndReached={handleLoadMore}
        ListEmptyComponent={() => 
          !isLoading && (
            <View style={styles.emptyContainer}>
              <Text style={styles.emptyText}>
                Không có bài học nào để hiển thị
              </Text>
            </View>
          )
        }
        refreshing={isRefreshing}
        onRefresh={handleRefresh}
        ListFooterComponent={
          isLoading && currentPage > 0 ? (
            <ActivityIndicator size="large" color={AppColors.blue} />
          ) : null
        }
        
      />
    </GradientBackground>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: { 
    flex: 1, 
    padding: 10 
  },
  emptyContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 20,
  },
  emptyText: {
    fontSize: 16,
    color: AppColors.yellow,
    textAlign: "center",
  },
});
