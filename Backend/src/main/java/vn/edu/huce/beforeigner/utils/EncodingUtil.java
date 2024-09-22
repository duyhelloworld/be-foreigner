package vn.edu.huce.beforeigner.utils;

import java.util.Collection;

public class EncodingUtil {


    public static boolean isValidQuestionString(String questionString) {
        return questionString.contains("{}");
    }

    public static String bulldAnswer(Collection<String> answers) {
        return answers.toString();
    }
}
