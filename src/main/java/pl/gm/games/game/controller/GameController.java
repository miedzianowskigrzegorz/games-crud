package pl.gm.games.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.gm.games.game.model.Game;
import pl.gm.games.game.service.GameService;

import java.util.List;

@RestController
@RequestMapping({"/games"})
public class GameController {
    @Autowired
    private GameService gameService;

    public GameController() {
    }

    @GetMapping({"/"})
    public List<Game> getGames() {
        return this.gameService.getGames();
    }

    @GetMapping({"/{id}"})
    public Game getGameById(@PathVariable Long id) {
        return this.gameService.getGameById(id);
    }

    @PostMapping({"/"})
    public Game createGame(@RequestBody Game game) {
        return this.gameService.createGame(game);
    }

    @PutMapping({"/{id}"})
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        return this.gameService.updateGame(id, game);
    }

    @DeleteMapping({"/{id}"})
    public void deleteGame(@PathVariable Long id) {
        this.gameService.deleteGame(id);
    }
}
