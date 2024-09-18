package vn.edu.huce.beforeigner.utils;

public class StringUtils {

    public static String standardizeTopic(String topicName) {
        return topicName.toLowerCase().strip().replace(" ", "_");
    }
}
