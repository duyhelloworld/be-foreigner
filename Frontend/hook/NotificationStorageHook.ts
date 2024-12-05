import AsyncStorage from "@react-native-async-storage/async-storage";
import { Notification } from "../types/apimodels";

export function useNotificationStorage() {
  const NOTIFICATION_KEY = "notifications";

  async function addNotification(notification: Notification) {
    const current: Notification[] = JSON.parse(
      (await AsyncStorage.getItem(NOTIFICATION_KEY)) ?? "[]"
    );
    if (current.filter((c) => c.id !== notification.id)) {
      await AsyncStorage.setItem(
        NOTIFICATION_KEY,
        JSON.stringify([...current, notification])
      );
    }
  }

  async function init() {
    await AsyncStorage.setItem(NOTIFICATION_KEY, "[]");
  }

  async function getNotifications() {
    let current: Notification[] = JSON.parse(
      (await AsyncStorage.getItem(NOTIFICATION_KEY)) ?? "[]"
    );
    if (current.length === 0) {
      return [
        {
          id: "1",
          title: "Hú lô, lại là Liongo đây!🦁",
          content: "Hình như lâu rồi tôi chưa thấy bạn đó",
          isRead: false,
        },
        {
          id: "2",
          title: "🤗Ôi bạn ơi🤗, sao bạn chưa vào học vậy",
          content:
            "Lần cuối bạn học cùng tôi là khi nào nhỉ? Hay là để tôi nhắc bạn nhớ nhé!",
          isRead: false,
        },
        {
          id: "3",
          title: "Phạm Duy ơi ❤️❤️❤️ bạn ỉm hơi lâu rồi á :D",
          content:
            "Hình như tôi chiều các em quá nên các em hư đúng không? Vào học ngayyyy",
          isRead: false,
        },
        {
          id: "4",
          title: "Gào gào gào 🦁🦁🦁, bạn Phạm Duy có ở đây không?",
          content: "Anh nhắc em, vào học ngay cho anh",
          isRead: false,
        },
        {
          id: "5",
          title: "Duy Phạm ơi! 🕒 Đến giờ học rồi nà",
          content: "Vừng ơi mở ra...",
          isRead: true,
        },
      ]
    }
      return current;
  }

  async function markRead(id: number) {
    const current: Notification[] = JSON.parse(
      (await AsyncStorage.getItem(NOTIFICATION_KEY)) ?? "[]"
    );
    const updatedNotifications = current.map((notification) =>
      notification.id === id ? { ...notification, isRead: true } : notification
    );
    await AsyncStorage.setItem(
      NOTIFICATION_KEY,
      JSON.stringify(updatedNotifications)
    );
  }

  return { addNotification, getNotifications, markRead, init };
}
