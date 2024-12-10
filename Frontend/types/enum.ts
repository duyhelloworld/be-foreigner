export enum ApiResponseCode {
  OK = 1000,
  ERROR_WHEN_VALIDATE = 9000,
  UNEXPECTED_ERROR = 9999,

  // AUTH
  UNAUTHORIZED = 1001,
  FORBIDDEN = 1002,
  USERNAME_NOT_FOUND = 1003,
  USERNAME_EXISTED = 1004,
  USERNAME_OR_PASSWORD_INCORRECT = 1005,
  USERNAME_MISSING = 1006,
  PASSWORD_MISSING = 1007,
  EMAIL_MISSING = 1008,
  USERNAME_INVALID = 1009,
  PASSWORD_INVALID = 1010,
  REFRESH_TOKEN_EXPIRED = 1011,
  REFRESH_TOKEN_MISSING = 1012,
  REFRESH_TOKEN_NOT_FOUND = 1013,
  EMAIL_ADDRESS_MAY_NOT_EXIST = 1014,
  CODE_INVALID = 1015,
  INVALID_REQUEST = 1016,
  PASSWORD_LENGTH_NOT_ENOUGH = 1017,

  // NOTIFICATION
  NOTIFICATION_TOKEN_NOT_FOUND = 1018,
  NOTIFICATION_TOKEN_MISSING = 1019,
  NOTIFICATION_TOKEN_INVALID = 1020,

  // MAIL
  NEW_PASS_IS_SAME_WITH_OLD = 1021,
  WRONG_RESET_PASSWORD_CODE = 1022,
  TOKEN_EXISTED = 1021,

  // STORAGE
  FILE_UPLOAD_ERROR = 1022,
  DEFAULT_FILE_NOT_FOUND = 1023,

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
  LESSON_ALREADY_COMPLETED = 5007,
  LESSON_IS_PLUS_ONLY = 5008,
  QUESTION_TYPE_MISSING = 5010,
  QUESTION_SENTENSE_AUDIO_MISSING = 5011,
  QUESTION_SENTENSE_WORDS_MISSING = 5012,
  QUESTION_SENTENSE_MEANING_MISSING = 5013,
  QUESTION_MATCHING_MISSING = 5014,
  CORRECT_ANSWER_NOT_FOUND = 5015,

  // HISTORY
  LESSON_HISTORY_NOT_FOUND = 6000,
  RETRY_COUNT_UNAVAILABLE = 6001,

  // LEADER BOARD
  LEADER_BOARD_TYPE_UNDEFINED = 7000,
  LEADER_BOARD_TYPE_INVALID = 7001,
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

export interface PlanOption {
  id: number;
  title: SubscriptionPlan;
  price: string;
  description: string;
}

export function getPlanOptions() : PlanOption[] {
  return [
    {
      id: 0,
      title: SubscriptionPlan.FREE,
      price: "Miễn phí",
      description: "Truy cập các tính năng cơ bản",
    },
    {
      id: 2,
      title: SubscriptionPlan.PREMIUM_YEAR,
      price: "499.000đ/năm",
      description: "Truy cập đầy đủ tính năng, tiết kiệm 15%",
    },
    {
      id: 1,
      title: SubscriptionPlan.PREMIUM_MONTH,
      price: "99.000đ/tháng",
      description: "Truy cập đầy đủ tính năng, thanh toán hàng tháng",
    },
    {
      id: 3,
      title: SubscriptionPlan.LIFETIME,
      price: "999.000đ",
      description: "Truy cập trọn đời, không giới hạn",
    },
  ];
}

export enum QuestionLevel {
  EASY,
  HARD,
}

export enum QuestionType {
  LEARN_WORD = "Học từ",
  GIVE_MEAN_ENTER_WORD = "Điền từ mang nghĩa sau",
  GIVE_AUDIO_ENTER_WORD = "Điền từ mà bạn nghe được",
  GIVE_AUDIO_REARRANGE_WORDS = "Sắp xếp lại các từ mà bạn nghe được",
  GIVE_SENTENSE_REARRANGE_WORDS = "Sắp xếp lại các từ để được câu bên dưới",
  GIVE_MEAN_CHOOSE_WORD = "Chọn đáp án đúng",
  GIVE_AUDIO_CHOOSE_WORD = "Chọn từ mà bạn nghe được",
}

export enum LeaderBoardType {
  WEEKLY = "Tuần",
  MONTHLY = "Tháng",
  ALL_TIME = "Tất cả",
}

export enum LessonStatus {
  COMPLETED = "Hoàn thành",
  ONGOING = "Đang học",
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
