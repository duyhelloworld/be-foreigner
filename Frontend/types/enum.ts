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
}

export enum UserLevel {
  BEGINNER = "Thấp, người mới (A0, A1)",
  INTERMEDIATE = "Biết chút chút (A2)",
  MEDIUM = "Trung bình, người đã có nền tảng nhẹ (B1)",
  ADVANCED = "Khá ổn, cần hiểu sâu tiếng anh (B2)",
}

export enum SubscriptionPlan {
  FREE = "Người dùng cơ bản",
  PLUS = "Gói VIP",
}

export interface PlanOption {
  id: SubscriptionPlan;
  title: string;
  price: number;
  benefits: string[];
  description: string;
}

export function getPlanOptions(): PlanOption[] {
  return [
    {
      id: SubscriptionPlan.FREE,
      title: "Gói miễn phí",
      price: 0,
      description: "Gói người dùng cơ bản",
      benefits: [
        "Truy cập các bài học cơ bản",
        "Sử dụng các bài kiểm tra từ vựng miễn phí",
        "Giới hạn học 3 bài mỗi ngày",
        "Không hỗ trợ tải bài học về ngoại tuyến",
      ],
    },
    {
      id: SubscriptionPlan.PLUS,
      title: "100.000đ/năm",
      price: 100000,
      description: "Gói người dùng VIP",
      benefits: [
        "Truy cập không giới hạn tất cả bài học",
        "Tải bài học về sử dụng ngoại tuyến",
        "Không quảng cáo",
        "Hỗ trợ cá nhân hoá lộ trình học",
        "Tham gia bài kiểm tra nâng cao",
      ],
    },  ];
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
