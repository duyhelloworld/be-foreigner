package vn.edu.huce.beforeigner.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.annotations.IsAdmin;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ApiResponse<List<UserDto>> getAllUsers() {
        return ApiResponse.ok(userService.findAllUsers());
    }
    
    @IsUser
    @GetMapping
    public ApiResponse<UserInfoDto> getMyInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.ok(userService.getInfo(user));
    }
}
