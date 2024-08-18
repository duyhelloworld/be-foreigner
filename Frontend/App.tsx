import { Dimensions, StyleSheet, Text, View } from 'react-native';

export default function App() {
  const {w, h} = Dimensions.get('window');
  console.log(`w, h >>> ${w} ${h}`);
  return (
    <View style={styles.container}>
          <Text>
            React Native Box Shadow (Elevation)
          </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },

  card: {
    backgroundColor: "white",
    borderRadius: 8,
    paddingVertical: 45,
    paddingHorizontal: 25,
    width: 100,
    marginVertical: 10,
  },
  elevation: {
    elevation: 20,
    shadowColor: "#52006A",
  },

  shadowBox: {
    shadowOffset: {
      width: 10,
      height: 10,
    },
    shadowColor: "#333333",
    shadowOpacity: 0.5,
    shadowRadius: 10,
  },

  android: {
    elevation: 10,
  },
});
