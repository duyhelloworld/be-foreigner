import React, { useEffect, useState, useRef } from "react";
import {
  View,
  Text,
  Image,
  StyleSheet,
  Animated,
  Easing,
  FlatList,
} from "react-native";
import Svg, { Circle } from "react-native-svg";
import { ApiResponse, LessonHistory } from "../../../types/apimodels";
import apiClient from "../../../config/AxiosConfig";
import { ApiResponseCode, LessonStatus } from "../../../types/enum";
import { AppColors } from "../../../types/colors";
import GradientBackground from "../../common/GradientBackground";

const AnimatedCircle = Animated.createAnimatedComponent(Circle);

const LessonHistoryScreen = () => {
  const [histories, setHistories] = useState<LessonHistory[]>([]);
  const [isRefresh, setIsRefresh] = useState(false);

  async function load() {
    const response = await apiClient.get<ApiResponse>(
      "lesson-history/my-history"
    );
    if (response.data.code === ApiResponseCode.OK) {
      setHistories(response.data.data as LessonHistory[]);
    }
  }

  async function handleRefresh() {
    setIsRefresh(true);
    await load();
    setIsRefresh(false);
  }

  useEffect(() => {
    load();
  }, []);

  const AccuracyCircle = ({
    accuracy,
    animatedValue,
  }: {
    accuracy: number | null;
    animatedValue: Animated.Value;
  }) => {
    const radius = 30;
    const circumference = 2 * Math.PI * radius;

    const strokeDashoffset = animatedValue.interpolate({
      inputRange: [0, 100],
      outputRange: [circumference, 0],
    });

    const strokeColor = () => {
      if (!accuracy) {
        return AppColors.white;
      }
      if (accuracy >= 90) {
        return AppColors.green;
      } else if (accuracy >= 40) {
        return AppColors.yellowLight;
      } else {
        return AppColors.red;
      }
    }

    return (
      <View style={styles.accuracyContainer}>
        <Svg height="70" width="70" viewBox="0 0 70 70">
          <Circle
            cx="35"
            cy="35"
            r={radius}
            stroke={AppColors.white}
            strokeWidth="5"
            fill="none"
          />
          <AnimatedCircle
            cx="35"
            cy="35"
            r={radius}
            stroke={strokeColor()}
            strokeWidth="5"
            fill="none"
            strokeDasharray={circumference}
            strokeDashoffset={strokeDashoffset}
            rotation="-90"
            origin="35, 35"
          />
        </Svg>
        <Text style={styles.accuracyText}>{`${accuracy ?? 0}%`}</Text>
      </View>
    );
  };

  const LessonHistoryItem = React.memo(({ item, index }: { item: LessonHistory; index: number }) => {
    const fadeAnim = useRef(new Animated.Value(0)).current;
    const scaleAnim = useRef(new Animated.Value(0.8)).current;
    const circleAnim = useRef(new Animated.Value(0)).current;

    useEffect(() => {
      fadeAnim.setValue(0);
      scaleAnim.setValue(0.8);
      circleAnim.setValue(0);
      Animated.sequence([
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
            tension: 50,
            delay: index * 100,
            useNativeDriver: true,
          }),
        ]),
        Animated.timing(circleAnim, {
          toValue: item.accuracy ?? 100,
          duration: 1000,
          easing: Easing.out(Easing.cubic),
          useNativeDriver: true,
        }),
      ]).start();
    }, [item, index]);

    return (
      <Animated.View
        style={[
          styles.lessonCard,
          {
            opacity: fadeAnim,
            transform: [{ scale: scaleAnim }],
          },
        ]}
      >
        <View style={styles.lessonHeader}>
          <Image
            source={{ uri: item.lessonImage }}
            style={styles.lessonImage}
          />
          <View style={styles.lessonInfo}>
            <Text style={styles.lessonName}>{item.lessonName}</Text>
            <Text style={styles.lessonDate}>
              {item.startedAt}    {item.completedAt}
            </Text>
          </View>
        </View>
        <View style={styles.lessonDetails}>
          <View style={styles.detailItem}>
            <Text style={styles.detailLabel}>Tổng thời gian</Text>
            <Text style={styles.detailValue}>{item.totalTime ?? "----------"}</Text>
          </View>
          <View style={styles.detailItem}>
            <Text style={styles.detailLabel}>Trạng thái:</Text>
            <Text
              style={[
                styles.detailValue,
                {
                  color:
                    item.status === "COMPLETED" ? "#4CAF50" : "#FFC107",
                },
              ]}
            >
              {LessonStatus[item.status]}
            </Text>
          </View>
          <AccuracyCircle
            key={item.lessonId}
            accuracy={item.accuracy}
            animatedValue={circleAnim}
          />
        </View>
      </Animated.View>
    );
  });

  return (
    <GradientBackground style={styles.container}>
      <FlatList
        keyExtractor={item => item.historyId.toString()}
        refreshing={isRefresh}
        onRefresh={handleRefresh}
        data={histories}
        renderItem={({ item, index }) => (
          <LessonHistoryItem item={item} index={index} />
        )}
      />
    </GradientBackground>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#F0F4F8",
    padding: 16,
  },
  lessonCard: {
    backgroundColor: "#FFFFFF",
    borderRadius: 12,
    padding: 16,
    marginHorizontal: 10,
    marginBottom: 16,
    elevation: 3,
  },
  lessonHeader: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 12,
  },
  lessonImage: {
    width: 60,
    height: 60,
    borderRadius: 30,
    marginRight: 12,
  },
  lessonInfo: {
    flex: 1,
  },
  lessonName: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#333333",
  },
  lessonDate: {
    fontSize: 14,
    color: "#666666",
    marginTop: 4,
  },
  lessonDetails: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  detailItem: {
    flex: 1,
  },
  detailLabel: {
    fontSize: 14,
    color: "#666666",
    marginBottom: 4,
  },
  detailValue: {
    fontSize: 16,
    fontWeight: "bold",
    color: "#333333",
  },
  accuracyContainer: {
    alignItems: "center",
    justifyContent: "center",
    position: "relative",
    width: 70,
    height: 70,
  },
  accuracyText: {
    position: "absolute",
    fontSize: 14,
    fontWeight: "bold",
    color: AppColors.gray,
  },
});

export default LessonHistoryScreen;