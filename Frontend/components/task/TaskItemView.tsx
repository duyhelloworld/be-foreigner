import { StyleSheet, Text, View } from "react-native";
import { Task } from "../../types/apimodels";
import { Ionicons } from "@expo/vector-icons";
import { AppColors } from "../../types/Colors";

interface TaskItemViewProp {
  task: Task;
}

const TaskItemView = ({ task }: TaskItemViewProp) => {
  return (
    <View style={styles.taskItem}>
      <Ionicons name="book" style={styles.taskIcon} />
      <View style={styles.taskContent}>
        <Text style={styles.taskName}>{task.name}</Text>
        <View style={styles.progressBar}>
          <View
            style={[
              styles.progressFill,
              { width: `${(task.current / task.total) * 100}%` },
            ]}
          />
        </View>
      </View>
      <Text style={styles.taskProgress}>
        {task.current}/{task.total}
      </Text>
    </View>
  );
};

export default TaskItemView

const styles = StyleSheet.create({
  taskItem: {
    backgroundColor: "white",
    borderRadius: 8,
    padding: 15,
    marginBottom: 10,
    flexDirection: "row",
    alignItems: "center",
  },
  taskIcon: {
    backgroundColor: AppColors.green,
    borderRadius: 20,
    padding: 8,
    marginRight: 15,
  },
  taskContent: {
    flex: 1,
  },
  taskName: {
    fontWeight: "600",
    marginBottom: 5,
  },
  progressBar: {
    height: 5,
    backgroundColor: AppColors.white,
    borderRadius: 2.5,
  },
  progressFill: {
    height: "100%",
    backgroundColor: AppColors.green,
    borderRadius: 2.5,
  },
  taskProgress: {
    fontSize: 12,
    color: "#666",
    marginLeft: 10,
  },
});
