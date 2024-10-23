package org.xomyakov.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xomyakov.dto.TeamDto;
import org.xomyakov.models.Team;
import org.xomyakov.models.Tournament;
import org.xomyakov.repositories.impl.TeamRepositoryimpl;
import org.xomyakov.repositories.TournamentRepository; // Импортируйте ваш репозиторий турниров
import java.util.List;

@Service
public class TeamServiceImpl {

    private final TeamRepositoryimpl teamRepository;
    private final TournamentRepository tournamentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepositoryimpl teamRepository, TournamentRepository tournamentRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Team addTeam(TeamDto teamDto) {
        // Мапим только поля команды
        Team team = modelMapper.map(teamDto, Team.class);

        // Находим турнир по ID
        Tournament tournament = tournamentRepository.findById(teamDto.getTournamentId());
        if (tournament == null) {
            throw new RuntimeException("Tournament not found: " + teamDto.getTournamentId());
        }

        // Устанавливаем турнир для команды
        team.setTournament(tournament);

        // Сохраняем команду
        teamRepository.save(team);
        return team;
    }

    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}

