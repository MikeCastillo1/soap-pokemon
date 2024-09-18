package com.pokemon.external;

import com.pokemon.pkm.Pokemon;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Service
public class PokeApiClient {
    private static final Logger logger = LoggerFactory.getLogger(PokeApiClient.class);

    @Value("${pokeapi.url}")
    private String pokeApi;
    @Value("${pokeapi.connect-timeout}")
    private Long connectionTimeout;
    @Value("${pokeapi.read-timeout}")
    private Long readTimeout;

    private RestClient restClient;

    @PostConstruct
    public void postConstruct(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(Duration.ofSeconds(connectionTimeout));
        simpleClientHttpRequestFactory.setReadTimeout(Duration.ofSeconds(readTimeout));
        restClient = RestClient.builder()
                .requestFactory(simpleClientHttpRequestFactory)
                .baseUrl(pokeApi)
                .build();

    }

    public ResponseEntity<Pokemon> getPokemon(String pokemonName){
        logger.info("CALLING POKE API");
        ResponseEntity<Pokemon> response =
                restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("pokemon")
                        .pathSegment(pokemonName).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {throw new PokemonNotFoundException(pokemonName);})
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {throw new PokemonServerError();})
                .toEntity(Pokemon.class);
       return response;
    }
}
