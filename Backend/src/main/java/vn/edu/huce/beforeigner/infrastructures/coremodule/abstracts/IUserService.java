package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;

public interface IUserService {
    
    List<UserDto> findAllUsers();

    UserInfoDto getInfo(User user);
}
