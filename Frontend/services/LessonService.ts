import apiClient from "../config/AxiosConfig";
import { useUserStorage } from "../storage/UserStorageHooks"

const useLessonService = () => {

  const userStorage = useUserStorage();

  const getSuggestLessons = async () => {
    try {
      const response = await apiClient.get("")
    } catch (error) {
      console.error(error);
    }
  }


  return {}
}