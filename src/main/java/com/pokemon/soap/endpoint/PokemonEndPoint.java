package com.pokemon.soap.endpoint;

import com.pokemon.external.PokeApiClient;
import com.pokemon.pkm.GetPokemonRequest;
import com.pokemon.pkm.GetPokemonResponse;
import com.pokemon.pkm.Pokemon;
import com.pokemon.soap.config.PokemonProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;


@Endpoint
public class PokemonEndPoint {
    private static Logger logger = LoggerFactory.getLogger(PokemonEndPoint.class);

    private final PokeApiClient pokeApiClient;

    public PokemonEndPoint(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemonByName(@RequestPayload GetPokemonRequest request, SoapHeader soapHeader) {
        logger.info("getPokemon request received");
        String name = request.getName();
        GetPokemonResponse response = new GetPokemonResponse();
        ResponseEntity<Pokemon> abilitiesByName = pokeApiClient.getAbilitiesByName(name);

        response.setPokemon(abilitiesByName.getBody());
        return response;
    }


}
