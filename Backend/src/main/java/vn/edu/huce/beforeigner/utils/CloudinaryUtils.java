package vn.edu.huce.beforeigner.utils;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtils {
    
    private static Map<String, Object> options = null;
    
    public static Map<String, Object> getOptions() {
        if (options == null) {
            options = new HashMap<>();
        } else {
            options.clear();
        }
        return options;
    }

}
