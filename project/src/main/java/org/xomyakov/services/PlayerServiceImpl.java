package org.xomyakov.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xomyakov.dto.PlayerDto;
import org.xomyakov.models.Player;
import org.xomyakov.models.Team;
import org.xomyakov.repositories.impl.PlayerRepositoryimpl;
import org.xomyakov.repositories.impl.TeamRepositoryimpl;

import java.util.List;

@Service
public class PlayerServiceImpl {

    private final PlayerRepositoryimpl playerRepository;
    private final TeamRepositoryimpl teamRepository; // Добавляем репозиторий команды
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepositoryimpl playerRepository, TeamRepositoryimpl teamRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Player addPlayer(PlayerDto playerDto) {
        // Мапим только поля игрока
        Player player = modelMapper.map(playerDto, Player.class);

        // Находим команду по названию
        Team team = teamRepository.findByTeamName(playerDto.getTeamName());
        if (team == null) {
            throw new RuntimeException("Team not found: " + playerDto.getTeamName());
        }

        // Устанавливаем команду для игрока
        player.setTeam(team);

        // Сохраняем игрока
        playerRepository.save(player);
        return player;
    }

    @Transactional
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}


