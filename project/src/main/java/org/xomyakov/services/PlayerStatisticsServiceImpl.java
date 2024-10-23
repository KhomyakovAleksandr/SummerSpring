package org.xomyakov.services;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xomyakov.dto.PlayerStatisticsDto;
import org.xomyakov.models.Hero;
import org.xomyakov.models.Player;
import org.xomyakov.models.PlayerStatistics;
import org.xomyakov.models.Team;
import org.xomyakov.repositories.HeroRepository;
import org.xomyakov.repositories.PlayerRepository;
import org.xomyakov.repositories.TeamRepository;
import org.xomyakov.repositories.impl.PlayerStatisticsRepositoryimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PlayerStatisticsServiceImpl {

    private final PlayerStatisticsRepositoryimpl playerStatisticsRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final HeroRepository heroRepository;

    @Autowired
    public PlayerStatisticsServiceImpl(PlayerStatisticsRepositoryimpl playerStatisticsRepository,
                                       PlayerRepository playerRepository, TeamRepository teamRepository,
                                       HeroRepository heroRepository) {
        this.playerStatisticsRepository = playerStatisticsRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.heroRepository = heroRepository;
    }

    @Transactional
    public PlayerStatistics addPlayerStatistics(PlayerStatisticsDto playerStatisticsDto) {
        PlayerStatistics playerStatistics = new PlayerStatistics(); // Создаем объект PlayerStatistics

        Player player = playerRepository.findById(playerStatisticsDto.getPlayerId());
        if (player != null) {
            playerStatistics.setPlayer(player);
        } else {
            throw new EntityNotFoundException("Player not found");
        }

        Team team = teamRepository.findById(playerStatisticsDto.getTeamId());
        if (team != null) {
            playerStatistics.setTeam(team);
        } else {
            throw new EntityNotFoundException("Team not found");
        }

        Hero hero = heroRepository.findById(playerStatisticsDto.getHeroId());
        if (hero != null) {
            playerStatistics.setHero(hero);
        } else {
            throw new EntityNotFoundException("Hero not found");
        }

        // Устанавливаем остальные параметры
        playerStatistics.setGamesPlayed(playerStatisticsDto.getGamesPlayed());
        playerStatistics.setGamesWon(playerStatisticsDto.getGamesWon());

        // Сохраняем объект
        playerStatisticsRepository.save(playerStatistics);
        System.out.println("PlayerStatistics saved with ID: " + playerStatistics.getId());
        return playerStatistics;
    }

    @Transactional
    public List<PlayerStatistics> getAllPlayerStatistics() {
        return playerStatisticsRepository.findAll();
    }


    public List<HeroWinRateDto> getTopHeroesByPlayer(Long playerId, Long teamId) {
        // Получаем статистику игрока по его команде
        List<PlayerStatistics> statistics = playerStatisticsRepository.findByTeamIdAndPlayerId(teamId, playerId);

        // Хранение данных о выигранных и сыгранных играх
        Map<Hero, int[]> heroStats = new HashMap<>();

        // Сбор статистики
        for (PlayerStatistics ps : statistics) {
            Hero hero = ps.getHero();
            heroStats.putIfAbsent(hero, new int[2]); // [0] - выигранные, [1] - сыгранные
            heroStats.get(hero)[0] += ps.getGamesWon();
            heroStats.get(hero)[1] += ps.getGamesPlayed();
        }

        // Создаем список DTO с винрейтом
        return heroStats.entrySet().stream()
                .map(entry -> {
                    Hero hero = entry.getKey();
                    int gamesWon = entry.getValue()[0];
                    int gamesPlayed = entry.getValue()[1];
                    double winRate = gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0; // Обработка деления на 0
                    return new HeroWinRateDto(hero, winRate);
                })
                .sorted((a, b) -> Double.compare(b.getWinRate(), a.getWinRate()))
                .limit(5)
                .collect(Collectors.toList());
    }

    public static class HeroWinRateDto {
        private Hero hero;
        private double winRate;

        public HeroWinRateDto(Hero hero, double winRate) {
            this.hero = hero;
            this.winRate = winRate;
        }

        public Hero getHero() {
            return hero;
        }

        public double getWinRate() {
            return winRate;
        }
    }
}


