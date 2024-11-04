export enum DiffLevel {
  BEGINNER = "Thấp, người mới",
  MEDIUM = "Trung bình, người đã có nền tảng nhẹ",
  HARD = "Cao, người muốn cải thiện sâu các kĩ năng khác",
}

export enum UserLevel {
  BEGINNER = "Thấp, người mới (A0, A1)",
  INTERMEDIATE = "Biết chút chút (A2)",
  MEDIUM = "Trung bình, người đã có nền tảng nhẹ (B1)",
  ADVANCED = "Khá ổn, cần hiểu sâu tiếng anh (B2)",
}

export enum SubscriptionPlan {
  FREE = "Người dùng cơ bản",
  PREMIUM_MONTH = "Gói VIP tháng",
  PREMIUM_YEAR = "Gói VIP năm",
  LIFETIME = "Gói trọn đời",
}

export enum ClaimTypes {
  USERNAME,
  USERID,
  EMAIL,
  ROLE,
  USERTYPE,
}

export enum WordType {
  DEFINITE_ARTICLE = "Mạo từ xác định",
  INDEFINITE_ARTICLE = "Mạo từ không xác định",
  NOUN = "danh từ",
  VERB = "động từ",
  ADJECTIVE = "tính từ",
  ADVERB = "trạng từ",
  PRONOUN = "đại từ",
  PREPOSITION = "giới từ",
  INTERJECTION = "thán từ",
  DETERMINER = "từ hạn định",
  CONJUNCTION = "liên từ",
}

export enum QuestionType {
  GIVE_AUDIO_REARRANGE_WORDS = "Sắp xếp lại các từ mà bạn nghe được",
  GIVE_SENTENSE_REARRANGE_WORDS = "Sắp xếp lại các từ để được câu bên dưới",
  GIVE_MEAN_CHOOSE_WORD = "Chọn đáp án đúng",
  MATCHING = "Nối các từ với nghĩa của chúng",
  GIVE_AUDIO_CHOOSE_WORD = "Cho file âm thanh, chọn từ chính xác"
}

export enum LessonStatus {
  LOCKED,
  COMPLETED,
  ONGOING
}

