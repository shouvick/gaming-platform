package com.api.gamingplatform.service.impl;

import com.api.gamingplatform.common.exceptions.CommonErrorValidation;
import com.api.gamingplatform.common.exceptions.NotFoundErrorValidation;
import com.api.gamingplatform.dto.GamePlayer.GamePlayerBill;
import com.api.gamingplatform.dto.GamePlayer.GamePlayerRequest;
import com.api.gamingplatform.entity.Game;
import com.api.gamingplatform.entity.GamePlayer;
import com.api.gamingplatform.repository.GamePlayerRepository;
import com.api.gamingplatform.repository.GameRepository;
import com.api.gamingplatform.repository.PlayerRepository;
import com.api.gamingplatform.service.GamePlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class GamePlayerServiceImpl implements GamePlayerService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(GamePlayerServiceImpl.class);

    @Override
    public Mono<GamePlayer> saveNewGame(GamePlayerRequest gamePlayerRequest) {
        try {
            UUID uuid = UUID.randomUUID();
            GamePlayer gamePlayer1 = new GamePlayer();
            gamePlayer1.setGame_id(gamePlayerRequest.getGame_id());
            gamePlayer1.setPlayer_id(gamePlayerRequest.getPlayer_id());
            gamePlayer1.setSession_id(uuid.toString());
            gamePlayer1.setStatus(true);
            LOGGER.info("New Game Session Started Called ");
            return maxConcurrentUserCount(gamePlayerRequest.getGame_id())
                    .then(checkActiveSession(gamePlayerRequest.getPlayer_id(), gamePlayerRequest.getGame_id()))
                    .then(gamePlayerRepository.save(gamePlayer1));
        }
        catch (Exception ex) {
            LOGGER.error("New Game Session Started Called " + ex);
        }
        return null;
    }

    private Mono<GamePlayer> checkActiveSession(Long player_id, Long game_id) {
        Mono<GamePlayer> gamePlayerMono =  gamePlayerRepository.findActiveSessionByPlayerId(player_id, game_id);
        return gamePlayerMono.flatMap(value -> {
            if(!value.getSession_id().isEmpty()) {
               return Mono.error(new CommonErrorValidation("Already Player and User has active session"));
            }
            return Mono.just(value);
        });
    }

    private Mono<Object> maxConcurrentUserCount(Long game_id) {
        Mono<Game> gameMono = gameRepository.findById(game_id);
          return gameMono.flatMap(game -> {
              return gamePlayerRepository.findActiveSessionByGameId(game.getId()).flatMap(value -> {
                  return value.intValue() >= game.getMaxConcurrentUsers()
                                          ?   Mono.error(new CommonErrorValidation("Launching newGameSession where the particular game is already having its maxConcurrentUsers active"))
                                          :   Mono.just(value);
              });
          });
    }


    @Override
    public Mono<GamePlayer> endNewGame(String id) {
        Mono<GamePlayer> game = gamePlayerRepository.findBySessionId(id);
        return game.flatMap(value -> {
            value.setStatus(false);
            value.setSession_end(LocalDateTime.now());
            LOGGER.info("New Game Session End Called Session ID: " + value.getSession_id());
            return Mono.just(value);
        }).switchIfEmpty(Mono.error(new NotFoundErrorValidation("Session ID Not Found"))).flatMap(gamePlayerRepository::save);
    }

    @Override
    public Flux<GamePlayer> currentActiveSessions() {
        Flux<GamePlayer> activeSessions = gamePlayerRepository.findAllActiveSession();
        activeSessions.log().subscribe();
        return activeSessions.flatMap(value -> {
             LOGGER.info("Current Active Session Called Session ID: " + value.getSession_id());
             return Mono.just(value);
        });
    }


    @Override
    public Flux<GamePlayerBill> generateBillForPlayer(Long playerID) {
        Flux<GamePlayerBill> gameplayerList = gamePlayerRepository.findAllSessionByPlayerId(playerID)
                .flatMap(this::apply);
        LOGGER.info("Generate Bill Called For Player ID: " + playerID);
        return gameplayerList;
    }

    private Mono<GamePlayerBill> apply(GamePlayer gamePlayer) {
        return gameRepository.findById(gamePlayer.getGame_id()).map(game -> {
           return GamePlayerBill.from(gamePlayer, new GamePlayerBill(), game);
        });
    }
}
