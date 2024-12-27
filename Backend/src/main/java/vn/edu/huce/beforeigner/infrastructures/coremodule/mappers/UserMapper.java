package vn.edu.huce.beforeigner.infrastructures.coremodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;

@Component
public class UserMapper {
    
    public UserDto toDto(Account user) {
        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .fullname(user.getFullname())
            .avatar(user.getAvatarUrl())
            .build();
    }

    public UserInfoDto toInfoDto(Account user) {
        return UserInfoDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .fullname(user.getFullname())
            .avatar(user.getAvatarUrl())
            .email(user.getEmail())
            .level(user.getLevel())
            .plan(user.getPlan())
            .build();
    }
}
