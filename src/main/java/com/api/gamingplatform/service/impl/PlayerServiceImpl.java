package com.api.gamingplatform.service.impl;


import com.api.gamingplatform.common.exceptions.NotFoundErrorValidation;
import com.api.gamingplatform.dto.Player.PlayerRequest;
import com.api.gamingplatform.entity.Player;
import com.api.gamingplatform.repository.PlayerRepository;
import com.api.gamingplatform.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Override
    public Mono<Player> save(PlayerRequest playerRequest) {
        Player player = new Player();
        player.setFirstName(playerRequest.getFirstName());
        player.setLastName(playerRequest.getLastName());
        player.setAge(playerRequest.getAge());
        player.setNationality(playerRequest.getNationality());
        Mono<Player> playerMono = null;
        try {
            playerMono = playerRepository.save(player);
            LOGGER.info(playerRequest.getPlayerInfo("AddPlayer called for", playerRequest));
            return playerMono;
        } catch (Exception ex) {
            LOGGER.error("Error Player Add" + ex.getMessage());
        }
        return playerMono;
    }

    @Override
    public Mono<Player> delete(Long id) {
        try {
            LOGGER.info("Delete Player ID Called " + id );
            return playerRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundErrorValidation("ID Not Found"))).flatMap(player -> {
                playerRepository.deleteById(player.getId()).subscribe();
                return Mono.just(player);
            });
        } catch (Exception ex) {
            LOGGER.error("Error Delete Player" + ex.getMessage());
        }
        return null;
    }

    @Override
    public Mono<Player> getById(Long id) {
        try {
            LOGGER.info("Get Player ID Called " + id );
            return playerRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundErrorValidation("ID Not Found")));
        } catch (Exception ex) {
            LOGGER.error("Error Get Player" + ex.getMessage());
        }
        return null;
    }

    @Override
    public Flux<Player> getAll() {
        Flux<Player> playerFlux = null;
        try {
            playerFlux = playerRepository.findAll();
            LOGGER.info("Get All Player Called");
            return playerFlux;
        } catch (Exception ex) {
            LOGGER.error("Error Get All Player" + ex.getMessage());
        }
        return playerFlux;
    }
}
