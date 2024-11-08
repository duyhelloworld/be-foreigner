package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAuthenticated;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.rankingmodule.abstracts.IRankingService;
import vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos.RankingDto;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final IRankingService rankingService;

    @GetMapping
    @IsAuthenticated
    public RankingDto fetchRank(
            @AuthenticationPrincipal User user) {
        return rankingService.fetch(user);
    }
}
