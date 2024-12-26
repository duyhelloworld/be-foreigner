package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/leaderboard")
@RequiredArgsConstructor
public class RankingController {

    // private final ILeaderboardService rankingService;

    // @GetMapping
    // public ApiResponse<?> fetchRank(
    //         @RequestParam(defaultValue = "WEEKLY") String rankingType) {
    //     try {
    //         return ApiResponse.ok(rankingService.fetch(RankingType.valueOf(rankingType)));
    //     } catch (IllegalArgumentException e) {
    //         return ApiResponse.error(ResponseCode.LEADER_BOARD_TYPE_INVALID);
    //     }
    // }
}
