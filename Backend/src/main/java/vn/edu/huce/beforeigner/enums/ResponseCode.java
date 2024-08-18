package vn.edu.huce.beforeigner.enums;

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
    TOKEN_EXPIRED(2001, "Phiên đăng nhập hết hạn");

    private int code;

    private String message;
}
