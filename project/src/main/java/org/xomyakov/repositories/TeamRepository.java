package org.xomyakov.repositories;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Team;

import java.util.List;

@Repository
public interface TeamRepository {
    Team save(Team team);
    Team findById(Long id);
    List<Team> findAll();
    Team findByTeamName(String teamName);
}
