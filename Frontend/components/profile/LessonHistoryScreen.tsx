

import React, { useEffect, useRef } from 'react';
import { View, Text, Image, StyleSheet, ScrollView, Animated, Easing, useAnimatedValue } from 'react-native';
import Svg, { Circle } from 'react-native-svg';
import { LessonHistory } from '../../types/apimodels';
import { LessonStatus } from '../../types/enum';

interface LessonHistoryProps {
  history?: LessonHistory[];
}

const defaultHistory: LessonHistory[] = [
  {
    lessonId: 1,
    lessonName: "Basic Vocabulary",
    lessonImage: "https://example.com/vocab.png",
    startAt: "2023-05-15T10:00:00Z",
    totalTime: "00:30:00",
    status: LessonStatus.COMPLETED,
    accuracy: 85,
  },
  {
    lessonId: 2,
    lessonName: "Grammar Essentials",
    lessonImage: "https://example.com/grammar.png",
    startAt: "2023-05-16T14:30:00Z",
    totalTime: "00:45:00",
    status: LessonStatus.COMPLETED,
    accuracy: 92,
  },
  {
    lessonId: 3,
    lessonName: "Advanced Conversation",
    lessonImage: "https://example.com/conversation.png",
    startAt: "2023-05-17T09:15:00Z",
    totalTime: "01:00:00",
    status: LessonStatus.ONGOING,
    accuracy: 78,
  },
];

const AnimatedCircle = Animated.createAnimatedComponent(Circle);

const LessonHistoryScreen: React.FC<LessonHistoryProps> = ({ history = defaultHistory }) => {
  const animatedValues = useRef(history.map(() => useAnimatedValue(0))).current;

  useEffect(() => {
    const animations = animatedValues.map((value, index) =>
      Animated.timing(value, {
        toValue: history[index].accuracy,
        duration: 1500,
        easing: Easing.out(Easing.cubic),
        useNativeDriver: true,
      })
    );

    Animated.stagger(300, animations).start();
  }, []);

  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', { year: 'numeric', month: 'short', day: 'numeric' });
  };

  const renderAccuracyCircle = (accuracy: number, animatedValue: Animated.Value) => {
    const radius = 30;
    const circumference = 2 * Math.PI * radius;

    const strokeDashoffset = animatedValue.interpolate({
      inputRange: [0, 100],
      outputRange: [circumference, 0],
    });

    return (
      <View style={styles.accuracyContainer}>
        <Svg width={radius * 2} height={radius * 2}>
          <Circle
            cx={radius}
            cy={radius}
            r={radius - 5}
            stroke="#E0E0E0"
            strokeWidth={5}
          />
          <AnimatedCircle
            cx={radius}
            cy={radius}
            r={radius - 5}
            stroke="#FF6B6B"
            strokeWidth={5}
            strokeDasharray={circumference}
            strokeDashoffset={strokeDashoffset}
            strokeLinecap="round"
          />
        </Svg>
        <Text style={styles.accuracyText}>{`${accuracy}%`}</Text>
      </View>
    );
  };

  return (
    <ScrollView style={styles.container}>
      {history.map((lesson, index) => (
        <Animated.View
          key={lesson.lessonId}
          style={[
            styles.lessonCard,
            {
              opacity: animatedValues[index].interpolate({
                inputRange: [0, 100],
                outputRange: [0, 1],
              }),
              transform: [
                {
                  translateY: animatedValues[index].interpolate({
                    inputRange: [0, 100],
                    outputRange: [50, 0],
                  }),
                },
              ],
            },
          ]}
        >
          <View style={styles.lessonHeader}>
            <Image source={{ uri: lesson.lessonImage }} style={styles.lessonImage} />
            <View style={styles.lessonInfo}>
              <Text style={styles.lessonName}>{lesson.lessonName}</Text>
              <Text style={styles.lessonDate}>{formatDate(lesson.startAt)}</Text>
            </View>
          </View>
          <View style={styles.lessonDetails}>
            <View style={styles.detailItem}>
              <Text style={styles.detailLabel}>Total Time:</Text>
              <Text style={styles.detailValue}>{lesson.totalTime}</Text>
            </View>
            <View style={styles.detailItem}>
              <Text style={styles.detailLabel}>Status:</Text>
              <Text
                style={[
                  styles.detailValue,
                  { color: lesson.status === LessonStatus.COMPLETED ? '#4CAF50' : '#FFC107' },
                ]}
              >
                {lesson.status}
              </Text>
            </View>
            {renderAccuracyCircle(lesson.accuracy, animatedValues[index])}
          </View>
        </Animated.View>
      ))}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F0F4F8',
    padding: 16,
  },
  lessonCard: {
    backgroundColor: '#FFFFFF',
    borderRadius: 12,
    padding: 16,
    marginBottom: 16,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 3,
  },
  lessonHeader: {
    flexDirection: 'row',
    alignItems: 'center',
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
    fontWeight: 'bold',
    color: '#333333',
  },
  lessonDate: {
    fontSize: 14,
    color: '#666666',
    marginTop: 4,
  },
  lessonDetails: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  detailItem: {
    flex: 1,
  },
  detailLabel: {
    fontSize: 14,
    color: '#666666',
    marginBottom: 4,
  },
  detailValue: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333333',
  },
  accuracyContainer: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  accuracyText: {
    position: 'absolute',
    fontSize: 14,
    fontWeight: 'bold',
    color: '#FF6B6B',
  },
});

export default LessonHistoryScreen;