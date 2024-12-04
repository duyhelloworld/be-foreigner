import axios from "axios";
import { AuthStorageType } from "../hook/AuthStorageHooks";

export const ROOT_URL = "http://192.168.32.144:8080/api";
// export const ROOT_URL = "http://192.168.1.125:8080/api";
// export const ROOT_URL = "http://192.168.1.56:8080/api";

const apiClient = axios.create({
  baseURL: ROOT_URL,
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
    (error) => Promise.reject(error)
  );
}

export default apiClient;
