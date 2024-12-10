import axios from "axios";
import { AuthStorageType } from "../hook/AuthStorageHooks";
import { Alert } from "react-native";

// export const ROOT_URL = "http://192.168.32.144:8080/api";
// export const ROOT_URL = "http://192.168.1.53:8080/api";
export const ROOT_URL = "http://192.168.1.56:8080/api";

const apiClient = axios.create({
  baseURL: ROOT_URL,
  timeout: 5000,
});

export function setupAxiosClient(authStorage: AuthStorageType) {

  apiClient.interceptors.request.use(
    async (config) => {
      const accessToken = await authStorage.getAccessToken();
      if (accessToken !== null) {
        config.headers["Authorization"] = `Bearer ${accessToken}`;
      }
      return config;
    },
    error => {
      if (axios.isAxiosError(error) && error.message === "Network Error") {
        Alert.alert(
          "Không thể kết nối server",
          "Không thể kết nối đến server. Vui lòng kiểm tra server của bạn.",
          [{ text: "OK" }]
        );
      }
      return Promise.reject(error);
    }
  );
}

export default apiClient;
