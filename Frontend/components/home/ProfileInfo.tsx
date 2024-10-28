import { Image, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import ProgressBar from '../common/ProgressBar';

interface ProfileInfoProp {
  avatar: string;
  experience: number;
  totalExp: number;
}

const ProfileInfo = ({avatar, experience} : ProfileInfoProp) => {
  return (
    <View>
      <Image src={avatar} />
      <ProgressBar progress={experience} />
    </View>
  )
}

export default ProfileInfo

const styles = StyleSheet.create({})