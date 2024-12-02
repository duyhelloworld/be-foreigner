import AsyncStorage from "@react-native-async-storage/async-storage";

export type FirstTryType = {
  isFirstTry: () => Promise<boolean>;
  setTried: () => Promise<void>;
};

const FIRST_TRY_KEY = "first-try";

export default function useFirstTry(): FirstTryType {
  
  async function isFirstTry() {
    const keys = await AsyncStorage.getAllKeys();
    return !keys.includes(FIRST_TRY_KEY);
  }

  async function setTried() {
    await AsyncStorage.setItem(FIRST_TRY_KEY, "OK");
  }

  return { isFirstTry , setTried };
}
