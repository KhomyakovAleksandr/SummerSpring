package org.xomyakov.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xomyakov.dto.PlayerStatisticsDto;
import org.xomyakov.models.PlayerStatistics;
import org.xomyakov.services.PlayerStatisticsServiceImpl;


import java.util.List;

@RestController
@RequestMapping("/api/player-statistics")
public class PlayerStatisticsController {

    private final PlayerStatisticsServiceImpl playerStatisticsService;

    @Autowired
    public PlayerStatisticsController(PlayerStatisticsServiceImpl playerStatisticsService) {
        this.playerStatisticsService = playerStatisticsService;
    }

    @PostMapping("/add")
    public ResponseEntity<PlayerStatistics> addPlayerStatistics(@RequestBody PlayerStatisticsDto playerStatisticsDto) {
        PlayerStatistics playerStatistics = playerStatisticsService.addPlayerStatistics(playerStatisticsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerStatistics);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerStatistics>> getAllPlayerStatistics() {
        List<PlayerStatistics> playerStatistics = playerStatisticsService.getAllPlayerStatistics();
        return ResponseEntity.ok(playerStatistics);
    }

    @GetMapping("/top-heroes")
    public List<PlayerStatisticsServiceImpl.HeroWinRateDto> getTopHeroes(
            @RequestParam Long playerId,
            @RequestParam Long teamId) {
        return playerStatisticsService.getTopHeroesByPlayer(playerId, teamId);
    }
}
