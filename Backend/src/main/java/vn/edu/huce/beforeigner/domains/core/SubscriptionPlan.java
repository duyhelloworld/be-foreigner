package vn.edu.huce.beforeigner.domains.core;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Gói đăng kí người dùng
 */
@Getter
@AllArgsConstructor
public enum SubscriptionPlan {

    /**
     * Người dùng cơ bản
     */
    FREE("free", 0, "Gói miễn phí", "Gói người dùng cơ bản", List.of(
            "Truy cập các bài học cơ bản",
            "Sử dụng các bài kiểm tra từ vựng miễn phí",
            "Giới hạn học 3 bài mỗi ngày",
            "Không hỗ trợ tải bài học về ngoại tuyến")),
    /**
     * Gói VIP tháng
     */
    PLUS("plus", 100000, "100.000đ/năm", "Gói người dùng vip", List.of(
            "Truy cập không giới hạn tất cả bài học",
            "Tải bài học về sử dụng ngoại tuyến",
            "Không quảng cáo",
            "Hỗ trợ cá nhân hoá lộ trình học",
            "Tham gia bài kiểm tra nâng cao"));
    
    private String id;
    private Integer money;
    private String title;
    private String description;
    private List<String> benefits;
}
