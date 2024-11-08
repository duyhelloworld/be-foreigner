package vn.edu.huce.beforeigner.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(1000, "OK"),
    UNEXPECTED_ERROR(9999, "Lỗi không xác định"),
    SYSTEM_VARIABLE_INVALID_DATA(9900, "Error when access server config"),
    
    // AUTH
    UNAUTHORIZED(1001, "Không thể xác thực"), 
    FORBIDDEN(1002, "Bạn không có quyền truy cập"),
    USERNAME_NOT_FOUND(1003, "Không tồn tại tài khoản"),
    USERNAME_EXISTED(1004, "Tên tài khoản đã tồn tại"),
    USERNAME_OR_PASSWORD_INCORRECT(1005, "Sai tài khoản hoặc mật khẩu"),
    USERNAME_MISSING(1009, "Không được bỏ trống tên tài khoản"),
    PASSWORD_MISSING(1010, "Không được bỏ trống mật khẩu"),
    EMAIL_MISSING(1011, "Không được bỏ trống email"),
    AVATAR_MISSING(1011, "Ảnh đại diện đang bị thiếu"),
    REFRESH_TOKEN_EXPIRED(1012, "Phiên đăng nhập hết hạn"),
    REFRESH_TOKEN_MISSING(1013, "Mã làm mới bị thiếu"),
    REFRESH_TOKEN_NOT_FOUND(1014, "Không tìm thấy mã làm mới"),
    
    // NOTIFICATION
    NOTIFICATION_TOKEN_NOT_FOUND(1015, "Không tìm thấy mã thông báo"),
    NOTIFICATION_TOKEN_MISSING(1016, "Mã thông báo bị thiếu"),

    // STORAGE
    FILE_UPLOAD_ERROR(2001, "Xảy ra lỗi khi tải lên file"),

    // VOCAB
    WORD_NOT_FOUND(4001, "Không tìm thấy từ này"),
    WORD_EXAMPLE_NOT_FOUND(4002, "Không tìm thấy ví dụ cho từ {}"),
    WORD_VALUE_MISSING(4002, "Thiếu mã từ vựng"),
    WORD_PHONETIC_MISSING(4003, "Thiếu phiên âm từ vựng"),
    WORD_MEAN_MISSING(4004, "Thiếu nghĩa từ vựng"),
    WORD_AUDIO_MISSING(4005, "Thiếu âm thanh từ vựng"),
    WORD_IMAGE_MISSING(4006, "Thiếu ảnh từ vựng"),
    WORD_TYPE_MISSING(4007, "Thiếu loại từ vựng"),
    WORD_EXAMPLE_SENTENSE_MISSING(4008, "Thiếu câu ví dụ cho từ vựng"),
    WORD_EXAMPLE_MEAN_MISSING(4009, "Thiếu nghĩa câu ví dụ cho từ vựng"),

    // EXAM
    NO_LESSON_WITH_RIGHT_LEVEL_OF_DIFFICULTY(5000, "Không có bài học nào có độ khó phù hợp với bạn"),
    LESSON_NOT_FOUND(5001, "Không tìm thấy bài học này"),  
    QUESTION_NOT_FOUND(5002, "Không tìm thấy câu hỏi này"), 
    ANSWER_NOT_FOUND(5003, "Không tìm thấy câu hỏi này"), 
    LESSON_COVER_MISSING(5004, "Thiếu ảnh bài học"),
    LESSON_NAME_MISSING(5005, "Thiếu tên bài học"),
    LESSON_DIAMONDS_MISSING(5006, "Thiếu số kim cương"),
    LESSON_EXPERIENCES_MISSING(5007, "Thiếu số kinh nghiệm đạt được"),
    LESSON_IS_PLUS_ONLY(5008, "Bài học bị khóa. Hãy nâng gói đăng kí của bạn để mở"),
    QUESTION_TYPE_MISSING(5010, "Thiếu loại câu hỏi"),
    QUESTION_SENTENSE_AUDIO_MISSING(5011, "Thiếu âm thanh của câu hỏi"),
    QUESTION_SENTENSE_WORDS_MISSING(5012, "Thiếu đáp án câu hỏi"),
    QUESTION_SENTENSE_MEANING_MISSING(5013, "Thiếu câu hỏi"),
    QUESTION_MATCHING_MISSING(5014, "Thiếu các đáp án cho câu hỏi nối"),
    CORRECT_ANSWER_NOT_FOUND(5015, "Không tìm thấy đáp án đúng"),

    NOT_ENOUGH_DIAMOND(3001, "Bạn không đủ kim cương cho thao tác này");

    private int code;

    private String message;
}
