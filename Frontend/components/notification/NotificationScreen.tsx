import React, { useEffect } from "react";
import { View, Text, StyleSheet, FlatList, Pressable } from "react-native";
import { Notification } from "../../types/apimodels";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";

const NotificationScreen: React.FC = () => {
  const [notifications, setNotifications] = React.useState<Notification[]>([]);
  const notificationStorage = useNotificationStorage();

  useEffect(() => {
    async function loadNoti() {
      let noti = await notificationStorage.getNotifications();
      setNotifications(noti!);
    }
    loadNoti();
  }, [notificationStorage, notifications]);

  const handleNotificationPress = async (id: number) => {
    setNotifications(
      notifications.map((notification) =>
        notification.id === id
          ? { ...notification, isRead: true }
          : notification
      )
    );
    await notificationStorage.markRead(id);
  };

  const renderNotification = ({ item }: { item: Notification }) => (
    <Pressable
      onPress={() => handleNotificationPress(item.id)}
      style={[
        styles.notificationItem,
        item.isRead ? styles.readNotification : styles.unreadNotification,
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
        renderItem={renderNotification}
        keyExtractor={(item) => item.id.toString()}
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
