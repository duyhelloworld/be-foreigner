package vn.edu.huce.beforeigner.entities.learn;

public enum WordType {
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
    CONJUNCTIONS;

    public static WordType caseSensitiveValue(String string) {
        return WordType.valueOf(string.toUpperCase());
    }

}
