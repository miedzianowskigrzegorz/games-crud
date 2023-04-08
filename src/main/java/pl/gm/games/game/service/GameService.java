package pl.gm.games.game.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.gm.games.game.dto.GameDTO;
import pl.gm.games.game.exception.GameNotFoundException;
import pl.gm.games.game.model.Game;
import pl.gm.games.game.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameService(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    public List<GameDTO> getGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream()
                .map(game -> modelMapper.map(game, GameDTO.class))
                .collect(Collectors.toList());
    }

    public GameDTO getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        return modelMapper.map(game, GameDTO.class);
    }

    public GameDTO createGame(GameDTO gameDTO) {
        Game game = modelMapper.map(gameDTO, Game.class);
        Game savedGame = gameRepository.save(game);
        return modelMapper.map(savedGame, GameDTO.class);
    }

    public GameDTO updateGame(Long id, GameDTO updatedGameDTO) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        modelMapper.map(updatedGameDTO, game);
        Game savedGame = gameRepository.save(game);
        return modelMapper.map(savedGame, GameDTO.class);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
