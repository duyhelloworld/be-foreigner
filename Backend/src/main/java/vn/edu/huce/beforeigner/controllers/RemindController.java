package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.ReadRemindDto;
import vn.edu.huce.beforeigner.annotations.security.IsUser;
import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.remind.NotificationMethod;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts.IRemindService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.RemindDto;
import vn.edu.huce.beforeigner.utils.apiresponse.ApiResponse;


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

    @IsUser
    @GetMapping("sync")
    public ApiResponse<List<RemindDto>> sync(
    @RequestParam(defaultValue = "NOTIFICATION") String method,    
    @AuthenticationPrincipal Account user) {
        try {
            return ApiResponse.ok(remindService.syncNotification(user, NotificationMethod.valueOf(method)));
        } catch (Exception e) {
            throw new AppException(ResponseCode.INVALID_REQUEST);
        }
    }

    @IsUser
    @PostMapping("read")
    public ApiResponse<Void> mardRead(@AuthenticationPrincipal Account user, @RequestBody ReadRemindDto readRemindDto) {
        remindService.markRead(user, readRemindDto.remindIds);
        return ApiResponse.ok();
    }
}
