package vn.edu.huce.beforeigner.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(1000, "OK"),
    ERROR_WHEN_VALIDATE(999, "Lỗi kiểm tra thông tin"),
    SYSTEM_VARIABLE_INVALID_DATA(9900, "Error when access server config"),
    UNEXPECTED_ERROR(9999, "Lỗi không xác định"),
    
    // AUTH
    UNAUTHORIZED(1001, "Không thể xác thực"), 
    FORBIDDEN(1002, "Bạn không có quyền truy cập"),
    USERNAME_NOT_FOUND(1003, "Không tồn tại tài khoản"),
    USERNAME_EXISTED(1004, "Tên tài khoản đã tồn tại"),
    USERNAME_OR_PASSWORD_INCORRECT(1005, "Sai tài khoản hoặc mật khẩu"),
    USERNAME_MISSING(1009, "Không được bỏ trống tên tài khoản"),
    PASSWORD_MISSING(1010, "Không được bỏ trống mật khẩu"),
    EMAIL_MISSING(1011, "Không được bỏ trống email"),
    USERNAME_INVALID(1011, "Tên tài khoản không hợp lệ. Tài khoản hợp lệ là tên chỉ gồm các chữ cái và số"),
    PASSWORD_INVALID(1, "Mật khẩu không hợp lệ. Mật khẩu hợp lệ gồm 8 kí tự không bao gồm dấu cách"),
    REFRESH_TOKEN_EXPIRED(1012, "Phiên đăng nhập hết hạn"),
    REFRESH_TOKEN_MISSING(1013, "Mã làm mới bị thiếu"),
    REFRESH_TOKEN_NOT_FOUND(1014, "Không tìm thấy mã làm mới"),
    EMAIL_ADDRESS_MAY_NOT_EXIST(1015, "Địa chỉ email có thể không tồn tại"),
    CODE_INVALID(1016, "Mã không hợp lẹ"),
    INVALID_REQUEST(101, "Yêu cầu không hợp lệ"),
    
    // NOTIFICATION
    NOTIFICATION_TOKEN_NOT_FOUND(1015, "Không tìm thấy mã thông báo"),
    NOTIFICATION_TOKEN_MISSING(1016, "Mã thông báo bị thiếu"),
    NOTIFICATION_TOKEN_INVALID(1017, "Mã thông báo lỗi"),

    // MAIL
    NEW_PASS_IS_SAME_WITH_OLD(1018, "Mật khẩu mới không đựợc trùng với mật khẩu cũ"),
    WRONG_RESET_PASSWORD_CODE(1019, "Mã cập nhật mật khẩu không hợp lệ"), 

    TOKEN_EXISTED(1020, "Mã đã tồn tại trong hệ thống"),

    // STORAGE
    FILE_UPLOAD_ERROR(2001, "Xảy ra lỗi khi tải lên file"),
    DEFAULT_FILE_NOT_FOUND(2002, "Ảnh đại diện mặc định không có trên hệ thống"),

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

    // HISTORY
    LESSON_HISTORY_NOT_FOUND(6000, "Không tìm thấy lịch sử học với bài học này"),
    RETRY_COUNT_UNAVAILABLE(6001, "Hết lượt dùng miễn phí" ),

    // LEADER BOARD
    LEADER_BOARD_TYPE_UNDEFINED(7000, "Loại bảng xếp hạng chưa được định nghĩa"),
    LEADER_BOARD_TYPE_INVALID(7001, "Loại bảng xếp hạng không hợp lệ"); 

    private int code;

    private String message;
}
