import React, { useEffect, useState } from "react";
import { View, Text, Pressable, StyleSheet, FlatList } from "react-native";
import { Notification } from "../../types/apimodels";
import { useNotificationStorage } from "../../hook/NotificationStorageHook";
import GradientBackground from "../common/GradientBackground";
import { AppColors } from "../../types/colors";

export default function NotificationScreen() {
  const [notifications, setNotifications] = useState<Notification[]>([]);

  const notificationStorage = useNotificationStorage();

  useEffect(() => {
    async function loadNoti() {
      let noti = await notificationStorage.getNotifications();
      if (noti.length === 0) {
        noti = [
          {
            id: "1",
            title: "Hú lô, lại là Liongo đây!🦁",
            content: "Hình như lâu rồi tôi chưa thấy bạn đó",
            isRead: false,
            sendAt: Date.now(),
          },
          {
            id: "2",
            title: "🤗Ôi bạn ơi🤗, sao bạn chưa vào học vậy",
            content:
              "Lần cuối bạn học cùng tôi là khi nào nhỉ? Hay là để tôi nhắc bạn nhớ nhé!",
            isRead: true,
            sendAt: Date.now(),
          },
          {
            id: "3",
            title: "Phạm Duy ơi ❤️❤️❤️ bạn ỉm hơi lâu rồi á :D",
            content:
              "Hình như tôi chiều các em quá nên các em hư đúng không? Vào học ngayyyy",
            isRead: false,
            sendAt: Date.now(),
          },
          {
            id: "4",
            title: "Gào gào gào 🦁🦁🦁, bạn Phạm Duy có ở đây không?",
            content: "Anh nhắc em, vào học ngay cho anh",
            isRead: false,
            sendAt: Date.now(),
          },
          {
            id: "5",
            title: "Duy Phạm ơi! 🕒 Đến giờ học rồi nà",
            content: "Vừng ơi mở ra...",
            isRead: false,
            sendAt: Date.now(),
          },
        ];
      }
      setNotifications(noti);
    }
    loadNoti();
  }, [notificationStorage]);

  const markAllAsRead = () => {
    setNotifications((prev) =>
      prev.map((notification) => ({ ...notification, isRead: true }))
    );
  };

  const markAsRead = async (id: string) => {
    const updatedNotifications = notifications.map((notification) =>
      notification.id === id ? { ...notification, isRead: true } : notification
    );
    setNotifications(updatedNotifications);
  };

  return (
    <GradientBackground>
      <View style={styles.header}>
        <Pressable onPress={markAllAsRead}>
          <Text style={styles.markAllButton}>Đánh dấu tất cả là đã đọc</Text>
        </Pressable>
      </View>
      <FlatList
        data={notifications}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <Pressable
            key={item.id}
            style={[
              styles.notificationItem,
              item.isRead && styles.readNotification,
            ]}
            onPress={() => markAsRead(item.id)}
          >
            <Text style={styles.notificationBody}>{item.content}</Text>
          </Pressable>
        )}
      />
    </GradientBackground>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  header: {
    marginTop: 50,
    marginBottom: 16,
  },
  markAllButton: {
    color: AppColors.gray,
    fontSize: 20,
  },
  notificationItem: {
    backgroundColor: "#fff",
    borderRadius: 8,
    padding: 16,
    marginBottom: 16,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.2,
    shadowRadius: 2,
    elevation: 3,
  },
  readNotification: {
    opacity: 0.6,
  },
  notificationHeader: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  notificationTitle: {
    fontSize: 18,
    fontWeight: "bold",
  },
  notificationBody: {
    marginTop: 8,
    fontSize: 16,
    color: "#333",
  },
});
