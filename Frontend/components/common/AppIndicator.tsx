import { ActivityIndicator, StyleSheet, Text, View } from 'react-native'
import React, { useEffect, useState } from 'react'

interface AppIndicatorProp {
  visible: boolean;
}

type colors = "#023" | "#331";

function Indicator() {

  const [color, setColor] = useState<colors>('#023');

  useEffect(() => {
    const id = setInterval(() => {
      setColor((color) => color == '#023' ? '#331' : '#023');
    }, 700);
    return () => clearInterval(id);
  }, []);

  return (
    <View style={styles.indicatorLocation}>
      <ActivityIndicator size={'large'} color={color} />
      <Text style={styles.text}>Chờ tui tí nhé</Text>
    </View>
  )
}

const AppIndicator = ({visible = true} : AppIndicatorProp) => {
  const [isVisible, setIsVisible] = useState(visible);

  return (
    <View style={styles.container}>
      {isVisible && <Indicator /> }
    </View>
  )
}

export default AppIndicator

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  text: {
    fontSize: 20,
    color: "#fff",
  },
  indicatorLocation: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});