import AsyncStorage from "@react-native-async-storage/async-storage";
import { UserInfo } from "../types/apimodels";

export function useUserStorage() {

  const KEY = "user-info";

  const setInfo = async (userInfo : UserInfo) => {
    await AsyncStorage.setItem(KEY, JSON.stringify(userInfo));
    console.log("Set user info success");
  }

  const getInfo = async () : Promise<UserInfo | undefined> => {
    const infoString = await AsyncStorage.getItem(KEY);
    if (infoString) {
      return JSON.parse(infoString);
    }
    return undefined;
  }

  return { getInfo, setInfo };
}

