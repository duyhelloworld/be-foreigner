package vn.edu.huce.beforeigner.utils;

import java.util.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public abstract class DatetimeUtils {

    public static Date getDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    public static String formatDuration(Long seconds) {
        if (seconds == null) {
            return null;
        }
        Duration duration = Duration.ofSeconds(seconds);

        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secondsPart = duration.toSecondsPart();

        StringBuilder sb = new StringBuilder();

        if (hours > 0) {
            sb.append(hours).append(" giờ ");
        }
        if (minutes > 0) {
            sb.append(minutes).append(" phút ");
        }
        if (secondsPart > 0 || sb.length() == 0) {
            sb.append(secondsPart).append(" giây");
        }

        return sb.toString().trim();
    }
}
