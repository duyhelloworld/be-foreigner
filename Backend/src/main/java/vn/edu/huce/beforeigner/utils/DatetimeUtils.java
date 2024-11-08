package vn.edu.huce.beforeigner.utils;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public abstract class DatetimeUtils {

    public static Date getDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
