package vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos;

import lombok.Data;

@Data
public class UserRemindSettingDto {

    private boolean isAllowMail;

    private boolean isAllowNotification;
    
}
