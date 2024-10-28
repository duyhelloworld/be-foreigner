import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { Ionicons } from '@expo/vector-icons'
import { AppColors } from '../../types/Colors'
import { useAppParams } from '../../hook/AppNavigationHooks'
const CompletedLessonScreen = () => {

  const params = useAppParams('LearnNav', 'CompletedLessonScreen');
  
  if (!params) {
    return;
  }

  return (
    <View style={styles.container}>
      <Text>
        Đã hoàn thành khóa học <Text style={styles.lessonText}>{params.name}</Text>
      </Text>
      <View style={styles.statisticContainer}>
        <View style={styles.statistic}>
          <Ionicons name="diamond" size={100} color={AppColors.blue}/>
          <Text>+ {params.diamonds}</Text>
        </View>
      </View>
    </View>
  );
}

export default CompletedLessonScreen

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
  },
  lessonText: {
    color: AppColors.grayDark
  },
  statisticContainer: {

  },
  statistic: {
    
  }
})