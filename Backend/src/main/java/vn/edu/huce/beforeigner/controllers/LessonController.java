package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.ILessonService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDetailDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/lesson")
public class LessonController {
    
    private final ILessonService lessonService;

    @IsUser
    @GetMapping("exam/{id}")
    public LessonDetailDto examine(
        @AuthenticationPrincipal User user,
        @PathVariable Integer id) {
            return lessonService.examine(id, user);
    }

    @PutMapping("examine/questions/{questionId}/retry")
    public void retry(
        @AuthenticationPrincipal User user,
        @PathVariable Integer questionId) {
        lessonService.retry(questionId, user);
    }
}
