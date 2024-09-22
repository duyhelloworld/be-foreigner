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
}
