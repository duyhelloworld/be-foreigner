package vn.edu.huce.beforeigner.utils;

public class NotificationUtil {

    private static final String[] REMIND_BODYS = {
        "Hình như lâu rồi tôi chưa thấy bạn đó",
        "Lần cuối bạn mở tôi là khi nào nhỉ? Vào học ngay",
        "Hình như tôi chiều các em quá nên các em hư đúng không? Vào học ngayyyy",
        "Anh nhắc em, vào học ngay cho anh",
        "Bạn {} ơi! 🕒 Đến giờ học rồi nà",
    };

    private static final String[] REMIND_TITLES = {
        "Hú lô, lại là Liongo đây!🦁",
        "🤗Ôi bạn ơi🤗, sao bạn chưa vào học vậy",
        "{} ơi ❤️❤️❤️ bạn ỉm hơi lâu rồi á :D",
        "Gào gào gào 🦁🦁🦁, {} có ở đây không?",
    };

    private static final String extractFormatted(String[] data, String fullname) {
        int random = NumberUtils.randomNumber(0, data.length - 1);
        String result = data[random];
        return result.contains("{}") ? result.replace("{}", fullname) : result;
    }

    public static final String getRemindTitle(String fullname) {
        return extractFormatted(REMIND_TITLES, fullname);
    };

    public static final String getRemindBody(String fullname) {
        return extractFormatted(REMIND_BODYS, fullname);
    };
    
}
