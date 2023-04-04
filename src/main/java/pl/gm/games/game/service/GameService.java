package pl.gm.games.game.service;

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
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        return gameRepository
                .findById(id)
                .map((game) -> {
                    game.setName(updatedGame.getName());
                    game.setGenre(updatedGame.getGenre());
                    game.setDescription(updatedGame.getDescription());
                    game.setReleaseDate(updatedGame.getReleaseDate());
                    return gameRepository.save(game);
                })
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
