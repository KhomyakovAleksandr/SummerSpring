package org.xomyakov.services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xomyakov.dto.TournamentDto;
import org.xomyakov.models.Tournament;
import org.xomyakov.repositories.impl.TournamentRepositoryimpl;

import java.util.List;

@Service
public class TournamentServiceImpl {

    private final TournamentRepositoryimpl tournamentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TournamentServiceImpl(TournamentRepositoryimpl tournamentRepository, ModelMapper modelMapper) {
        this.tournamentRepository = tournamentRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public Tournament addTournament(TournamentDto tournamentDto) {
        Tournament tournament = modelMapper.map(tournamentDto, Tournament.class);
        tournamentRepository.save(tournament);
        System.out.println("Tournament saved with ID: " + tournament.getId());
        return tournament;
    }

    @Transactional
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }
}
