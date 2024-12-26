package vn.edu.huce.beforeigner.domains.core;

/**
 * Loại mã
 */
public enum TokenType {
    /**
     * Mã làm mới - refresh token
     */
    REFRESH,
    /**
     * Mã thông báo - firebase token
     */
    NOTIFICATION,

    /**
     * Mã xác nhận tài khoản. Hiện tại hỗ trợ verify qua mail
     */
    VERIFY_EMAIL,

    /**
     * Mã cập nhật mật khẩu
     */
    RESET_PASSWORD,
}
