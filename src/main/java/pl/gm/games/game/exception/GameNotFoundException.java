package pl.gm.games.game.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Game not found with id :: " + id);
    }
}
