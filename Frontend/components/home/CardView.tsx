import { Image, StyleSheet, Text, View } from 'react-native'
import React from 'react'

interface CardViewProp {
  card: Card;
}

const CardView = (prop: CardViewProp) => {
  return (
    <View>
      <Image src={prop.card.image} />
      <Text>{prop.card.word.value}</Text>
    </View>
  );
}

export default CardView

const styles = StyleSheet.create({})