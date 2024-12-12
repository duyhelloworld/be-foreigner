package vn.edu.huce.beforeigner.utils;

import vn.edu.huce.beforeigner.domains.remind.RemindMethod;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.TemplateDto;

import java.util.ArrayList;
import java.util.List;

public class RemindTemplateUtils {

    private static final List<TemplateDto> TEMPLATES = new ArrayList<>();

    static {
        TEMPLATES.addAll(List.of(
                new TemplateDto("Học tập chăm chỉ nào!",
                        "Xin chào %s, đã lâu không thấy bạn học tập 🕒. Hãy quay lại và bắt đầu học nhé! 🦁🦁🦁"),
                new TemplateDto("Bạn tôi ơi, bảnh mắt rồi!",
                        "Chào %s! Hôm nay bạn đã học được thêm từ mới nào chưa?"),
                new TemplateDto("Hế nhô!",
                        "Tuyệt vời %s! Hãy tiếp tục duy trì chuỗi học tập của bạn nhé! 🦁🦁🦁"),
                new TemplateDto("Hôm nay tôi nhớ bạn quá!",
                        "Bạn đã bỏ lỡ một số từ vựng thú vị. Hãy quay lại và học ngay nào ❤️❤️❤️ %s!"),
                new TemplateDto("Nay bạn bận gì vậy, sao tôi chưa thấy bạn online nhỉ?",
                        "Tiếp tục thêm từ mới vào vốn từ vựng của bạn, %s!"),
                new TemplateDto("Học từ vựng mới đi!",
                        "Xuất sắc %s! Bạn có thể học thêm nhiều từ mới hôm nay không?"),
                new TemplateDto("🤗Ôi bạn ơi🤗",
                        "Chào %s, chúng tôi có một số cập nhật mới cho bạn. Hãy kiểm tra ngay bây giờ!"),
                new TemplateDto("Ưu đãi đặc biệt!",
                        "Xin chào %s, bạn có ưu đãi đặc biệt từ chúng tôi. Đừng bỏ lỡ!")));
    }

    public static TemplateDto getTemplate(RemindMethod method, User user) {
        if (TEMPLATES.isEmpty()) {
            throw new AppException(ResponseCode.NOTIFICATION_MESSAGE_MAY_NOT_INITILIZED);
        }
        TemplateDto template = TEMPLATES.get(NumberUtils.randomNumber(0, TEMPLATES.size() - 1));
        String formattedBody = String.format(template.getBody(), user.getFullname());
        template.setBody(formattedBody);
        return template;
    }
}