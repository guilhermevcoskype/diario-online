package com.gui.diarioOnline.business;

import com.gui.diarioOnline.controller.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class IGDBService {

    private final WebClient webClient;

    public IGDBService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<DetailedGameResponse> consumeGamesList(String game) {
        String queryGameList = "search \""+game+"\"; fields name, summary, cover;";
        return webClient.post()
                .uri("/games")
                .bodyValue(queryGameList)
                .retrieve()
                .bodyToFlux(GameResponse.class)
                // Operador chave: Para cada 'game' no Flux, faça outra chamada (flatMap)
                .flatMap(gameResponse -> {

                    // Se o jogo não tiver capa, retorne um DTO incompleto imediatamente (opcional)
                    if (gameResponse.cover() == null) {
                        return Mono.just(new DetailedGameResponse(
                                gameResponse.id(),
                                gameResponse.name(),
                                gameResponse.summary(),
                                null,
                                "N/A"
                        ));
                    }

                    // 1. Chame o método que retorna o Mono<Cover>
                    Mono<CoverResponse> coverMono = consumeCoverGame(gameResponse);

                    // 2. Use 'zipWith' para combinar o gameResponse original e o coverMono
                    return coverMono.map(cover ->
                            // 3. Mapeie para o novo DTO DetailedGameResponse
                            new DetailedGameResponse(
                                    gameResponse.id(),
                                    gameResponse.name(),
                                    gameResponse.summary(),
                                    gameResponse.cover(),
                                    cover.url() // Assumindo que Cover tem um método url()
                            )
                    );
                }).collectList().block();
    }

    public Mono<CoverResponse> consumeCoverGame(GameResponse game) {
        String queryCoverUrl = "fields url; where id = "+game.cover()+";";
        return webClient.post()
                .uri("/covers") // The endpoint returning a Mono
                .bodyValue(queryCoverUrl)
                .retrieve()
                .bodyToFlux(CoverResponse.class).next(); // Specify the type of the single element
    }

}
