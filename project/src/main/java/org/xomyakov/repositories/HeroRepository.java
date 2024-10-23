package org.xomyakov.repositories;
import org.springframework.stereotype.Repository;
import org.xomyakov.models.Hero;

import java.util.List;

@Repository
public interface HeroRepository {
    Hero save(Hero hero);
    Hero findById(Long id);
    List<Hero> findAll();
}
