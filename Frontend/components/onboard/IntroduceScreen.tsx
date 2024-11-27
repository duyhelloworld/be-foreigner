import React, { useState } from "react";
import {
  Animated,
  Dimensions,
  FlatList,
  Image,
  StyleSheet,
  Text,
  View,
  useAnimatedValue,
} from "react-native";
import BottomButton from "../common/BottomButton";
import { AppColors } from "../../types/colors";
import { useAppNavigation } from "../../navigation/AppNavigation";
import useFirstTry from "../../storage/FirstTryHooks";

const screens = [
  {
    title: "Hiện đại & Trẻ trung",
    description: "Trải nghiệm giao diện hiện đại và năng động",
    color: "#3B82F6",
    image:
      "https://images.unsplash.com/photo-1507652955-f3dcef5a3be5?ixid=M3w2NTYwNDN8MHwxfHNlYXJjaHwxfHx5b3V8ZW58MHx8fHwxNzMxNTQ2OTg1fDA&ixlib=rb-4.0.3",
  },
  {
    title: "Vui vẻ & Công bằng",
    description: "Tận hưởng không gian vui vẻ và công bằng cho mọi người",
    color: "#10B981",
    image:
      "https://images.unsplash.com/photo-1507652955-f3dcef5a3be5?ixid=M3w2NTYwNDN8MHwxfHNlYXJjaHwxfHx5b3V8ZW58MHx8fHwxNzMxNTQ2OTg1fDA&ixlib=rb-4.0.3",
  },
  {
    title: "Hoàn toàn Miễn phí",
    description: "Sử dụng tất cả tính năng mà không mất phí",
    color: "#F59E0B",
    image:
      "https://images.unsplash.com/photo-1507652955-f3dcef5a3be5?ixid=M3w2NTYwNDN8MHwxfHNlYXJjaHwxfHx5b3V8ZW58MHx8fHwxNzMxNTQ2OTg1fDA&ixlib=rb-4.0.3",
  },
  {
    title: "Bạn đã sẵn sàng?",
    description: "Hãy bắt đầu trải nghiệm ngay bây giờ!",
    color: "#EC4899",
    image:
      "https://images.unsplash.com/photo-1507652955-f3dcef5a3be5?ixid=M3w2NTYwNDN8MHwxfHNlYXJjaHwxfHx5b3V8ZW58MHx8fHwxNzMxNTQ2OTg1fDA&ixlib=rb-4.0.3",
  },
];

const IntroduceScreen = () => {
  const scrollX = useAnimatedValue(0);
  const [curIndex, setCurIndex] = useState(0);
  const { width } = Dimensions.get("window");

  const navigator = useAppNavigation();
  const firstTry = useFirstTry();

  return (
    <View style={styles.container}>
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
              source={{ uri: item.image }}
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
            navigator.navigate("AuthNavigator", { screen: "LoginScreen" });
            await firstTry.setTried();
          }
          }
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
  slide: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  image: {
    resizeMode: "cover",
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
