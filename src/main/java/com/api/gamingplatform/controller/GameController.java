package com.api.gamingplatform.controller;

import com.api.gamingplatform.dto.Game.GameRequest;
import com.api.gamingplatform.dto.GamePlayer.GamePlayerBill;
import com.api.gamingplatform.dto.GamePlayer.GamePlayerRequest;
import com.api.gamingplatform.entity.Game;
import com.api.gamingplatform.entity.GamePlayer;
import com.api.gamingplatform.service.GamePlayerService;
import com.api.gamingplatform.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GamePlayerService gamePlayerService;

    @PostMapping("registerGame")
    public Mono<Game> reqisterGame(@Valid @RequestBody GameRequest gameRequest) {
        return gameService.save(gameRequest);
    }

    @DeleteMapping("unregisterGame/{gameID}")
    public Mono<Void> unreqisterGame(@PathVariable Long gameID) {
        return gameService.delete(gameID);
    }

    @PostMapping("newGameSession")
    public Mono<GamePlayer> newGameSession(@Valid @RequestBody GamePlayerRequest gamePlayerRequest) {
        return gamePlayerService.saveNewGame(gamePlayerRequest);
    }

    @GetMapping("endGameSession/{sessionID}")
    public Mono<GamePlayer> endGameSession(@PathVariable String sessionID) {
        return gamePlayerService.endNewGame(sessionID);
    }

    @GetMapping("currentActiveSessions")
    public Flux<GamePlayer> currentActiveSessions() {
        return gamePlayerService.currentActiveSessions();
    }

    @GetMapping("generateBillForPlayer/{playerID}")
    public Flux<GamePlayerBill> generateBillForPlayer(@PathVariable Long playerID) {
        return gamePlayerService.generateBillForPlayer(playerID);
    }

}
