package org.xomyakov.services;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xomyakov.dto.HeroDto;
import org.xomyakov.dto.PlayerStatisticsDto;
import org.xomyakov.models.Hero;
import org.xomyakov.repositories.PlayerStatisticsRepository;
import org.xomyakov.repositories.impl.HeroRepositoryimpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl{

    @Autowired
    private HeroRepositoryimpl heroRepository;

    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    public Hero addHero(HeroDto heroDto) {
        Hero hero = modelMapper.map(heroDto, Hero.class);
        heroRepository.save(hero);
        System.out.println("Hero saved with ID: " + hero.getId());
        return hero;
    }


    @Transactional
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }


    public List<HeroDto> selectBestHeroesForFinal(List<Long> playerIds) {
        List<HeroDto> heroes = heroRepository.findAll().stream()
                .map(hero -> modelMapper.map(hero, HeroDto.class))
                .collect(Collectors.toList());

        Map<HeroDto, Double> heroScores = new HashMap<>();

        for (HeroDto heroDTO : heroes) {
            List<PlayerStatisticsDto> playerStats = playerStatisticsRepository.findByHeroId(heroDTO.getId()).stream()
                    .map(ps -> modelMapper.map(ps, PlayerStatisticsDto.class))
                    .collect(Collectors.toList());

            double averagePlayerWinRate = calculateAveragePlayerWinRate(heroDTO, playerStats);

            double heroScore = heroDTO.getWinRate() * averagePlayerWinRate;
            heroScores.put(heroDTO, heroScore);
        }

        List<HeroDto> sortedHeroes = heroScores.entrySet()
                .stream()
                .sorted(Map.Entry.<HeroDto, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return sortedHeroes.stream().limit(5).collect(Collectors.toList());
    }

    private double calculateAveragePlayerWinRate(HeroDto heroDTO, List<PlayerStatisticsDto> playerStats) {
        double totalWinRate = playerStats.stream()
                .filter(ps -> ps.getGamesPlayed() > 0)
                .mapToDouble(ps -> (double) ps.getGamesWon() / ps.getGamesPlayed())
                .average()
                .orElse(0.0);
        return totalWinRate;
    }
}
