package org.xomyakov.repositories.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Tournament;
import org.xomyakov.repositories.TournamentRepository;

import java.util.List;


@Repository
public class TournamentRepositoryimpl  implements TournamentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Tournament save(Tournament tournament) {
        entityManager.persist(tournament);
        return tournament;
    }

    @Override
    public Tournament findById(Long id) {
        return entityManager.find(Tournament.class, id);
    }

    @Override
    public List<Tournament> findAll() {
        return entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList();
    }
}
