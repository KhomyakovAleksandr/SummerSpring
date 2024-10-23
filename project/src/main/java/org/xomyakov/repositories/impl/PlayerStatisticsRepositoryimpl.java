package org.xomyakov.repositories.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.PlayerStatistics;
import org.xomyakov.repositories.PlayerStatisticsRepository;
import java.util.List;

@Repository
public class PlayerStatisticsRepositoryimpl implements PlayerStatisticsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public PlayerStatistics save(PlayerStatistics playerStatistics) {
        entityManager.persist(playerStatistics);
        return playerStatistics;
    }

    @Override
    public PlayerStatistics findById(Long id) {
        return entityManager.find(PlayerStatistics.class, id);
    }

    @Override
    public List<PlayerStatistics> findAll() {
        return entityManager.createQuery("SELECT ps FROM PlayerStatistics ps", PlayerStatistics.class).getResultList();
    }

    @Override
    public List<PlayerStatistics> findByTeamIdAndPlayerId(Long teamId, Long playerId) {
        String jpql = "SELECT ps FROM PlayerStatistics ps WHERE ps.team.id = :teamId AND ps.player.id = :playerId";
        return entityManager.createQuery(jpql, PlayerStatistics.class)
                .setParameter("teamId", teamId)
                .setParameter("playerId", playerId)
                .getResultList();
    }

    @Override
    public List<PlayerStatistics> findByPlayerIdAndHeroId(Long playerId, Long heroId) {
        String query = "SELECT ps FROM PlayerStatistics ps WHERE ps.player.id = :playerId AND ps.hero.id = :heroId";
        TypedQuery<PlayerStatistics> typedQuery = entityManager.createQuery(query, PlayerStatistics.class);
        typedQuery.setParameter("playerId", playerId);
        typedQuery.setParameter("heroId", heroId);
        return typedQuery.getResultList();
    }

    @Override
    public List<PlayerStatistics> findByHeroId(Long heroId) {
        return entityManager.createQuery("SELECT ps FROM PlayerStatistics ps WHERE ps.hero.id = :heroId", PlayerStatistics.class)
                .setParameter("heroId", heroId)
                .getResultList();
    }
}
