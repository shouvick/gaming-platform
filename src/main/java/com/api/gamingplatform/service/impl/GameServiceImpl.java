package com.api.gamingplatform.service.impl;

import com.api.gamingplatform.common.exceptions.NotFoundErrorValidation;
import com.api.gamingplatform.dto.Game.GameRequest;
import com.api.gamingplatform.entity.Game;
import com.api.gamingplatform.entity.PriceType;
import com.api.gamingplatform.repository.ClientRepository;
import com.api.gamingplatform.repository.GameRepository;
import com.api.gamingplatform.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ClientRepository clientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Override
    public Mono<Game> save(GameRequest gameRequest) {
        Optional<PriceType> priceType = PriceType.getFromText(gameRequest.getPerMinuteCost());
        try {
            LOGGER.info(gameRequest.getGameInfo("New Game Register Called",gameRequest));
            return clientRepository.findById(gameRequest.getClientId()).switchIfEmpty(Mono.error(new NotFoundErrorValidation("Client ID not found"))).flatMap(client -> {
                Game game = new Game();
                game.setName(gameRequest.getName());
                game.setMaxConcurrentUsers(gameRequest.getMaxConcurrentUsers());
                game.setPerMinuteCost(priceType.get());
                game.setClientId(client.getId());
                return gameRepository.save(game);
            });
        } catch (Exception ex) {
            LOGGER.error("Error on Game Register" + ex);
        }
        return null;
    }

    @Override
    public Mono<Void> delete(Long gameID) {
        try {
            LOGGER.info("New Game Un Register Called for gameID " + gameID );
            return gameRepository.findById(gameID).switchIfEmpty(Mono.error(new NotFoundErrorValidation("Game ID not found"))).flatMap(game -> {
                return gameRepository.delete(game);
            });
        } catch (Exception ex) {
            LOGGER.error("Error on Game Un Register" + ex);
        }
        return null;
    }
}
