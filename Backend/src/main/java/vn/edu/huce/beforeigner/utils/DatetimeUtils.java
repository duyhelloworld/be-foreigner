package vn.edu.huce.beforeigner.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class DatetimeUtils {

    public static Date getJavaSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    public static Date getJavaSqlDate(LocalDateTime localDateTime) {
        return Date.valueOf(localDateTime.toLocalDate());
    }
}
