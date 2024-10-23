package org.xomyakov.repositories;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Hero;
import org.xomyakov.models.Player;
import java.util.List;

@Repository
public interface PlayerRepository {
    Player save(Player player);
    Player findById(Long id);
    List<Player> findAll();

}
