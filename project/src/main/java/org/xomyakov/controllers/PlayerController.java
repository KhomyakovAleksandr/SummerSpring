package org.xomyakov.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xomyakov.dto.PlayerDto;
import org.xomyakov.models.Player;
import org.xomyakov.services.PlayerServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerServiceImpl playerService;


    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDto playerDto) {
        Player player = playerService.addPlayer(playerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }
}

