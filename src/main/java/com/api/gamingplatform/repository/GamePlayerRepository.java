package com.api.gamingplatform.repository;

import com.api.gamingplatform.entity.GamePlayer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GamePlayerRepository extends ReactiveCrudRepository<GamePlayer,Long> {

    @Query("Select * from game_player where session_id = :id")
    Mono<GamePlayer> findBySessionId(String id);

    @Query("Select * from game_player where status = 1")
    Flux<GamePlayer> findAllActiveSession();

    @Query("Select * from game_player gp inner join player p on p.id = gp.player_id where gp.player_id = :playerID and gp.status = 0")
    Flux<GamePlayer> findAllSessionByPlayerId(Long playerID);


    @Query("Select * from game_player where player_id = :player_id and game_id = :game_id and status = 1 limit 1")
    Mono<GamePlayer> findActiveSessionByPlayerId(Long player_id, Long game_id);

    @Query("Select count(*) from game_player where game_id = :game_id and status = 1")
    Mono<Long> findActiveSessionByGameId(Long game_id);
}
