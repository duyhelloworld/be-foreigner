package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.notificationmodule.abstracts.INotificationService;

@AllArgsConstructor
@RestController
@RequestMapping("api/notification")
public class NotificationController {
    
    private INotificationService notificationService;

    @GetMapping("")
    public void test(@RequestParam String token) {
        notificationService.test(token);
    }
    
}
