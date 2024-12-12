import AsyncStorage from "@react-native-async-storage/async-storage";
import { UserInfo } from "../types/apimodels";

export function useUserStorage() {
  const KEY = "user-info";

  const setInfo = async (user: UserInfo) => {
    await AsyncStorage.setItem(
      KEY,
      JSON.stringify(user)
    );
  }
  
  const getInfo = async (): Promise<UserInfo | undefined> => {
    const infoString = await AsyncStorage.getItem(KEY);
    if (infoString) {
      return JSON.parse(infoString);
    } else {
      return undefined;
    }
  };

  const saveNotificationSetting = async ({isAllowEmail, isAllowNotification} : {isAllowEmail:boolean, isAllowNotification:boolean}) => {
    const user = await getInfo();
    await setInfo({...user!, isAllowEmail, isAllowNotification});
  }

  async function clear() {
    await AsyncStorage.removeItem(KEY);
  }

  return { getInfo, setInfo, clear, saveNotificationSetting };
}
