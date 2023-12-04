package com.poli.integracioncontinua.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.models.DeveloperInfo;
import com.models.GameInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RawgServiceImpl implements RawgService {

    private final WebClient webClient;
    private final String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(RawgServiceImpl.class);

    public RawgServiceImpl(@Value("${rawg.api.baseurl}") final String baseUrl,
                       @Value("${rawg.api.key}") final String apiKey) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    @Override
    public Mono<DeveloperInfo> getDeveloperInfo(final String developerName) {
        return fetchDeveloperData(developerName)
                .doOnNext(ids -> logger.info("Fetched developer ids: {} ", ids.toString()))
                .flatMapMany(ids -> Flux.fromIterable(ids))
                .flatMap(this::fetchGameData)
                .doOnNext(game -> logger.info("Fetched game data: {}", game.getName()))
                .collectList()
                .map(games -> new DeveloperInfo(developerName, games));
    }

    private Mono<GameInfo> fetchGameData(final Integer gameId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/games/{id}")
                                             .queryParam("key", apiKey)
                                             .build(gameId))
                .retrieve()
                .bodyToMono(GameInfo.class)
                .onErrorResume(e -> {
                    logger.error("Error fetching game data for ID {}: {}", gameId, e.getMessage(), e);
                    return Mono.just(new GameInfo());
                });
    }

    private Mono<List<Integer>> fetchDeveloperData(final String developerName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/developers")
                                             .queryParam("key", apiKey)
                                             .queryParam("search", developerName)
                                             .build())
                .retrieve()
                .bodyToMono(RawgDeveloperResponse.class)
                .map(response -> toDeveloperInfo(response))
                .onErrorResume(e -> {
                    logger.error("Error fetching developer data for name {}: {}", developerName, e.getMessage(), e); 
                    return Mono.just(List.of());
                });
    }

    private List<Integer> toDeveloperInfo(final RawgDeveloperResponse response) {
        return response.getResults().stream()
                                        .flatMap(r -> r.getTopGames().stream())
                                        .collect(Collectors.toList());
    }
}

class RawgDeveloperResponse {
    private List<RawgDeveloperResult> results;

    public List<RawgDeveloperResult> getResults() {
        return results;
    }

    public void setResults(final List<RawgDeveloperResult> results) {
        this.results = results;
    }
}

class RawgDeveloperResult {
    private String name;
    
    @JsonProperty("top_games")
    private List<Integer> topGames;

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public List<Integer> getTopGames() {
        return topGames;
    }
    public void setTopGames(final List<Integer> topGames) {
        this.topGames = topGames;
    }
}

