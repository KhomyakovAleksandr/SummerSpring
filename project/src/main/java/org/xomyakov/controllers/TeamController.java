package org.xomyakov.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xomyakov.dto.TeamDto;
import org.xomyakov.models.Team;
import org.xomyakov.services.TeamServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamServiceImpl teamService;


    @Autowired
    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody TeamDto teamDto) {
        Team team = teamService.addTeam(teamDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }
}
