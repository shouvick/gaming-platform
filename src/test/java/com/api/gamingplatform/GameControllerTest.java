package com.api.gamingplatform;


import com.api.gamingplatform.entity.Client;
import com.api.gamingplatform.entity.Game;
import com.api.gamingplatform.entity.PriceType;
import com.api.gamingplatform.repository.ClientRepository;
import com.api.gamingplatform.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTest {
    @MockBean
    GameRepository gameRepository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testRegisterGame() {
        Game game = new Game();
        game.setClientId((long) 1);
        game.setName("Test");
        game.setMaxConcurrentUsers((long) 400);
        game.setPerMinuteCost(PriceType.Five);

        Mockito.when(gameRepository.save(game)).thenReturn(Mono.just(game));

        webClient.post()
                .uri("/api/registerGame")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(game))
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(gameRepository, times(1)).save(game);
    }

    @Test
    void testCurrentActiveSessions() {
        webClient.get()
                .uri("/api/currentActiveSessions")
                .exchange()
                .expectStatus().isOk();
    }
}
