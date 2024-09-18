package vn.edu.huce.beforeigner.infrastructures.coremodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;

@Component
public class UserMapper {
    
    public UserDto toDto(User user) {
        return UserDto.builder()
            .username(user.getUsername())
            .fullname(user.getFullname())
            .avatar(user.getAvatar())
            .build();
    }

    public UserInfoDto toInfoDto(User user) {
        return UserInfoDto.builder()
            .username(user.getUsername())
            .fullname(user.getFullname())
            .avatar(user.getAvatar())
            .email(user.getEmail())
            .build();
    }
}
