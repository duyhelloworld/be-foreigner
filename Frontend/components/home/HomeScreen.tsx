import {
  ActivityIndicator,
  FlatList,
  StyleSheet,
  Text,
  View,
} from "react-native";
import React, { useEffect, useState } from "react";
import TodayWordView from "./TodayWordView";
import {
  ApiResponse,
  Lesson,
  PageResponse,
  Word,
} from "../../types/apimodels";
import { AppColors } from "../../types/colors";
import { ApiResponseCode } from "../../types/enum";
import LessonInfoView from "./LessonInfoView";
import apiClient from "../../config/AxiosConfig";

const PAGE_SIZE = 10;

const HomeScreen = () => {
  const [lessons, setLessons] = useState<Lesson[]>([]);
  const [todayWord, setTodayWord] = useState<Word>({} as Word);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string[]>();
  const [isRefreshing, setIsRefreshing] = useState(false);

  // Gọi API để lấy dữ liệu
  const loadData = async (pageNumber: number, append = false) => {
    if (isLoading) return;
    setIsLoading(true);

    const lessonResponse = await apiClient.get<ApiResponse>(
      `lesson/suggest?pageNumber=${pageNumber}&pageSize=${PAGE_SIZE}`
    );
    if (lessonResponse.data.code === ApiResponseCode.OK) {
      const pageLesson = lessonResponse.data.data as PageResponse<Lesson>;
      // thêm mới lesson
      setLessons((prev) =>
        append ? [...prev, ...pageLesson.items] : pageLesson.items
      );
      setTotalPage(pageLesson.totalPage);
    } else {
      setErrorMessage(lessonResponse.data.data as string[]);
    }

    const wordResponse = await apiClient.get<ApiResponse>("word/today");
    if (wordResponse.data.code === ApiResponseCode.OK) {
      setTodayWord(wordResponse.data.data as Word);
    } else {
      setErrorMessage(wordResponse.data.data as string[]);
    }
    setIsLoading(false);
  };

  useEffect(() => {
    loadData(currentPage); // Gọi API khi trang thay đổi
  }, [currentPage]);

  const handleLoadMore = () => {
    if (currentPage < totalPage && !isLoading) {
      const nextPage = currentPage + 1;
      setCurrentPage(nextPage);
      loadData(nextPage, true);
    }
  };

  const handleRefresh = async () => {
    setIsRefreshing(true);
    await loadData(1); // Tải lại từ trang 1
    setCurrentPage(1);
    setIsRefreshing(false);
  };

  return (
    <View style={styles.container}>
      {errorMessage ? (
        <FlatList
          data={errorMessage}
          renderItem={({ item }) => <Text>{item}</Text>}
        />
      ) : (
        <>
          <TodayWordView word={todayWord} />
          <FlatList
            data={lessons}
            renderItem={({ item }) => <LessonInfoView lesson={item} />}
            keyExtractor={(_, index) => index.toString()}
            onEndReached={handleLoadMore} // Gọi khi cuộn tới đáy
            onEndReachedThreshold={0.5} // Gọi khi còn 50% danh sách chưa cuộn
            refreshing={isRefreshing} // Trạng thái tải lại
            onRefresh={handleRefresh} // Tải lại khi kéo xuống
            ListFooterComponent={
              isLoading && currentPage > 1 ? (
                <ActivityIndicator size="large" color={AppColors.blue} />
              ) : null
            }
          />
        </>
      )}
    </View>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: { flex: 1, padding: 10 },
});
