package com.api.gamingplatform.service;

import com.api.gamingplatform.entity.Client;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ClientService {
    Mono<Client> save();
}
