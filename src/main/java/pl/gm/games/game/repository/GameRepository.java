package pl.gm.games.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gm.games.game.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
