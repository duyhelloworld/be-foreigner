package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import vn.edu.huce.beforeigner.infrastructures.historymodule.abstracts.ILessonHistoryService;
import vn.edu.huce.beforeigner.infrastructures.historymodule.dtos.LessonHistoryDto;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/lesson-history")
public class LessonHistoryController {
    
    private final ILessonHistoryService lessonHistoryService;

    @GetMapping("my-history")
    public ApiResponse<List<LessonHistoryDto>> getMyHistory(
        @AuthenticationPrincipal User user) {
        return ApiResponse.ok(lessonHistoryService.getMyHistory(user));
    }

    @GetMapping("statistic")
    public ApiResponse<Void> statistic(@RequestParam String param) {
        return ApiResponse.ok();
    }
    
    
}
