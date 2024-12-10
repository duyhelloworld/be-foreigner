package vn.edu.huce.beforeigner.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.annotations.IsAdmin;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;

import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SetupDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UpdateProfileDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.StreakDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.UserRemindSettingDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @IsAdmin
    @GetMapping("/all")
    public ApiResponse<PagingResult<UserDto>> getAllUsers(PagingRequest pagingRequest) {
        return ApiResponse.ok(userService.findAllUsers(pagingRequest));
    }

    @IsUser
    @GetMapping("/my-info")
    public ApiResponse<UserInfoDto> getMyInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.ok(userService.getInfo(user));
    }

    @IsUser
    @PutMapping("my-info")
    public ApiResponse<UserInfoDto> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestBody UpdateProfileDto updateProfileDto) {
        return ApiResponse.ok(userService.updateProfile(user, updateProfileDto));
    }

    @IsUser
    @PostMapping("setup")
    public ApiResponse<Void> setUpInformation(
            @AuthenticationPrincipal User user,
            @RequestBody SetupDto setupDto) {
        userService.setup(user, setupDto);
        return ApiResponse.ok();
    }

    @IsUser
    @GetMapping("streak")
    public ApiResponse<StreakDto> streak(@AuthenticationPrincipal User user) {
        return ApiResponse.ok(userService.streak(user));
    }

    @IsUser
    @PutMapping("notification/setting")
    public ApiResponse<Void> save(
            @AuthenticationPrincipal User user,
            @RequestBody UserRemindSettingDto settingDto) {
        userService.saveSetting(user, settingDto);
        return ApiResponse.ok();
    }

}
