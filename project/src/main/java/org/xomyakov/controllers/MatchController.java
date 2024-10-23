package org.xomyakov.controllers;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xomyakov.dto.MatchDto;
import org.xomyakov.models.Match;
import org.xomyakov.services.MatchServiceImpl;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchServiceImpl matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/add")
    public ResponseEntity<Match> addMatch(@RequestBody MatchDto matchDto) {
        Match match = matchService.addMatch(matchDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/winrate")
    public ResponseEntity<Map<String, Double>> getWinRateByMatchDuration(@RequestParam String teamName) {
        try {
            Map<String, Double> winRates = matchService.calculateWinRateByMatchDuration(teamName);
            return new ResponseEntity<>(winRates, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}