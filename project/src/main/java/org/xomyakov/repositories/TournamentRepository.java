package org.xomyakov.repositories;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Hero;
import org.xomyakov.models.Tournament;

import java.util.List;

@Repository
public interface TournamentRepository {
    Tournament save(Tournament tournament);
    Tournament findById(Long id);
    List<Tournament> findAll();
}
