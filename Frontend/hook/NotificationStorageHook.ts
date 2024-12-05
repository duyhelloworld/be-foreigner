import AsyncStorage from "@react-native-async-storage/async-storage";
import { Notification, UserInfo } from "../types/apimodels";

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
