package pl.gm.games.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gm.games.game.exception.GameNotFoundException;
import pl.gm.games.game.model.Game;
import pl.gm.games.game.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return this.gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return (Game)this.gameRepository.findById(id).orElseThrow(() -> {
            return new GameNotFoundException(id);
        });
    }

    public Game createGame(Game game) {
        return (Game)this.gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        return (Game)this.gameRepository.findById(id).map((game) -> {
            game.setName(updatedGame.getName());
            game.setGenre(updatedGame.getGenre());
            game.setDescription(updatedGame.getDescription());
            game.setReleaseDate(updatedGame.getReleaseDate());
            return (Game)this.gameRepository.save(game);
        }).orElseThrow(() -> {
            return new GameNotFoundException(id);
        });
    }

    public void deleteGame(Long id) {
        this.gameRepository.deleteById(id);
    }
}
