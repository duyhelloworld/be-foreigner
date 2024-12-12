import React, { useEffect, useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  Pressable,
  ActivityIndicator,
} from "react-native";
import { ApiResponse, Notification } from "../../types/apimodels";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";
import apiClient from "../../config/AxiosConfig";
import { ApiResponseCode } from "../../types/enum";

const NotificationScreen: React.FC = () => {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [refreshing, setRefreshing] = useState(false);
  const notificationStorage = useNotificationStorage();

  async function loadNoti() {
    let noti = await notificationStorage.getNotifications();
    setNotifications(noti);
  }

  async function refresh() {
    setRefreshing(true);
    const response = await apiClient.get<ApiResponse>("remind/sync");
    if (response.data.code === ApiResponseCode.OK) {
      await notificationStorage.addAll(response.data.data as Notification[]);
    }
    setRefreshing(false);
  }

  useEffect(() => {
    loadNoti();
  }, [notificationStorage]);

  const handleNotificationPress = async (id: number) => {
    const updated = notifications.map((notification) =>
      notification.id === id ? { ...notification, read: true } : notification
    );
    await notificationStorage.markRead(id);
    setNotifications(updated);
  };

  const renderNotification = ({ item }: { item: Notification }) => (
    <Pressable
      onPress={() => handleNotificationPress(item.id)}
      style={[
        styles.notificationItem,
        item.read ? styles.readNotification : styles.unreadNotification,
      ]}
    >
      <Text style={styles.notificationTitle}>{item.title}</Text>
      <Text style={styles.notificationContent}>{item.content}</Text>
    </Pressable>
  );

  const renderEmptyState = () => (
    <View style={styles.emptyState}>
      <Text style={styles.emptyStateText}>Không có thông báo nào</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Thông báo</Text>
      <FlatList
        data={notifications}
        refreshing={refreshing}
        onRefresh={refresh}
        renderItem={renderNotification}
        ListEmptyComponent={renderEmptyState}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 30,
    backgroundColor: "#f5f5f5",
  },
  loading: {
    alignItems: "center",
    justifyContent: "center",
  },
  header: {
    fontSize: 24,
    fontWeight: "bold",
    padding: 16,
    backgroundColor: "#ffffff",
  },
  notificationItem: {
    backgroundColor: "#ffffff",
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: "#e0e0e0",
  },
  unreadNotification: {
    backgroundColor: "#e8f4fd",
  },
  readNotification: {
    backgroundColor: "#ffffff",
  },
  notificationTitle: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 8,
  },
  notificationContent: {
    fontSize: 16,
  },
  emptyState: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 16,
  },
  emptyStateText: {
    fontSize: 18,
    color: "#757575",
  },
});

export default NotificationScreen;
