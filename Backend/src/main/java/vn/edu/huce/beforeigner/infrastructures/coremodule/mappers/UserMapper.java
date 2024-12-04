package vn.edu.huce.beforeigner.infrastructures.coremodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;

@Component
public class UserMapper {
    
    public UserDto toDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .fullname(user.getFullname())
            .avatar(user.getAvatarUrl())
            .build();
    }

    public UserInfoDto toInfoDto(User user) {
        return UserInfoDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .fullname(user.getFullname())
            .avatar(user.getAvatarUrl())
            .email(user.getEmail())
            .isAllowMail(user.isAllowMail())
            .isAllowNotification(user.isAllowNotification())
            .level(user.getLevel())
            .plan(user.getPlan())
            .build();
    }
}
