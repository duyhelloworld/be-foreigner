package vn.edu.huce.beforeigner.domains.core;

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
    BEGINNER(0, 20),
    /**
     * Biết chút chút (A2)
     */
    INTERMEDIATE(21, 40),
    /**
     * Trung bình, người đã có nền tảng nhẹ (B1)
     */
    MEDIUM(41, 60),
    /**
     * Khá ổn, cần hiểu sâu tiếng anh (B2)
     */
    ADVANCED(61, 80);

    private Integer levelStart;
    
    private Integer levelEnd;
}
