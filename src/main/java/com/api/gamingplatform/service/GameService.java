package com.api.gamingplatform.service;

import com.api.gamingplatform.dto.Game.GameRequest;
import com.api.gamingplatform.entity.Game;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface GameService {
    Mono<Game> save(GameRequest game);

    Mono<Void> delete(Long gameID);
}
