package vn.edu.huce.beforeigner.domains.exam;

public enum QuestionType {
    /**
     * Điền vào chỗ trống
     */
    FILL_IN_BLANK,
    /**
     * Nối đáp án
     */
    MATCHING,
    /**
     * Chọn đáp án chứa nghĩa đúng
     */
    CHOOSE_CORRECT_MEAN,
    /**
     * Chọn đáp án chứa từ đúng
     */
    CHOOSE_CORRECT_WORD,
    /**
     * Cho từ, ghi nghĩa
     */
    WORD_TO_MEAN,
    /**
     * Cho nghĩa, điền từ
     */
    MEAN_TO_WORD,
}
