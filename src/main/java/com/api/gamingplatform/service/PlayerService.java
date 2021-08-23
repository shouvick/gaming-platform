package com.api.gamingplatform.service;

import com.api.gamingplatform.dto.Player.PlayerRequest;
import com.api.gamingplatform.entity.Player;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface PlayerService {
    Mono<Player> save(PlayerRequest playerRequest);
    Mono<Player> delete(Long id);
    Mono<Player> getById(Long id);
    Flux<Player> getAll();
}
