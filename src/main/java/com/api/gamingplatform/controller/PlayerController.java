package com.api.gamingplatform.controller;

import com.api.gamingplatform.dto.Player.PlayerRequest;
import com.api.gamingplatform.entity.Player;
import com.api.gamingplatform.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/addPlayer")
    public Mono<Player> addPlayer(@Valid @RequestBody PlayerRequest playerRequest) {
        return playerService.save(playerRequest);
    }

    @DeleteMapping("/removePlayer/{playerID}")
    public Mono<Player> removePlayer(@PathVariable Long playerID) {
        return playerService.delete(playerID);
    }

    @GetMapping("/getPlayerDetails/{playerID}")
    public Mono<Player> getPlayerDetails(@PathVariable Long playerID) {
        return playerService.getById(playerID);
    }

    @GetMapping("/getListOfPlayers")
    public Flux<Player> getListOfPlayers() {
        return playerService.getAll();
    }


}
