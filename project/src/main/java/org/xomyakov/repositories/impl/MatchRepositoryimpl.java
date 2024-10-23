package org.xomyakov.repositories.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Match;
import org.xomyakov.repositories.MatchRepository;
import java.util.List;

@Repository
public class MatchRepositoryimpl implements MatchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Match save(Match match) {
        entityManager.merge(match);
        return match;
    }

    @Override
    public Match findById(Long id) {
        return entityManager.find(Match.class, id);
    }

    @Override
    public List<Match> findAll() {
        return entityManager.createQuery("SELECT m FROM Match m", Match.class).getResultList();
    }

    @Override
    public List<Match> findByTeam1_IdOrTeam2_Id(Long team1Id, Long team2Id) {
        // Создаем JPQL-запрос
        String jpql = "SELECT m FROM Match m WHERE m.team1.id = :team1Id OR m.team2.id = :team2Id";

        // Создаем запрос через EntityManager
        TypedQuery<Match> query = entityManager.createQuery(jpql, Match.class);
        query.setParameter("team1Id", team1Id);
        query.setParameter("team2Id", team2Id);

        // Выполняем запрос и возвращаем результат
        return query.getResultList();
    }
}
