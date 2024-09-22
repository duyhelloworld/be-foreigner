package vn.edu.huce.beforeigner.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(1000, "OK"),
    UNEXPECTED_ERROR(9999, "Lỗi không xác định"),

    UNAUTHORIZED(1001, "Không thể xác thực"), 
    FORBIDDEN(1002, "Bạn không có quyền truy cập"),
    USERNAME_NOT_FOUND(1003, "Không tồn tại tài khoản"),
    USERNAME_EXISTED(1004, "Tên tài khoản đã tồn tại"),
    USERNAME_OR_PASSWORD_INCORRECT(1005, "Sai tài khoản hoặc mật khẩu"),
    USERNAME_MISSING(1009, "Không được bỏ trống tên tài khoản"),
    PASSWORD_MISSING(1010, "Không được bỏ trống mật khẩu"),
    EMAIL_MISSING(1011, "Không được bỏ trống email"),
    AVATAR_MISSING(1011, "Không được bỏ trống ảnh đại diện"),
    REFRESH_TOKEN_EXPIRED(1012, "Phiên đăng nhập hết hạn"),
    REFRESH_TOKEN_MISSING(1013, "Mã làm mới bị thiếu"),
    REFRESH_TOKEN_NOT_FOUND(1014, "Không tìm thấy mã làm mới"),
    NOTIFICATION_TOKEN_NOT_FOUND(1015, "Không tìm thấy mã thông báo"),
    NOTIFICATION_TOKEN_MISSING(1016, "Mã thông báo bị thiếu"),
    
    TOPIC_NOT_FOUND(2000, "Không tìm thấy chủ đề này"),
    WORD_NOT_FOUND(2001, "Không tìm thấy từ này"),
    QUESTION_NOT_FOUND(2002, "Không tìm thấy câu hỏi này"), 
    LESSON_NOT_FOUND(2003, "Không tìm thấy bài học này"),  
    MATCH_ANSWER_NOT_FOUND(2004, "Không tìm thấy đáp án để nối"),   
    NO_LESSON_HAVE_THIS_DIFF_LEVEL(2004, "Không có bài học nào có độ khó phù hợp với bạn"),
    HAVENT_BEEN_LEARN_THIS_WORD(2004, "Bạn chưa từng học từ này");

    private int code;

    private String message;
}
