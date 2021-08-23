package com.api.gamingplatform.controller;

import com.api.gamingplatform.entity.Client;
import com.api.gamingplatform.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/onBoard")
    public Mono<Client> OnBoard() {
        return clientService.save();
    }
}
