export enum ApiResponseCode {
  OK = 1000,
  ERROR_WHEN_VALIDATE = 999,
  SYSTEM_VARIABLE_INVALID_DATA = 9900,
  UNEXPECTED_ERROR = 9999,

  // AUTH
  UNAUTHORIZED = 1001,
  FORBIDDEN = 1002,
  USERNAME_NOT_FOUND = 1003,
  USERNAME_EXISTED = 1004,
  USERNAME_OR_PASSWORD_INCORRECT = 1005,
  USERNAME_MISSING = 1009,
  PASSWORD_MISSING = 1010,
  EMAIL_MISSING = 1011,
  AVATAR_MISSING = 1011,
  REFRESH_TOKEN_EXPIRED = 1012,
  REFRESH_TOKEN_MISSING = 1013,
  REFRESH_TOKEN_NOT_FOUND = 1014,

  // NOTIFICATION
  NOTIFICATION_TOKEN_NOT_FOUND = 1015,
  NOTIFICATION_TOKEN_MISSING = 1016,

  // STORAGE
  FILE_UPLOAD_ERROR = 2001,

  // VOCAB
  WORD_NOT_FOUND = 4001,
  WORD_EXAMPLE_NOT_FOUND = 4002,
  WORD_VALUE_MISSING = 4002,
  WORD_PHONETIC_MISSING = 4003,
  WORD_MEAN_MISSING = 4004,
  WORD_AUDIO_MISSING = 4005,
  WORD_IMAGE_MISSING = 4006,
  WORD_TYPE_MISSING = 4007,
  WORD_EXAMPLE_SENTENSE_MISSING = 4008,
  WORD_EXAMPLE_MEAN_MISSING = 4009,

  // EXAM
  NO_LESSON_WITH_RIGHT_LEVEL_OF_DIFFICULTY = 5000,
  LESSON_NOT_FOUND = 5001,
  QUESTION_NOT_FOUND = 5002,
  ANSWER_NOT_FOUND = 5003,
  LESSON_COVER_MISSING = 5004,
  LESSON_NAME_MISSING = 5005,
  LESSON_DIAMONDS_MISSING = 5006,
  LESSON_EXPERIENCES_MISSING = 5007,
  LESSON_IS_PLUS_ONLY = 5008,
  QUESTION_TYPE_MISSING = 5010,
  QUESTION_SENTENSE_AUDIO_MISSING = 5011,
  QUESTION_SENTENSE_WORDS_MISSING = 5012,
  QUESTION_SENTENSE_MEANING_MISSING = 5013,
  QUESTION_MATCHING_MISSING = 5014,
  CORRECT_ANSWER_NOT_FOUND = 5015,

  // HISTORY
  LESSON_HISTORY_NOT_FOUND = 6000,
  NOT_ENOUGH_DIAMOND = 3001,
}

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
  GIVE_AUDIO_CHOOSE_WORD = "Cho file âm thanh, chọn từ chính xác",
}

export enum LessonStatus {
  COMPLETED = "COMPLETED",
  ONGOING = "ONGOING",
}

export enum LessonType {
  FREE_ACCESS,
  PLUS_ONLY,
}

export enum ContentType {
  JSON = "application/json",
  FORM_DATA = "multipart/form-data",
  XML = "application/xml",
  TEXT = "text/plain",
  HTML = "text/html",
}
