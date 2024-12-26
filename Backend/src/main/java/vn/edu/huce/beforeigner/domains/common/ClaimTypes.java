package vn.edu.huce.beforeigner.domains.common;

/**
 * Các claim trong jwt
 */
public enum ClaimTypes {
    /**
     * Tên tài khoản
     */
    USERNAME,
    /**
     * Mã tài khoản
     */
    USERID, 
    /**
     * Email
     */
    EMAIL, 
    /**
     * Vai trò
     */
    ROLE
}