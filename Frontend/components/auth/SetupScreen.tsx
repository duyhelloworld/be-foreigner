import React from 'react'
import SplashScreen from '../common/SplashScreen';
import { useUserStorage } from '../../storage/UserStorageHooks';
import { ApiResponse, UserInfo } from '../../types/apimodels';
import apiClient from '../../config/AxiosConfig';
import { ApiResponseCode } from '../../types/enum';
import { useAppNavigation } from '../../navigation/AppNavigation';

const SetupScreen = () => {

  const navigator = useAppNavigation();
  const userStorage = useUserStorage();

  const handleTask = async () => {
    const response = await apiClient.get<ApiResponse>(`user/my-info`);
    if (response.data.code === ApiResponseCode.OK) {
      await userStorage.setInfo(response.data.data as UserInfo);
    } 
  };

  const handleFinish = () => {
    navigator.navigate("HomeNavigator", { screen: "HomeScreen" });
  };

  return (
    <SplashScreen onTask={handleTask} onFinish={handleFinish} totalTime={2000} />
  )
}

export default SetupScreen;