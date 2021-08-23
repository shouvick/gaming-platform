package com.api.gamingplatform.service.impl;

import com.api.gamingplatform.entity.Client;
import com.api.gamingplatform.repository.ClientRepository;
import com.api.gamingplatform.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public Mono<Client> save() {
        try {
            Client client = new  Client();
            Mono<Client> client1 = clientRepository.save(client);
                LOGGER.info("Client Added Called");
                return client1;
        } catch (Exception ex) {
            LOGGER.info("Error for client Added" + ex);
        }
        return null;
    }
}
