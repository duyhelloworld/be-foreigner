package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.core.repo.AccountRepo;
import vn.edu.huce.beforeigner.domains.streak.repo.StreakRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAccountTokenService;
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

    private final AccountRepo accountRepo;

    private final UserMapper userMapper;

    private final ICloudFileService cloudFileService;

    private final IAccountTokenService userTokenService;

    private final StreakRepository streakRepository;

    @Override
    public PagingResult<UserDto> findAllUsers(PagingRequest pagingRequest) {
        return PagingResult.of(accountRepo.findByRole(Role.USER, pagingRequest.pageable()), u -> userMapper.toDto(u));
    }

    @Override
    public UserInfoDto getInfo(Account user) {
        return userMapper.toInfoDto(user);
    }

    @Override
    public UserInfoDto updateProfile(Account user, UpdateProfileDto updateProfileDto) {
        if (StringUtils.hasText(updateProfileDto.getAvatar())) {
            if (StringUtils.hasText(user.getAvatarPublicId())) {
                cloudFileService.delete(user.getAvatarPublicId());
            } 
            var response = cloudFileService.save(updateProfileDto.getAvatar(), CloudFileType.USER_AVATAR);
            user.setAvatarUrl(response.getUrl());
            user.setAvatarPublicId(response.getPublicId());
        }
        if (StringUtils.hasText(updateProfileDto.getFullname())) {
            user.setFullname(updateProfileDto.getFullname());
        }
        accountRepo.save(user);
        return userMapper.toInfoDto(user);
    }

    @Override
    public void saveSetting(Account user, UserRemindSettingDto settingDto) {
        // var settings = accountSettingRepo.findByOwnerAndIsEnabledAndRemindType(user.getUsername(), RemindType.LEARN_REMIND);
        // accountRepo.save(user);
    }

    @Override
    public void setup(Account user, SetupDto setupDto) {
        userTokenService.addNew(TokenType.NOTIFICATION, setupDto.getToken());
    }

    @Override
	public StreakDto streak(Account user) {
        var builder = StreakDto.builder();
        var streak = streakRepository.findByOwner(user.getUsername())
            .orElseThrow(() -> new AppException(ResponseCode.UNEXPECTED_ERROR));
        if (streak.isPlusStreak()) {
            // User đã học trước đó
            builder.streakDays(streak.getCurrentStreak())
                .hasLearned(true);
        } else {
            // User chưa học, tăng streak và set trạng thái đã học
            streak.plusStreak();
            streak.setPlusStreak(true);
            accountRepo.save(user);
            builder.streakDays(streak.getCurrentStreak())
                .hasLearned(false);
        }
        return builder.build();
	}
}
