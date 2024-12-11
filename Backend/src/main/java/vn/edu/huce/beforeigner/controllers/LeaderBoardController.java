package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardType;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.abstracts.ILeaderboardService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/leaderboard")
@RequiredArgsConstructor
public class LeaderBoardController {

    private final ILeaderboardService rankingService;

    @GetMapping
    public ApiResponse<?> fetchRank(
            @RequestParam(defaultValue = "WEEKLY") String leaderBoardType) {
        try {
            return ApiResponse.ok(rankingService.fetch(LeaderBoardType.valueOf(leaderBoardType)));
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(ResponseCode.LEADER_BOARD_TYPE_INVALID);
        }
    }
}
