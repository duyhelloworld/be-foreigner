package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts.IRemindService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/remind")
public class RemindController {
    
    private final IRemindService remindService;
    
    @GetMapping("test")
    public ApiResponse<String> sendTestNotification() {
        remindService.testCronJob();
        return ApiResponse.ok("Đã gửi thông báo vào " + LocalDateTime.now());
    }

}
