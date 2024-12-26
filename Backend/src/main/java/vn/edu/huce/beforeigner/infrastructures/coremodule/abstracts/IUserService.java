package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SetupDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UpdateProfileDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.StreakDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.UserRemindSettingDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface IUserService {
    
    PagingResult<UserDto> findAllUsers(PagingRequest pagingRequest);

    UserInfoDto getInfo(Account user);

    void setup(Account user, SetupDto setupDto);

    void saveSetting(Account user, UserRemindSettingDto settingDto);

    UserInfoDto updateProfile(Account user, UpdateProfileDto updateProfileDto);

    StreakDto streak(Account user);
}
