import AsyncStorage from "@react-native-async-storage/async-storage";
import { Notification } from "../types/apimodels";

const NOTIFICATION_KEY = "notifications";

export function useNotificationStorage() {

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
      JSON.stringify(notification.id) === JSON.stringify(id) ? { ...notification, isRead: true } : notification
    );
    await AsyncStorage.setItem(
      NOTIFICATION_KEY,
      JSON.stringify(updatedNotifications)
    );
  }

  async function markAllRead() {
    const current: Notification[] = JSON.parse(
      (await AsyncStorage.getItem(NOTIFICATION_KEY)) ?? "[]"
    );
    const updatedNotifications = current.map((notification) => ({
      ...notification,
      isRead: true,
    }));
    await AsyncStorage.setItem(
      NOTIFICATION_KEY,
      JSON.stringify(updatedNotifications)
    );
  }

  return { addNotification, getNotifications, markRead, markAllRead };
}
