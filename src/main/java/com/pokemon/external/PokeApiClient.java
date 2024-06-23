package com.pokemon.external;

import com.pokemon.pkm.Pokemon;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PokeApiClient {
    private static final Logger logger = LoggerFactory.getLogger(PokeApiClient.class);

    @Value("${pokeapi.url}")
    private String pokeApi;

    private RestClient restClient;

    @PostConstruct
    public void postConstruct(){
        restClient = RestClient.builder()
                .baseUrl(pokeApi).build();
    }

    public ResponseEntity<Pokemon> getAbilitiesByName(String pokemonName){
        logger.info("CALLING POKE API");
        ResponseEntity<Pokemon> response =
                restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("pokemon")
                        .pathSegment(pokemonName).build())
                .retrieve()
                .toEntity(Pokemon.class);
       return response;
    }
}
