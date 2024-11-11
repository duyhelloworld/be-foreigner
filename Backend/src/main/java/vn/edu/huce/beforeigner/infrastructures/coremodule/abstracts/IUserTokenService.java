package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;

public interface IUserTokenService {

    /**
     * Kiểm tra token 
     * @param token
     * @return
     */
    boolean isValidRefreshToken(String token);

    /**
     * Thêm mới 1 token
     * @param user người được thêm
     * @param type loại token
     * @param token giá trị token
     * @return token vừa được thêm vào
     */
    String addNew(User user, TokenType type, String token);

    /**
     * Đặt cờ disable token
     * @param user
     */
    void expired(User user);

    /**
     * Làm mới mã làm mới
     * @param user
     * @param refreshToken
     * @return mã làm mới
     */
    AuthDto renewAccess(User user, String refreshToken);

    /**
     * Tạo mã refresh token
     * @return
     */
    String generateRefreshToken();
}