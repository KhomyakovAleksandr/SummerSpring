package org.xomyakov.repositories.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xomyakov.models.Player;
import org.xomyakov.repositories.PlayerRepository;
import java.util.List;

@Repository
public class PlayerRepositoryimpl implements PlayerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Player save(Player player) {
        entityManager.persist(player);
        return player;
    }

    @Override
    public Player findById(Long id) {
        return entityManager.find(Player.class, id);
    }

    @Override
    public List<Player> findAll() {
        return entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList();
    }
}
