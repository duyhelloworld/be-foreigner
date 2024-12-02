package vn.edu.huce.beforeigner.domains.exam;

public enum QuestionType {
    /**
     * Học từ tiếng anh
     */
    LEARN_BY_WORD,
    /**
     * Học phát âm từ tiếng anh 
     */
    LEARN_BY_AUDIO,
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
    GIVE_AUDIO_CHOOSE_WORD
}
