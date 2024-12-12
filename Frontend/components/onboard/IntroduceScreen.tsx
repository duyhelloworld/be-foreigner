import React, { useState } from "react";
import {
  Animated,
  Dimensions,
  FlatList,
  Image,
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  useAnimatedValue,
} from "react-native";
import BottomButton from "../common/BottomButton";
import { AppColors } from "../../types/colors";
import { useAppNavigation } from "../../navigation/AppNavigation";
import useFirstTry from "../../hook/FirstTryHooks";

const screens = [
  {
    title: "Hiện đại & Trẻ trung",
    description: "Trải nghiệm giao diện hiện đại và năng động",
    color: "#3B82F6",
    image: require("../../assets/modern.png"),
  },
  {
    title: "Vui vẻ & Công bằng",
    description: "Tận hưởng không gian vui vẻ và công bằng cho mọi người",
    color: "#10B981",
    image: require("../../assets/funny.png"),
  },
  {
    title: "Hoàn toàn Miễn phí",
    description: "Sử dụng tất cả tính năng mà không mất phí",
    color: "#F59E0B",
    image: require("../../assets/free.png"),
  },
  {
    title: "Bạn đã sẵn sàng?",
    description: "Hãy bắt đầu trải nghiệm ngay bây giờ!",
    color: "#11AA11",
    image: require("../../assets/ready.png"),
  },
];

const IntroduceScreen = () => {
  const scrollX = useAnimatedValue(0);
  const [curIndex, setCurIndex] = useState(0);
  const { width } = Dimensions.get("window");

  const navigator = useAppNavigation();
  const firstTry = useFirstTry();

  const handleSkip = async () => {
    await firstTry.setTried();
    navigator.navigate("AuthNavigator", { screen: "LoginScreen" });
  };

  return (
    <View style={styles.container}>
      {/* Nút Bỏ qua */}
      <TouchableOpacity style={styles.skipButton} onPress={handleSkip}>
        <Text style={styles.skipText}>Bỏ qua</Text>
      </TouchableOpacity>

      <FlatList
        data={screens}
        horizontal
        showsHorizontalScrollIndicator={false}
        pagingEnabled
        bounces={false}
        onScroll={Animated.event(
          [{ nativeEvent: { contentOffset: { x: scrollX } } }],
          { useNativeDriver: false }
        )}
        onViewableItemsChanged={({ viewableItems }) =>
          setCurIndex(viewableItems[0]?.index ?? 0)
        }
        viewabilityConfig={{ viewAreaCoveragePercentThreshold: 50 }}
        scrollEventThrottle={32}
        keyExtractor={(item) => item.title}
        renderItem={({ item }) => (
          <View style={[styles.slide, { backgroundColor: item.color, width }]}>
            <Image
              source={item.image}
              style={[
                styles.image,
                { width: width * 0.8, height: width * 0.8 },
              ]}
            />
            <View style={styles.textContainer}>
              <Text style={styles.title}>{item.title}</Text>
              <Text style={styles.description}>{item.description}</Text>
            </View>
          </View>
        )}
      />
      {curIndex === screens.length - 1 ? (
        <BottomButton
          onPress={async () => {
            await firstTry.setTried();
            navigator.navigate("AuthNavigator", { screen: "LoginScreen" });
          }}
          title="Bắt đầu ngay"
        />
      ) : (
        <View style={styles.indicatorContainer}>
          {screens.map((_, index) => (
            <View
              key={index}
              style={[
                styles.indicator,
                {
                  backgroundColor:
                    index === curIndex ? AppColors.white : AppColors.gray,
                  width: index === curIndex ? 20 : 10,
                },
              ]}
            />
          ))}
        </View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  skipButton: {
    position: "absolute",
    top: 40,
    right: 20,
    zIndex: 10,
  },
  skipText: {
    fontSize: 16,
    color: AppColors.white,
    fontWeight: "bold",
  },
  slide: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  image: {
    resizeMode: "contain",
    borderRadius: 20,
    marginBottom: 30,
  },
  textContainer: {
    alignItems: "center",
    paddingHorizontal: 20,
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
    color: "#fff",
    textAlign: "center",
    marginBottom: 10,
  },
  description: {
    fontSize: 18,
    color: "#fff",
    textAlign: "center",
  },
  indicatorContainer: {
    flexDirection: "row",
    position: "absolute",
    bottom: 40,
    alignSelf: "center",
  },
  indicator: {
    height: 10,
    borderRadius: 5,
    marginHorizontal: 5,
  },
});

export default IntroduceScreen;