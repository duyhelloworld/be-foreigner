import axios, { AxiosError } from "axios";
import { AuthStorageType } from "../storage/AuthStorageHooks";
import { AuthServiceType } from "../services/AuthService";
import { isApiErrorResponse, isAuth } from "../types/apimodels";

export const ROOT_URL = "http://192.168.1.158:8080/api";

const apiClient = axios.create({
  baseURL: ROOT_URL,
});

export function setupAxiosClient(authStorage: AuthStorageType, authService: AuthServiceType) {
  apiClient.interceptors.request.use(
    (config) => {
      const accessToken = authStorage.getAccessToken();
      if (accessToken) {
        config.headers["Authorization"] = `Bearer ${accessToken}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );

  let isRenewRefreshTokenRequest = false;

  apiClient.interceptors.response.use(
    (response) => response,
    async (error: AxiosError) => {
      // Unauthorized
      if (error.status === 401 || error.status === 403) {
        if (!isRenewRefreshTokenRequest) {
          isRenewRefreshTokenRequest = true;
          try {
              const response = await authService.renewRefreshToken();
              if (isAuth(response)) {
                apiClient.defaults.headers.common.Authorization = `Bearer ${response.accessToken}`;
                isRenewRefreshTokenRequest = false;
                apiClient(error.config!);
              } else if (isApiErrorResponse(response)) {
                console.log(response.messages);
              }
          } catch (error) {
            // Redirect login
            console.error("Error when refresh token : ", error);
            authStorage.removeTokens();
          }
        }
        isRenewRefreshTokenRequest = false;
        return Promise.reject(error);
      }
    }
  );
}
export default apiClient;

