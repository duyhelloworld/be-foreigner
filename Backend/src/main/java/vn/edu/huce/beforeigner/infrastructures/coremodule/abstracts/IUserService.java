package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UpdateProfileDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.UserRemindSettingDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface IUserService {
    
    PagingResult<UserDto> findAllUsers(PagingRequest pagingRequest);

    UserInfoDto getInfo(User user);

    void saveNotificationToken(User user, String token);

    void saveSetting(User user, UserRemindSettingDto settingDto);

    UserInfoDto updateProfile(User user, UpdateProfileDto updateProfileDto);
}
