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
    boolean isValid(TokenType type, String token);

    /**
     * Thêm mới 1 token
     * @param type loại token
     * @param token giá trị token
     * @return token vừa được thêm vào
     */
    String addNew(TokenType type, String token);

    /**
     * Xóa token hiện tại
     * @param user
     */
    void disable(User user, String token, TokenType type);

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