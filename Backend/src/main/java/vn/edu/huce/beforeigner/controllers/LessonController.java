package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.ILessonService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.CompletedLessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/lesson")
public class LessonController {

    private final ILessonService lessonService;

    @IsUser
    @GetMapping("exam/{id}")
    public ApiResponse<LessonDetailDto> examine(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id) {
        return ApiResponse.ok(lessonService.examine(id, user));
    }

    @IsUser
    @GetMapping("suggest")
    public ApiResponse<PagingResult<LessonDto>> getSuggestedLessons(
            @AuthenticationPrincipal User user,
            PagingRequest pagingRequest) {
        return ApiResponse.ok(lessonService.getSuggestedLessons(pagingRequest, user));
    }

    @IsUser
    @PutMapping("exam/complete")
    public ApiResponse<Void> completed(
            @AuthenticationPrincipal User user,
            @RequestBody CompletedLessonDto completedLessonDto) {
        lessonService.completed(completedLessonDto, user);
        return ApiResponse.ok();
    }
}