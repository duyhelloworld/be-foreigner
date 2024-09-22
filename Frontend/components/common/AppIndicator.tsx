import { StyleSheet, Text, View } from "react-native";
import * as ProgressBar from "react-native-progress";
import { MAIN } from "../../constants/Colors";

interface AppIndicatorProp {
  visible?: boolean;
}

const AppIndicator = ({ visible = true } : AppIndicatorProp) => {

  if (!visible) {
    return null;
  }

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Chờ tui tí nhé ...</Text>
      <ProgressBar.Bar indeterminate borderWidth={3} borderRadius={10} unfilledColor={MAIN} />
    </View>
  );
};

export default AppIndicator;

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: "center",
    alignItems: "center",
  },
  text: {
    fontSize: 20,
    marginBottom: 10,
  },
});
