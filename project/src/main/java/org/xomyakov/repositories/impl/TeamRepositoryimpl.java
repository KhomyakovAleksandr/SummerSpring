package org.xomyakov.repositories.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Team;
import org.xomyakov.repositories.TeamRepository;
import java.util.List;

@Repository
public class TeamRepositoryimpl  implements TeamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Team save(Team team) {
        entityManager.persist(team);
        return team;
    }

    @Override
    public Team findById(Long id) {
        return entityManager.find(Team.class, id);
    }

    @Override
    public List<Team> findAll() {
        return entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }

    @Override
    @Transactional
    public Team findByTeamName(String teamName) {
        try {
            return entityManager.createQuery("SELECT t FROM Team t WHERE t.teamName = :teamName", Team.class)
                    .setParameter("teamName", teamName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Если команда не найдена
        }
    }
}
