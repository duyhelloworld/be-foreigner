import AsyncStorage from "@react-native-async-storage/async-storage";
import { ApiResponse, Notification } from "../types/apimodels";
import apiClient from "../config/AxiosConfig";
import { ApiResponseCode, ContentType } from "../types/enum";

const NOTIFICATION_KEY = "notifications";

export function useNotificationStorage() {
  async function addNotification(notification: Notification) {
    const current: Notification[] = await getNotifications();
    if (current.filter((c) => c.id !== notification.id)) {
      await AsyncStorage.setItem(
        NOTIFICATION_KEY,
        JSON.stringify([...current, notification])
      );
    }
  }

  async function addAll(notifications: Notification[]) {
    let current = await getNotifications();
    const notificationMap = new Map(current.map((n) => [n.id, n]));
    for (const notification of notifications) {
      notificationMap.set(notification.id, notification);
    }
    const uniqueNotifications = Array.from(notificationMap.values());
    await AsyncStorage.setItem(
      NOTIFICATION_KEY,
      JSON.stringify(uniqueNotifications)
    );
  }

  async function getNotifications() {
    const json = await AsyncStorage.getItem(NOTIFICATION_KEY);
    await AsyncStorage.setItem("1", "A");
    let current: Notification[] = json ? JSON.parse(json) : [];
    return current;
  }

  async function markRead(id: number) {
    const current: Notification[] = await getNotifications();
    const updatedNotifications = current.map((notification) =>
      notification.id === id ? { ...notification, read: true } : notification
    );
    const response = await apiClient.post<ApiResponse>("remind/read", {
      remindIds: [id],
    });
    if (response.data.code === ApiResponseCode.OK) {
      await AsyncStorage.setItem(
        NOTIFICATION_KEY,
        JSON.stringify(updatedNotifications)
      );
    }
  }

  async function markAllRead() {
    const current: Notification[] = await getNotifications();
    const updatedNotifications = current.map((notification) => ({
      ...notification,
      isRead: true,
    }));
    await AsyncStorage.removeItem(NOTIFICATION_KEY);
    await AsyncStorage.setItem(
      NOTIFICATION_KEY,
      JSON.stringify(updatedNotifications)
    );
  }

  return { addNotification, getNotifications, markRead, markAllRead, addAll };
}
