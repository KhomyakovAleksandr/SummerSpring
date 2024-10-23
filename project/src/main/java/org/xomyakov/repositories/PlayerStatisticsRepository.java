package org.xomyakov.repositories;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Hero;
import org.xomyakov.models.PlayerStatistics;
import java.util.List;

@Repository
public interface PlayerStatisticsRepository {
    PlayerStatistics save(PlayerStatistics playerStatistics);
    PlayerStatistics findById(Long id);
    List<PlayerStatistics> findAll();
    public List<PlayerStatistics> findByHeroId(Long heroId) ;
    List<PlayerStatistics> findByTeamIdAndPlayerId(Long teamId, Long playerId);
    List<PlayerStatistics> findByPlayerIdAndHeroId(Long playerId, Long heroId);
}
