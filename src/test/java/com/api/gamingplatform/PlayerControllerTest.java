package com.api.gamingplatform;




import com.api.gamingplatform.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {
    @MockBean
    PlayerRepository playerRepository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testRemovePlayer() {
        webClient.delete()
                .uri("/api/removePlayer/1")
                .exchange()
                .expectStatus().isOk();
    }
}
