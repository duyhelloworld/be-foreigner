import { FlatList, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import TopicView from './TopicView';

interface ListTopicViewProp {
  title: string;
  topics: Topic[];
}
const ListTopicView = (prop: ListTopicViewProp) => {
  return (
    <View>
      <Text style={styles.title}>{prop.title}</Text>
      <FlatList
        horizontal
        data={prop.topics}
        renderItem={({ item }) => (
          <TopicView topic={item} />
        )}
      />
    </View>
  )
}

export default ListTopicView

const styles = StyleSheet.create({
  title: {
    paddingLeft: 10,
    paddingTop: 10,
  },
})