package pl.gm.games.game.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.gm.games.game.exception.GameNotFoundException;
import pl.gm.games.game.model.Game;
import pl.gm.games.game.repository.GameRepository;

@SpringBootTest
class GameServiceTest {

    private GameService gameService;
    private GameRepository gameRepository;

    @BeforeEach
    public void setup() {
        gameRepository = mock(GameRepository.class);
        gameService = new GameService(gameRepository);
    }

    @Test
    public void testGetGames() {
        List<Game> games = new ArrayList<>();
        Game game1 = new Game(1L, "a", "a", "a", LocalDate.now());
        Game game2 = new Game(2L, "b", "b", "a", LocalDate.now());
        games.add(game1);
        games.add(game2);

        when(gameRepository.findAll()).thenReturn(games);

        assertEquals(games, gameService.getGames());
    }

    @Test
    public void testGetGameById() {
        Game game = new Game(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        assertEquals(game, gameService.getGameById(1L));
    }

    @Test
    public void testGetGameByIdNotFound() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(1L));
    }

    @Test
    public void testCreateGame() {
        Game game = new Game(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.save(game)).thenReturn(game);

        assertEquals(game, gameService.createGame(game));
    }

    @Test
    public void testUpdateGame() {
        Game existingGame = new Game(1L, "a", "a", "a", LocalDate.now());
        Game updatedGame = new Game(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(existingGame)).thenReturn(existingGame);

        assertEquals(updatedGame, gameService.updateGame(1L, updatedGame));
    }

    @Test
    public void testUpdateGameNotFound() {
        Game updatedGame = new Game(1L, "Updated", "a", "a", LocalDate.now());

        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.updateGame(1L, updatedGame));
    }

    @Test
    public void testDeleteGame() {
        gameService.deleteGame(1L);

        // Verify that the deleteById method of the gameRepository was called once with the argument 1L
        verify(gameRepository, times(1)).deleteById(1L);
    }
}