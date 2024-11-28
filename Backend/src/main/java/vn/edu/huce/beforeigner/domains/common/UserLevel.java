package vn.edu.huce.beforeigner.domains.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Trình độ người dùng
 */
@Getter
@AllArgsConstructor
public enum UserLevel {
    /**
     * Thấp, người mới (A0, A1)
     */
    BEGINNER(0),
    /**
     * Biết chút chút (A2)
     */
    INTERMEDIATE(200),
    /**
     * Trung bình, người đã có nền tảng nhẹ (B1)
     */
    MEDIUM(500),
    /**
     * Khá ổn, cần hiểu sâu tiếng anh (B2)
     */
    ADVANCED(800);

    private Integer eloRequired;
}
