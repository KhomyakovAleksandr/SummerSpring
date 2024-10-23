package org.xomyakov.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xomyakov.dto.TournamentDto;
import org.xomyakov.models.Tournament;
import org.xomyakov.services.TournamentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentServiceImpl tournamentService;

    @Autowired
    public TournamentController(TournamentServiceImpl tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Tournament> addTournament(@RequestBody TournamentDto tournamentDto) {
        Tournament tournament = tournamentService.addTournament(tournamentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tournament);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }
}
