import { Audio } from "expo-av";

export const playWordAudio = async (url: string) => {
  const { sound } = await Audio.Sound.createAsync({uri: url});
  await sound.playAsync();
};

export const playAudio = async (file: any) => {
  const { sound } = await Audio.Sound.createAsync(file);
  await sound.playAsync();
};
