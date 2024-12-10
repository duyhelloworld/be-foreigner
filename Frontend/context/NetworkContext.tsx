import React, { createContext, useContext, useEffect, useState } from "react";
import { AppState, AppStateStatus, Alert } from "react-native";
import * as Network from "expo-network";

type NetworkContextType = {
  isConnected: boolean;
};

const NetworkContext = createContext<NetworkContextType>({
  isConnected: true,
});

export const NetworkCheckerProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [isConnected, setIsConnected] = useState(true);

  const checkNetwork = async () => {
    const networkState = await Network.getNetworkStateAsync();
    setIsConnected(networkState.isConnected ?? true);

    if (!networkState.isConnected) {
      Alert.alert(
        "Mất kết nối mạng",
        "Bạn hiện không có kết nối internet. Một số tính năng có thể không hoạt động.",
        [{ text: "OK" }]
      );
    }
  };

  useEffect(() => {
    checkNetwork();

    // Lắng nghe khi ứng dụng trở lại foreground
    const subscription = AppState.addEventListener("change", (nextAppState: AppStateStatus) => {
      if (nextAppState === "active") {
        checkNetwork();
      }
    });

    return () => subscription.remove();
  }, []);

  return (
    <NetworkContext.Provider value={{ isConnected }}>
      {children}
    </NetworkContext.Provider>
  );
};

export const useNetwork = () => useContext(NetworkContext);