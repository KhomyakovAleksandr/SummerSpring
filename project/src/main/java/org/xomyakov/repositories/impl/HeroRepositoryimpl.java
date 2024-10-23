package org.xomyakov.repositories.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Hero;
import org.xomyakov.repositories.HeroRepository;

import java.util.List;

@Repository
public class HeroRepositoryimpl implements HeroRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Hero save(Hero hero) {
        entityManager.persist(hero);
        return hero;
    }

    @Override
    public Hero findById(Long id) {
        return entityManager.find(Hero.class, id);
    }

    @Override
    public List<Hero> findAll() {
        return entityManager.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
    }

}
