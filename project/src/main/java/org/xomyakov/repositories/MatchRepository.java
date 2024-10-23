package org.xomyakov.repositories;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Match;
import java.util.List;

@Repository
public interface MatchRepository {
    Match save(Match match);
    Match findById(Long id);
    List<Match> findAll();
    List<Match> findByTeam1_IdOrTeam2_Id(Long team1Id, Long team2Id);
}
