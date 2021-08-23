package com.api.gamingplatform.repository;

import com.api.gamingplatform.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client,Long> {
}
