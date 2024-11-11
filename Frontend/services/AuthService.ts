import useAuthStorage from "../storage/AuthStorageHooks";
import { ApiErrorResponse, Auth } from "../types/apimodels";
import apiClient from "../config/AxiosConfig";
import { handleError } from "../utils/ErrorHanlderUtil";

export type AuthServiceType = {
  signIn: (username: string, password: string) => Promise<void |  ApiErrorResponse>;
  renewRefreshToken: () => Promise<Auth | ApiErrorResponse>;
  signUp: (username: string, password: string, email: string, fullname: string) => Promise<void | ApiErrorResponse>;
  signOut: () => Promise<void | ApiErrorResponse>;
};

const useAuthService = () : AuthServiceType => {
  const authStorage = useAuthStorage();

  const renewRefreshToken = async () => {
    try {
      const response = await apiClient.put("auth/renew", {
        refreshToken: await authStorage.getRefreshToken(),
      });
      await authStorage.saveTokens(response.data);
      return response.data;
    } catch (error) {
      return handleError(error);
    }
  };

  const signIn = async (username: string, password: string) => {
    try {
      const response = await apiClient.post(
        "auth/sign-in",
        {
          username,
          password,
        }
      );
      await authStorage.saveTokens(response.data);
    } catch (error) {
      return handleError(error);
    }
  };

  const signUp = async (username: string, password: string, email: string, fullname: string) => {
    try {
      const response = await apiClient.post(
        "auth/sign-up",
        {
          username,
          password,
          email,
          fullname
        }
      );
      console.log(response);
      // await authStorage.saveTokens(response.data);
    } catch (error) {
      return handleError(error);
    }
  };

  const signOut = async () => {
    try {
      const response = await apiClient.put("auth/sign-out");
      if (response.status === 200) {
        authStorage.removeTokens();
      } else {
        console.error(response.data);
      }
    } catch (error) {
      return handleError(error);
    }
  };

  return { renewRefreshToken, signIn, signUp, signOut };
};

export default useAuthService;
