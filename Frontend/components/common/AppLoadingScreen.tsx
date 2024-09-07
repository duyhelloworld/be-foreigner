import { StyleSheet, Text, View } from 'react-native'
import React, { useEffect } from 'react'
import Animated, { interpolate, useAnimatedStyle, useSharedValue, withSpring } from 'react-native-reanimated'
import { Ionicons } from '@expo/vector-icons';

const SIZE = 100;
const AppLoadingScreen = () => {
  
  const tranRotate = useSharedValue(0);
  const tranScale = useSharedValue(0);
  const tranTran = useSharedValue(0);

  const reanimatedStyle = useAnimatedStyle(() => {
    return {
      width: SIZE,
      height: SIZE,
      transform: [{scale: tranScale.value}],
    }
  }, []);

  useEffect(() => {
    tranScale.value = withSpring(5, {duration: 3000 });
  }, []);

  return (
    <Animated.View style={reanimatedStyle}>
      <Ionicons name='airplane' />
    </Animated.View>
  )
}

export default AppLoadingScreen

const styles = StyleSheet.create({})