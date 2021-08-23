package com.api.gamingplatform.repository;

import com.api.gamingplatform.entity.Game;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends ReactiveCrudRepository<Game,Long> {
}
