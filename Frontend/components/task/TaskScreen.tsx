import { ScrollView, StyleSheet, Text, View } from 'react-native'
import React, { useState } from 'react'
import { sampleTasks } from '../../utils/InitData';
import TaskItemView from './TaskItemView';
import { AppColors } from '../../types/colors';
import CircularProgressBar from './CircularProgressBar';

const TaskScreen = () => {

  const [progress, setProgress] = useState(65);

  // Update progress task from cache
  const tasks = sampleTasks();
  return (
    <View >
      <View style={styles.header}>
        <Text style={styles.headerTitle}>Nhiệm vụ của bạn</Text>
        <CircularProgressBar progress={progress} />
      </View>
      <Text style={styles.sectionTitle}>Task trong hôm này</Text>
      <ScrollView style={styles.taskList}>
        {tasks.map((task, index) => (
          <TaskItemView key={index} task={task} />
        ))}
      </ScrollView>
    </View>
  );
}

export default TaskScreen

const styles = StyleSheet.create({
  header: {
    backgroundColor: AppColors.green,
    padding: 20,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  headerTitle: {
    color: "white",
    fontSize: 24,
    fontWeight: "bold",
  },
  taskList: {
    padding: 20,
  },
  sectionTitle: {
    fontSize: 18,
    marginVertical: 10,
    fontWeight: "600",
    marginLeft: 10,
  },
})