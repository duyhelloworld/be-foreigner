package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.core.SubscriptionPlan;
import vn.edu.huce.beforeigner.domains.core.TokenProvider;

@Data
@Builder
public class UserInfoDto {

    private Integer id;
    
    private String fullname;

    private String avatar;
    
    private String username;
    
    private String email;

    private Integer streakDays;

    private SubscriptionPlan plan;

    private Integer diamonds;

    private TokenProvider provider;

    private UserLevel level;

    private boolean isAllowMail;

    private boolean isAllowNotification;
}
