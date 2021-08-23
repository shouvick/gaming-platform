package com.api.gamingplatform.service;

import com.api.gamingplatform.dto.GamePlayer.GamePlayerBill;
import com.api.gamingplatform.dto.GamePlayer.GamePlayerRequest;
import com.api.gamingplatform.entity.GamePlayer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface GamePlayerService {

    Mono<GamePlayer> saveNewGame(GamePlayerRequest gamePlayerRequest);

    Mono<GamePlayer> endNewGame(String id);

    Flux<GamePlayer> currentActiveSessions();

    Flux<GamePlayerBill> generateBillForPlayer(Long playerID);
}
