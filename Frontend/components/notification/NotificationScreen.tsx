import React, { useEffect, useState } from 'react';
import { View, Text, Pressable, StyleSheet, FlatList } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { Notification } from '../../types/apimodels';
import { useNotificationStorage } from '../../hook/NotificationStorageHook';
import { AppColors } from '../../types/colors';

export default function NotificationScreen() {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [expandedIds, setExpandedIds] = useState<Set<string>>(new Set());

  const notificationStorage = useNotificationStorage();

  useEffect(() => {
    async function loadNoti() {
      let noti = await notificationStorage.getNotifications();
      if (noti.length === 0) {
        noti = [
          {
            id: '1',
            content: 'Êy zô Cú xanh tới rồi nè',
            isRead: false,
            sendAt: Date.now(),
            title: 'Nhắc học bài',
          },
          {
            id: '2',
            title: "AA",
            content: 'AAAB',
            isRead: true,
            sendAt: Date.now(),
          },
          {
            id: '3',
            title: "AA",
            content: 'AAAB',
            isRead: true,
            sendAt: Date.now(),
          },
          {
            id: '4',
            title: "AA",
            content: 'AAAB',
            isRead: true,
            sendAt: Date.now(),
          },
          {
            id: '5',
            title: "AA",
            content: 'AAAB',
            isRead: true,
            sendAt: Date.now(),
          },
          {
            id: '6',
            title: "AA",
            content: 'AAABVBJBJKDSJXNVDDJKSBVCXBBFGNFCNNFXGCV N DGCVBGFCNXCFB ',
            isRead: true,
            sendAt: Date.now(),
          }
        ];
      }
      setNotifications(noti);
    }
    loadNoti();
  }, []);

  const toggleExpand = (id: string) => {
    setExpandedIds((prev) => {
      const newSet = new Set(prev);
      if (newSet.has(id)) {
        newSet.delete(id);
      } else {
        newSet.add(id);
      }
      return newSet;
    });
  };

  const markAllAsRead = () => {
    setNotifications((prev) =>
      prev.map((notification) => ({ ...notification, isRead: true }))
    );
  };

  const markAsRead = (id: string) => {
    setNotifications((prev) =>
      prev.map((notification) =>
        notification.id === id ? { ...notification, isRead: true } : notification
      )
    );
  };

  const handlePressNotification = (id: string) => {
    markAsRead(id);
    toggleExpand(id);
  };

  return (
    <View style={styles.container}>
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
            onPress={() => handlePressNotification(item.id)}
          >
            <View style={styles.notificationHeader}>
              <Text style={styles.notificationTitle}>{item.title}</Text>
              <Pressable onPress={() => toggleExpand(item.id)}>
                <Ionicons
                  name={expandedIds.has(item.id) ? 'chevron-up' : 'chevron-down'}
                  size={24}
                  color={AppColors.black}
                />
              </Pressable>
            </View>
            {expandedIds.has(item.id) && (
              <Text style={styles.notificationBody}>{item.content}</Text>
            )}
          </Pressable>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f0f0f0',
    padding: 16,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 16,
  },
  markAllButton: {
    color: '#007AFF',
    fontSize: 16,
  },
  notificationItem: {
    backgroundColor: '#fff',
    borderRadius: 8,
    padding: 16,
    marginBottom: 16,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.2,
    shadowRadius: 2,
    elevation: 3,
  },
  readNotification: {
    opacity: 0.6,
  },
  notificationHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  notificationTitle: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  notificationBody: {
    marginTop: 8,
    fontSize: 16,
    color: '#333',
  },
});
