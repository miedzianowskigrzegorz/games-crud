package pl.gm.games.game.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import pl.gm.games.game.dto.GameDTO;
import pl.gm.games.game.exception.GameNotFoundException;
import pl.gm.games.game.model.Game;
import pl.gm.games.game.repository.GameRepository;

@SpringBootTest
class GameServiceTest {

    private GameService gameService;
    private GameRepository gameRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        gameRepository = mock(GameRepository.class);
        modelMapper = new ModelMapper();
        gameService = new GameService(gameRepository, modelMapper);
    }

    @Test
    public void testGetGames() {
        List<GameDTO> gameDTOs = new ArrayList<>();
        GameDTO gameDTO1 = new GameDTO(1L, "a", "a", "a", LocalDate.now());
        GameDTO gameDTO2 = new GameDTO(2L, "b", "b", "a", LocalDate.now());
        gameDTOs.add(gameDTO1);
        gameDTOs.add(gameDTO2);

        when(gameRepository.findAll()).thenReturn(gameDTOs.stream().map(game -> modelMapper.map(game, Game.class)).collect(Collectors.toList()));

        assertEquals(gameDTOs, gameService.getGames());
    }

    @Test
    public void testGetGameById() {
        Game game = new Game(1L, "a", "a", "a", LocalDate.now());
        GameDTO expectedGameDTO = new GameDTO(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameDTO actualGameDTO = gameService.getGameById(1L);

        assertEquals(expectedGameDTO, actualGameDTO);
    }

    @Test
    public void testGetGameByIdNotFound() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(1L));
    }

    @Test
    public void testCreateGame() {
        GameDTO gameDTO = new GameDTO(1L, "a", "a", "a", LocalDate.now());
        Game game = new Game(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.save(game)).thenReturn(game);

        assertEquals(gameDTO, gameService.createGame(gameDTO));
    }

    @Test
    public void testUpdateGame() {
        Game existingGame = new Game(1L, "a", "a", "a", LocalDate.now());
        GameDTO existingGameDTO = new GameDTO(1L, "a", "a", "a", LocalDate.now());
        GameDTO updatedGameDTO = new GameDTO(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(existingGame)).thenReturn(existingGame);

        assertEquals(updatedGameDTO, gameService.updateGame(1L, updatedGameDTO));
    }

    @Test
    public void testUpdateGameNotFound() {
        Game existingGame = new Game(1L, "a", "a", "a", LocalDate.now());
        GameDTO updatedGame = new GameDTO(1L, "a", "a", "a", LocalDate.now());

        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(existingGame)).thenReturn(existingGame);

        assertEquals(updatedGame, gameService.updateGame(1L, updatedGame));
    }

    @Test
    public void testDeleteGame() {
        gameService.deleteGame(1L);

        verify(gameRepository, times(1)).deleteById(1L);
    }
}