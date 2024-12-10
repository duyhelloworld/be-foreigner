import React, { useEffect, useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  FlatList,
  SafeAreaView,
} from "react-native";
import { SubscriptionPlan } from "../../../types/enum";
import { useUserStorage } from "../../../hook/UserStorageHooks";

interface PlanOption {
  id: number;
  title: SubscriptionPlan;
  price: string;
  description: string;
}

const planOptions: PlanOption[] = [
  {
    id: 0,
    title: SubscriptionPlan.FREE,
    price: "Miễn phí",
    description: "Truy cập các tính năng cơ bản",
  },
  {
    id: 1,
    title: SubscriptionPlan.PREMIUM_MONTH,
    price: "99.000đ/tháng",
    description: "Truy cập đầy đủ tính năng, thanh toán hàng tháng",
  },
  {
    id: 2,
    title: SubscriptionPlan.PREMIUM_YEAR,
    price: "999.000đ/năm",
    description: "Truy cập đầy đủ tính năng, tiết kiệm 15%",
  },
  {
    id: 3,
    title: SubscriptionPlan.LIFETIME,
    price: "2.999.000đ",
    description: "Truy cập trọn đời, không giới hạn",
  },
];

const UpgradePlanScreen: React.FC = () => {
  const [selectedPlan, setSelectedPlan] = useState<SubscriptionPlan | null>();
  const [isChoosen, setIsChoosen] = useState(false);

  const userStorage = useUserStorage();

  useEffect(() => {
    async function load() {
      const user = await userStorage.getInfo();
      if (user) {
        setSelectedPlan(user.plan);
      }
    }
    load();
    setIsChoosen(false);
  }, []);

  const renderPlanItem = ({ item }: { item: PlanOption }) => (
    <TouchableOpacity
      style={[
        styles.planCard,
        selectedPlan === item.title && styles.selectedPlanCard,
      ]}
      onPress={() => {
        setSelectedPlan(item.title);
        setIsChoosen(true);
      }}
    >
      <Text style={styles.planTitle}>{item.title}</Text>
      <Text style={styles.planPrice}>{item.price}</Text>
      <Text style={styles.planDescription}>{item.description}</Text>
    </TouchableOpacity>
  );

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.header}>Nâng cấp tài khoản</Text>
      <FlatList
        data={planOptions}
        renderItem={renderPlanItem}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={styles.planList}
      />
      {isChoosen ? (
        <TouchableOpacity
          style={[styles.upgradeButton, !selectedPlan && styles.disabledButton]}
          disabled={!selectedPlan}
          onPress={() => {
            // Xử lý logic nâng cấp tài khoản ở đây
            // console.log(`Nâng cấp lên ${selectedPlan}`);
          }}
        >
          <Text style={styles.upgradeButtonText}>
            {selectedPlan
              ? selectedPlan !== SubscriptionPlan.FREE
                ? `Nâng cấp lên ${selectedPlan}`
                : "Đây là gói hiện tại của bạn"
              : "Chọn gói đăng ký"}
          </Text>
        </TouchableOpacity>
      ) : null}
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 30,
    padding: 16,
  },
  header: {
    fontSize: 24,
    fontWeight: "bold",
    marginBottom: 20,
    textAlign: "center",
  },
  planList: {
    paddingBottom: 20,
  },
  planCard: {
    backgroundColor: "#ffffff",
    borderRadius: 8,
    padding: 16,
    marginBottom: 16,
    elevation: 2,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
  },
  selectedPlanCard: {
    borderColor: "#007AFF",
    borderWidth: 2,
  },
  planTitle: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 8,
  },
  planPrice: {
    fontSize: 16,
    color: "#007AFF",
    marginBottom: 8,
  },
  planDescription: {
    fontSize: 14,
    color: "#666",
  },
  upgradeButton: {
    backgroundColor: "#007AFF",
    borderRadius: 8,
    padding: 16,
    alignItems: "center",
  },
  disabledButton: {
    backgroundColor: "#B0B0B0",
  },
  upgradeButtonText: {
    color: "#ffffff",
    fontSize: 18,
    fontWeight: "bold",
  },
});

export default UpgradePlanScreen;
