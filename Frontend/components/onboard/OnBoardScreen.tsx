import { Button, StyleSheet, Text, View } from "react-native";
import React, { useEffect } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { Auth } from "../../types/apimodels";

const OnBoardScreen = () => {
  const KEYS = {
    accessToken: "access",
    refreshToken: "refresh",
  };

  async function getToken(): Promise<string> {
    try {
      const value = await AsyncStorage.getItem(KEYS.accessToken);
      if (!value) {
        return "";
      }
      return value;
    } catch (error) {
      console.error("lỗi khi lấy token: ", error);
      return "";
    }
  }

  async function setToken(auth: Auth) {
    try {
      await AsyncStorage.multiSet([
        [KEYS.accessToken, auth.accessToken],
        [KEYS.refreshToken, auth.refreshToken],
      ]);
      console.log("saved : ", JSON.stringify(auth));
    } catch (error) {
      console.error("error when save token: ", error);
    }
  }

  return (
    <View>
      <Button
        title="Set"
        onPress={() =>
          setToken({ accessToken: "Hello", refreshToken: "Bscript" })
        }
      />
      <Text>{}</Text>
    </View>
  );
};

export default OnBoardScreen;

const styles = StyleSheet.create({});
