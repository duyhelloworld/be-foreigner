package vn.edu.huce.beforeigner.utils;

import java.util.Random;

public class NumberUtils {

    private static final Random random = new Random();

    public static boolean notNullAndGreaterThanZero(Integer number) {
        return number != null && number > 0;
    }

    public static int randomNumber(int start, int end) {
        return start + random.nextInt(end - start + 1);
    }
    
}
