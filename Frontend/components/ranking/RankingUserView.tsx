import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { RankingUser } from '../../types/apimodels'
import { NavigationProp, useNavigation } from '@react-navigation/native'
import { RootNavigatorParams } from '../../navigation/AppNavigation'

interface RankingUserViewProp {
  user: RankingUser
}

const RankingUserView = ({ user } : RankingUserViewProp) => {

  const navigation = useNavigation<NavigationProp<RootNavigatorParams>>();
  
  function onPress() {
    navigation.navigate("ProfileNavigator", {screen: "UserDetailScreen", params: {username: user.username }})
  }

  return (
    <Pressable style={styles.container} onPress={onPress}>
      <Text>{user.username}</Text>
    </Pressable>
  )
}

export default RankingUserView

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    marginHorizontal: 10,
  }
})