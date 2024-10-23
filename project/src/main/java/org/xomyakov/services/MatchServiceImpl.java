package org.xomyakov.services;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.xomyakov.dto.MatchDto;
import org.xomyakov.models.Match;
import org.xomyakov.models.Team;
import org.xomyakov.models.Tournament;
import org.xomyakov.repositories.impl.MatchRepositoryimpl;
import org.xomyakov.repositories.TeamRepository;
import org.xomyakov.repositories.TournamentRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MatchServiceImpl {

    private final MatchRepositoryimpl matchRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;



    @Autowired
    public MatchServiceImpl(MatchRepositoryimpl matchRepository, ModelMapper modelMapper,TeamRepository teamRepository,
                            TournamentRepository tournamentRepository){
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public Match addMatch(MatchDto matchDto) {
        Match match = new Match(); // Создаем новый объект Match для каждого вызова

        // Устанавливаем данные турнира
        Tournament tournament = tournamentRepository.findById(matchDto.getTournamentId());
        if (tournament != null) {
            match.setTournament(tournament);
        } else {
            throw new EntityNotFoundException("Tournament not found");
        }

        // Обрабатываем команды
        Team team1 = teamRepository.findByTeamName(matchDto.getTeam1Name());
        Team team2 = teamRepository.findByTeamName(matchDto.getTeam2Name());
        Team teamWinner = teamRepository.findByTeamName(matchDto.getWinnerTeam());

        if (team1 == null) {
            throw new EntityNotFoundException("Team 1 not found");
        }
        if (team2 == null) {
            throw new EntityNotFoundException("Team 2 not found");
        }
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setMatchDate(matchDto.getMatchDate());
        match.setMatchTime(matchDto.getMatchTime());
        match.setWinnerTeam(teamWinner);
        match.setMatchDuration(matchDto.getMatchDuration());
        match.setMatchType(matchDto.getMatchType());
        match = matchRepository.save(match); // Сохраняем новый объект
        System.out.println("Match saved with ID: " + match.getId());
        return match;
    }



    @Transactional
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Map<String, Double> calculateWinRateByMatchDuration(String teamName) {
        // Находим команду по имени
        Team team = teamRepository.findByTeamName(teamName);
        if (team == null) {
            throw new EntityNotFoundException("Team not found");
        }

        // Получаем все матчи
        List<Match> matches = matchRepository.findAll();

        // Фильтруем матчи, в которых участвует команда
        List<Match> teamMatches = matches.stream()
                .filter(match -> match.getTeam1().getId().equals(team.getId()) || match.getTeam2().getId().equals(team.getId()))
                .collect(Collectors.toList());

        // Считаем количество побед в матчах до 60 минут
        long shortMatches = teamMatches.stream()
                .filter(match -> match.getMatchDuration() <= 60)
                .count();

        long shortWins = teamMatches.stream()
                .filter(match -> match.getMatchDuration() <= 60 && match.getWinnerTeam() != null && match.getWinnerTeam().getId().equals(team.getId()))
                .count();

        // Считаем количество побед в матчах более 60 минут
        long longMatches = teamMatches.stream()
                .filter(match -> match.getMatchDuration() > 60)
                .count();

        long longWins = teamMatches.stream()
                .filter(match -> match.getMatchDuration() > 60 && match.getWinnerTeam() != null && match.getWinnerTeam().getId().equals(team.getId()))
                .count();

        // Рассчитываем винрейт
        double shortWinRate = shortMatches > 0 ? ((double) shortWins / shortMatches) * 100 : 0;
        double longWinRate = longMatches > 0 ? ((double) longWins / longMatches) * 100 : 0;

        // Возвращаем результат в виде карты
        Map<String, Double> winRates = new HashMap<>();
        winRates.put("Win rate for matches <= 60 minutes", shortWinRate);
        winRates.put("Win rate for matches > 60 minutes", longWinRate);
        return winRates;
    }

}
