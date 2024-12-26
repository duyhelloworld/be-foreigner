package vn.edu.huce.beforeigner.domains.exam;

/**
 * Loại câu hỏi
 */
public enum QuestionType {
    /**
     * Học từ tiếng anh
     */
    LEARN_WORD,
    /**
     * Cho âm thanh điền từ
     */
    GIVE_AUDIO_ENTER_WORD,
    /**
     * Cho nghĩa điền từ
     */
    GIVE_MEAN_ENTER_WORD,
    /**
     * Cho file nghe TA, sắp xếp từ TA
     */
    GIVE_AUDIO_REARRANGE_WORDS,
    /**
     * Cho câu từ TV, sắp xếp các từ TA
     */
    GIVE_SENTENSE_REARRANGE_WORDS,
    /**
     * Cho nghĩa TV, chọn từ TA
     */
    GIVE_MEAN_CHOOSE_WORD,
    /**
     * Cho audio TA, chọn từ được phát âm
     */
    GIVE_AUDIO_CHOOSE_WORD,
    /**
     * Cho âm thanh, đọc ghi âm
     */
    GIVE_AUDIO_CHECK_RECORD,
    /**
     * Cho từ, đọc ghi âm
     */
    GIVE_WORD_CHECK_RECORD,
    /**
     * Cho câu đọc ghi âm
     */
    GIVE_SENTENSE_CHECK_RECORD,
    /**
     * Cho âm thanh nhập câu nghe được
     */
    GIVE_AUDIO_ENTER_SENTENSE,
    /**
     * Chọn các đáp án đồng nghĩa
     */
    GIVE_WORD_CHOOSE_SYNONYMOUS_WORDS
}
