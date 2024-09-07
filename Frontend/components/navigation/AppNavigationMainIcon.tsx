import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { Ionicons } from '@expo/vector-icons';

const AppNavigationMainIcon = (prop: { focused: boolean } ) => {
  return (
    <View
      style={[
        styles.learnButton,
        prop.focused
          ? { backgroundColor: "#000" }
          : { backgroundColor: "#C4FF61" },
      ]}
    >
      <Ionicons name="book" color={prop.focused ? "#C4FF61" : "#000"} />
    </View>
  );
}

export default AppNavigationMainIcon

const styles = StyleSheet.create({
  learnButton: {
    bottom: 12,
    height: 58,
    width: 58,
    borderRadius: 58,
    justifyContent: "center",
    alignItems: "center",
  }
});