package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.core.SubscriptionPlan;

@Data
@Builder
public class UserInfoDto {

    private Integer id;
    
    private String fullname;

    private String avatar;
    
    private String username;
    
    private String email;

    private SubscriptionPlan plan;

    private Integer quota;

    private Integer streakDays;

    private UserLevel level;

    private boolean isAllowMail;

    private boolean isAllowNotification;
}
