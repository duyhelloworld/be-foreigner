import { Animated, StyleSheet, Text, View, useAnimatedValue } from 'react-native'
import React, { useEffect } from 'react'
import { Ionicons } from '@expo/vector-icons';

const SIZE = 100;
const AppLoadingScreen = () => {
  
  const tranRotate = useAnimatedValue(0);
  const tranScale = useAnimatedValue(0);
  const tranTran = useAnimatedValue(0);

  return (
    <Animated.View>
      <Ionicons name='airplane' />
    </Animated.View>
  )
}

export default AppLoadingScreen

const styles = StyleSheet.create({
  
})