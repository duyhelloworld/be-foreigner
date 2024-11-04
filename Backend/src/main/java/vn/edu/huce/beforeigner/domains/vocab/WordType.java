package vn.edu.huce.beforeigner.domains.vocab;

import org.springframework.util.StringUtils;

/**
 * Loại từ
 */
public enum WordType {

    /**
     * Mạo từ xác định
     */
    DEFINITE_ARTICLE,

     /**
     * Mạo từ không xác định
     */
    INDEFINITE_ARTICLE,

    /**
     * Danh từ
     */
    NOUN,
    
    /**
     * Động từ
     */
    VERB,

    /**
     * Tính từ
     */
    ADJECTIVE,

    /**
     * Trạng từ
     */
    ADVERB,

    /**
     * Đại từ
     */
    PRONOUN,

    /**
     * Giới từ
     */
    PREPOSITION,

    /**
     * Thán từ
     */
    INTERJECTION,

    /**
     * Từ hạn định
     */
    DETERMINER,

    /**
     * Liên từ
     */
    CONJUNCTION;

    public static WordType caseSensitiveValue(String string) {
        if (!StringUtils.hasText(string)) {
            return WordType.NOUN;
        }
        return WordType.valueOf(string.toUpperCase());
    }
}
