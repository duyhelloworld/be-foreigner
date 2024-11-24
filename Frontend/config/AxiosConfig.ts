import axios from "axios";
import { AuthStorageType } from "../storage/AuthStorageHooks";

// export const ROOT_URL = "http://192.168.184.144:8080/api";
export const ROOT_URL = "http://192.168.1.21:8080/api";

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

//   apiClient.interceptors.response.use(
//     async (response: AxiosResponse) => {
//       return response;
//       // const originalRequest = response.config;

//       // if (
//       //   response.data.code === ApiResponseCode.UNAUTHORIZED ||
//       //   response.data.code === ApiResponseCode.FORBIDDEN
//       // ) {
//       //   if (!isRenewingToken) {
//       //     isRenewingToken = true;
//       //     try {
//       //       const refreshToken = await authStorage.getRefreshToken();
//       //       const refreshResponse: AxiosResponse<ApiResponse> =
//       //         await apiClient.put("auth/renew", { refreshToken: refreshToken });
//       //       if (
//       //         refreshResponse.data.code === ApiResponseCode.OK
//       //       ) {
//       //         const auth = refreshResponse.data.data as Auth;
//       //         await authStorage.saveTokens(auth);
//       //         // Tiếp tục các request bị treo
//       //         apiClient.defaults.headers.common.Authorization = `Bearer ${auth.accessToken}`;
//       //         requestQueue.forEach((callback) => callback());
//       //         requestQueue = [];
//       //       }
//       //     } catch (error) {
//       //       // Redirect login
//       //       console.error("Error when refresh token : ", error);
//       //       authStorage.removeTokens();
//       //     } finally {
//       //       isRenewingToken = false;
//       //     }
//       //   }
//       // }
//       // return new Promise((resolve) => {
//       //   requestQueue.push(() => {
//       //     resolve(apiClient(originalRequest));
//       //   });
//       // });
//     }
//   );
}

export default apiClient;
