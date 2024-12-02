import {
  ActivityIndicator,
  FlatList,
  StyleSheet,
  Text,
  View,
} from "react-native";
import React, { useEffect, useState } from "react";
import TodayWordView from "./TodayWordView";
import { ApiResponse, Lesson, PageResponse, Word } from "../../types/apimodels";
import { AppColors } from "../../types/colors";
import { ApiResponseCode } from "../../types/enum";
import LessonInfoView from "./LessonInfoView";
import apiClient from "../../config/AxiosConfig";

const PAGE_SIZE = 6;

const HomeScreen = () => {
  const [lessons, setLessons] = useState<Lesson[]>([]);
  const [todayWord, setTodayWord] = useState<Word>({} as Word);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [isRefreshing, setIsRefreshing] = useState(false);
  const [refreshScrollCount, setRefreshScrollCount] = useState(0);

  async function loadWord() {
    const wordResponse = await apiClient.get<ApiResponse>("word/today");
    if (wordResponse.data.code === ApiResponseCode.OK) {
      setTodayWord(wordResponse.data.data as Word);
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
    loadLesson(currentPage);
  }, [currentPage]);

  useEffect(() => {
    loadWord();
  }, []);

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
    setIsRefreshing(false);
  }

  return (
    <View style={styles.container}>
      
      <TodayWordView word={todayWord} />

      <FlatList
        data={lessons}
        renderItem={({ item }) => <LessonInfoView lesson={item} />}
        keyExtractor={(_, index) => index.toString()}
        onEndReached={handleLoadMore} 
        onEndReachedThreshold={0.5} 
        refreshing={isRefreshing} 
        onRefresh={handleRefresh} 
        ListFooterComponent={
          isLoading && currentPage > 0 ? (
            <ActivityIndicator size="large" color={AppColors.blue} />
          ) : null
        }
      />
    </View>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: { flex: 1, padding: 10 },
});
