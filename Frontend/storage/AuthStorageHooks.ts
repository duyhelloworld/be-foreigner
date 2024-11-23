import AsyncStorage from "@react-native-async-storage/async-storage";
import { Auth } from "../types/apimodels";

const ACCESS_TOKEN_KEY = "access-token";
const REFRESH_TOKEN_KEY = "refresh-token";

export type AuthStorageType = {
  saveTokens: (auth: Auth) => Promise<void>;
  removeTokens: () => Promise<void>;
  getAccessToken: () => Promise<string | null>;
  getRefreshToken: () => Promise<string | null>;
};

const useAuthStorage = () : AuthStorageType => {

  const saveTokens = async (newTokens: Auth) => {
    await AsyncStorage.setItem(ACCESS_TOKEN_KEY, newTokens.accessToken);
    await AsyncStorage.setItem(REFRESH_TOKEN_KEY, newTokens.refreshToken);
  };

  const removeTokens = async () => {
    await AsyncStorage.removeItem(ACCESS_TOKEN_KEY);
    await AsyncStorage.removeItem(REFRESH_TOKEN_KEY);
  };

  const getAccessToken = async () => {
    const accessToken = await AsyncStorage.getItem(ACCESS_TOKEN_KEY);
    return accessToken;
  };

  const getRefreshToken = async () => {
    const refreshToken = await AsyncStorage.getItem(REFRESH_TOKEN_KEY);
    return refreshToken;
  }

  return { saveTokens, removeTokens, getAccessToken, getRefreshToken };
};

export default useAuthStorage;
