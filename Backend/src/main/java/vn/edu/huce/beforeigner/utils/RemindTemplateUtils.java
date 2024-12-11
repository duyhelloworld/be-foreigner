package vn.edu.huce.beforeigner.utils;

import vn.edu.huce.beforeigner.domains.remind.RemindFrequence;
import vn.edu.huce.beforeigner.domains.remind.RemindMethod;
import vn.edu.huce.beforeigner.domains.remind.RemindType;
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
                "Xin chào {}, đã lâu không thấy bạn học tập 🕒. Hãy quay lại và bắt đầu học nhé! 🦁🦁🦁",
                RemindMethod.NOTIFICATION, RemindType.LEARN_REMIND,
                RemindFrequence.LOW),
            new TemplateDto("Học tập chăm chỉ nào!",
                "Chào {}! Hôm nay bạn đã học được thêm từ mới nào chưa?",
                RemindMethod.NOTIFICATION, RemindType.LEARN_REMIND,
                RemindFrequence.MEDIUM),
            new TemplateDto("Học tập chăm chỉ nào!",
                "Tuyệt vời {}! Hãy tiếp tục duy trì chuỗi học tập của bạn nhé! 🦁🦁🦁",
                RemindMethod.NOTIFICATION,
                RemindType.LEARN_REMIND, RemindFrequence.HIGH),
            new TemplateDto("Học từ vựng mới!",
                "Bạn đã bỏ lỡ một số từ vựng thú vị. Hãy quay lại và học ngay nào ❤️❤️❤️ {}!",
                RemindMethod.EMAIL,
                RemindType.WORD_LEARNING, RemindFrequence.LOW),
            new TemplateDto("Học từ vựng mới!", "Tiếp tục thêm từ mới vào vốn từ vựng của bạn, {}!",
                RemindMethod.NOTIFICATION, RemindType.WORD_LEARNING,
                RemindFrequence.MEDIUM),
            new TemplateDto("Học từ vựng mới!",
                "Xuất sắc {}! Bạn có thể học thêm nhiều từ mới hôm nay không?",
                RemindMethod.NOTIFICATION, RemindType.WORD_LEARNING,
                RemindFrequence.HIGH),
            new TemplateDto("🤗Ôi bạn ơi🤗",
                "Chào {}, chúng tôi có một số cập nhật mới cho bạn. Hãy kiểm tra ngay bây giờ!",
                RemindMethod.NOTIFICATION, RemindType.NEW_UPDATE, null),
            new TemplateDto("Ưu đãi đặc biệt!",
                "Xin chào {}, bạn có ưu đãi đặc biệt từ chúng tôi. Đừng bỏ lỡ!",
                RemindMethod.NOTIFICATION, RemindType.OFFERS, null)));
    }

    public static TemplateDto getTemplate(RemindMethod method, User user) {
        return TEMPLATES.stream()
            .filter(template -> template.getMethod() == method)
            .findAny()
            .map(template -> {
                String formattedBody = String.format(template.getBody(), user.getFullname());
                template.setBody(formattedBody);
                return template;
            })
            .orElseThrow(() -> new AppException(
                    ResponseCode.NOTIFICATION_MESSAGE_MAY_NOT_INITILIZED));
    }
}
