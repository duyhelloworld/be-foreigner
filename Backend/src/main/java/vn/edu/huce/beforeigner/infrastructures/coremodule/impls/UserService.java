package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SetupDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UpdateProfileDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.StreakDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.mappers.UserMapper;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.UserRemindSettingDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepo;

    private final UserMapper userMapper;

    private final ICloudFileService cloudFileService;

    private final IUserTokenService userTokenService;

    @Override
    public PagingResult<UserDto> findAllUsers(PagingRequest pagingRequest) {
        return PagingResult.of(userRepo.findByRole(Role.USER, pagingRequest.pageable()), u -> userMapper.toDto(u));
    }

    @Override
    public UserInfoDto getInfo(User user) {
        return userMapper.toInfoDto(user);
    }

    @Override
    public UserInfoDto updateProfile(User user, UpdateProfileDto updateProfileDto) {
        if (StringUtils.hasText(updateProfileDto.getAvatar())) {
            if (StringUtils.hasText(user.getAvatarPublicId())) {
                cloudFileService.delete(user.getAvatarPublicId());
            } 
            var response = cloudFileService.save(updateProfileDto.getAvatar(), "", CloudFileType.USER_AVATAR);
            user.setAvatarUrl(response.getUrl());
            user.setAvatarPublicId(response.getPublicId());
            user.setAvatarFilename(response.getFilename());
        }
        if (StringUtils.hasText(updateProfileDto.getFullname())) {
            user.setFullname(updateProfileDto.getFullname());
        }
        userRepo.save(user);
        return userMapper.toInfoDto(user);
    }

    @Override
    public void saveSetting(User user, UserRemindSettingDto settingDto) {
        user.setAllowMail(settingDto.isAllowMail);
        user.setAllowNotification(settingDto.isAllowNotification);
        userRepo.save(user);
    }

    @Override
    public void setup(User user, SetupDto setupDto) {
        userTokenService.addNew(TokenType.NOTIFICATION, setupDto.getToken());
    }

    @Override
	public StreakDto streak(User user) {
        var builder = StreakDto.builder();
        if (user.isShowedStreak()) {
            // User đã học trước đó
            builder.streakDays(user.getStreakDays())
                .hasLearned(true);
        } else {
            // User chưa học, tăng streak và set trạng thái đã học
            user.setStreakDays(user.getStreakDays() + 1);
            user.setShowedStreak(true);
            userRepo.save(user);
            builder.streakDays(user.getStreakDays())
                .hasLearned(false);
        }
        return builder.build();
	}
}
