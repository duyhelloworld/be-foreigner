package vn.edu.huce.beforeigner.utils;

import java.util.HashMap;
import java.util.Map;

public class LessonUtil {
    
    private final static String ANSWER_DELIMITER = ";";
    private final static String WORD_DELIMITER = ":";

    public static Map<String, String> decode(String matching) {
        Map<String, String> map = new HashMap<>();
        // Bỏ dấu ở cuối chuỗi nếu có
        if (matching.endsWith(ANSWER_DELIMITER)) {
            matching = matching.substring(0, matching.length() - 1);
        }
        
        // Tách chuỗi thành các cặp
        String[] pairs = matching.split(ANSWER_DELIMITER);
        
        for (String pair : pairs) {
            // Tách mỗi cặp thành key và value
            String[] keyValue = pair.split(WORD_DELIMITER);
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }

    public static String encode(Map<String, String> map) {
        StringBuilder encodedString = new StringBuilder();
    
        for (Map.Entry<String, String> entry : map.entrySet()) {
            encodedString.append(entry.getKey())
                .append(WORD_DELIMITER)
                .append(entry.getValue())
                .append(ANSWER_DELIMITER);
        }
        // Xóa dấu phân tách cuối cùng
        if (encodedString.length() > 0) {
            encodedString.setLength(encodedString.length() - 1);
        }
        return encodedString.toString();
    }
}
