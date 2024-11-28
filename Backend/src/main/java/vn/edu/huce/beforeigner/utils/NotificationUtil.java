package vn.edu.huce.beforeigner.utils;

public class NotificationUtil {

    private static final String[] REMIND_BODYS = {
        "Hú lô, lại là Liongo đây! Hình như lâu rồi tôi chưa thấy bạn đó",
        "Lần cuối bạn mở tôi là khi nào nhỉ? Vào học ngay",
        "Hình như tôi chiều các em quá nên các em hư đúng không? Vào học ngayyyy",
        "Anh nhắc em, vào học ngay cho anh"
    };

    private static final String[] REMIND_TITLES = {
        "🤗 Ôi bạn ơi 🤗, sao bạn chưa vào học vậy",
        "Bạn <h1>{}</h1> ơi</br>🕒 Đến giờ học rồi nà",
        "<h1>{}</h1> ơi ❤️❤️❤️</br> ỉm hơi lâu rồi á!",
        "Gào gào gào 🦁🦁🦁, <h1>{}</h1> có ở đây không?",
    };

    public static final String getRemindTitle(String fullname) {
        int random = NumberUtils.randomNumber(0, REMIND_TITLES.length - 1);
        String result = REMIND_TITLES[random];
        return result.contains("{}") ? result : String.format(result, fullname);
    };

    public static final String getRemindBody() {
        int random = NumberUtils.randomNumber(0, REMIND_BODYS.length - 1);
        return REMIND_BODYS[random];
    };

    
}
