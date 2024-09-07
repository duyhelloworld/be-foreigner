package vn.edu.huce.beforeigner.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller

public class UserController {
    
    private IUserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllOnlineUser() {
        return userService.findAllUsers();
    }
    
}
