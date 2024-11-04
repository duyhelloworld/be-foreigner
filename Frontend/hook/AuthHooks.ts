import { useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { Auth } from "../types/apimodels";

const ACCESS_TOKEN_KEY = "access-token";
const REFRESH_TOKEN_KEY = "refresh-token";

const useAuthTokens = () => {
  const [tokens, setTokens] = useState<Auth>();

  const saveTokens = async (newTokens: Auth) => {
    await AsyncStorage.setItem(ACCESS_TOKEN_KEY, newTokens.accessToken);
    await AsyncStorage.setItem(REFRESH_TOKEN_KEY, newTokens.refreshToken);
    setTokens(newTokens);
  };

  const removeTokens = async () => {
    await AsyncStorage.removeItem(ACCESS_TOKEN_KEY);
    await AsyncStorage.removeItem(REFRESH_TOKEN_KEY);
    setTokens({ accessToken: "", refreshToken: "" });
  };

  const getTokens = async () => {
    const accessToken = await AsyncStorage.getItem(ACCESS_TOKEN_KEY);
    const refreshToken = await AsyncStorage.getItem(REFRESH_TOKEN_KEY);
    if (accessToken !== null && refreshToken !== null) {
      setTokens({ accessToken: accessToken, refreshToken: refreshToken });
    }
  };

  return { tokens, saveTokens, removeTokens , getTokens };
};

export default useAuthTokens;

