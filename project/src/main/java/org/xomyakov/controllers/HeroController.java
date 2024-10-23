package org.xomyakov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xomyakov.dto.HeroDto;
import org.xomyakov.models.Hero;
import org.xomyakov.services.HeroServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroServiceImpl heroService;

    @Autowired
    public HeroController(HeroServiceImpl heroService) {
        this.heroService = heroService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<Hero>> addHeroes(@RequestBody List<HeroDto> heroDtos) {
        List<Hero> addedHeroes = new ArrayList<>();
        for (HeroDto heroDto : heroDtos) {
            addedHeroes.add(heroService.addHero(heroDto)); // Используйте метод добавления одного героя
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedHeroes);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hero>> getAllHeroes() {
        List<Hero> heroes = heroService.getAllHeroes();
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/best-for-final")
    public ResponseEntity<List<HeroDto>> getBestHeroesForFinal(@RequestParam List<Long> playerIds) {
        List<HeroDto> bestHeroes = heroService.selectBestHeroesForFinal(playerIds);
        return new ResponseEntity<>(bestHeroes, HttpStatus.OK);
    }
}
