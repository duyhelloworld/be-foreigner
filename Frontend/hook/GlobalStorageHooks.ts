import { User } from "../types/apimodels";
import { SubscriptionPlan } from "../types/enum";

export function useUserStorage() : User {
  return {
    id: "u489wuufsj",
    username: "Dzuynen",
    fullname: "Pham Duy",
    avatar:
      "https://cellphones.com.vn/sforum/wp-content/uploads/2024/01/anh-meme-98.jpg",
      email: "duy2915@gmail.com",
      plan: SubscriptionPlan.FREE
  };
}

