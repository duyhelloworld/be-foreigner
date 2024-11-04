import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { SubscriptionPlan } from '../../types/enum'
import { useUserStorage } from '../../hook/GlobalStorageHooks'
import { AppColors } from '../../types/Colors';

interface SubscriptionPlanViewProp {
  plan: SubscriptionPlan,
}


function handleBackgroundColor(plan: SubscriptionPlan) : string {
  switch (plan) {
    case SubscriptionPlan.FREE:
      return AppColors.yellow;
    case SubscriptionPlan.LIFETIME: 
      return AppColors.lightGreen;
    case SubscriptionPlan.PREMIUM_MONTH:
      return AppColors.blue;
    case SubscriptionPlan.PREMIUM_YEAR: 
      return AppColors.gray;
  }
}
const SubscriptionPlanView = ({plan} : SubscriptionPlanViewProp) => {
  
  const backgroundColor = handleBackgroundColor(plan);

  function onReviewPlanPress() {
    const current = useUserStorage();
    if (plan === current.plan) {
      return;
    }
  }

  return (
    <Pressable style={[styles.container, {backgroundColor}]} onPress={onReviewPlanPress} >
      <Text style={styles.planName}>{plan}</Text>
    </Pressable>
  )
}

export default SubscriptionPlanView

const styles = StyleSheet.create({
  container: {
    padding: 10,
    borderRadius: 20,
    maxWidth: "80%",
    margin: 10,
  },
  planName: {
    fontWeight: '500'
  }
})