package com.api.gamingplatform;


import com.api.gamingplatform.controller.ClientController;
import com.api.gamingplatform.entity.Client;
import com.api.gamingplatform.repository.ClientRepository;
import com.api.gamingplatform.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @MockBean
    ClientRepository clientRepository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateClient() {
        Client client = new Client();
        Mockito.when(clientRepository.save(client)).thenReturn(Mono.just(client));

        webClient.get()
                .uri("/api/onBoard")
                .exchange()
                .expectStatus().isOk();
    }

}
