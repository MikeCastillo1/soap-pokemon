package com.pokemon.soap.endpoint;

import com.pokemon.external.PokeApiClient;
import com.pokemon.pkm.*;
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

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonByNameRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemonByNameRequest(@RequestPayload GetPokemonByNameRequest request, SoapHeader soapHeader) {
        logger.info("getPokemonByNameRequest");

        String name = request.getName();
        GetPokemonResponse response = new GetPokemonResponse();
        ResponseEntity<Pokemon> pokemonResponse = pokeApiClient.getPokemon(name);
        response.setPokemon(pokemonResponse.getBody());

        return response;
    }

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonAbilitiesByNameRequest")
    @ResponsePayload
    public GetPokemonAbilitiesResponse getPokemonAbilitiesByName(@RequestPayload GetPokemonAbilitiesByNameRequest request, SoapHeader soapHeader) {
        logger.info("getPokemonAbilitiesByNameRequest");

        String name = request.getName();
        GetPokemonAbilitiesResponse response = new GetPokemonAbilitiesResponse();
        ResponseEntity<Pokemon> pokemonResponse = pokeApiClient.getPokemon(name);
        response.getAbilities().addAll(pokemonResponse.getBody().getAbilities());

        return response;
    }

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonBaseExperienceByNameRequest")
    @ResponsePayload
    public GetPokemonBaseExperienceResponse getPokemonBaseExperienceByNameRequest(@RequestPayload GetPokemonBaseExperienceByNameRequest request, SoapHeader soapHeader) {
        logger.info("getPokemonBaseExperienceByNameRequest");

        String name = request.getName();
        GetPokemonBaseExperienceResponse response = new GetPokemonBaseExperienceResponse();
        ResponseEntity<Pokemon> responsePokemon = pokeApiClient.getPokemon(name);
        response.setBaseExperience(responsePokemon.getBody().getBaseExperience());

        return response;
    }

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonHeldItemsByNameRequest")
    @ResponsePayload
    public GetPokemonHeldItemsResponse getPokemonHeldItemsByNameRequest(@RequestPayload GetPokemonHeldItemsByNameRequest request, SoapHeader soapHeader) {
        logger.info("getPokemonHeldItemsByNameRequest");

        String name = request.getName();
        GetPokemonHeldItemsResponse response = new GetPokemonHeldItemsResponse();
        ResponseEntity<Pokemon> responsePokemon = pokeApiClient.getPokemon(name);
        response.getHeldItems().addAll(responsePokemon.getBody().getHeldItems());

        return response;
    }

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonIdByNameRequest")
    @ResponsePayload
    public GetPokemonIdResponse getPokemonIdByNameRequest(@RequestPayload GetPokemonIdByNameRequest request, SoapHeader soapHeader) {
        logger.info("getPokemonIdByNameRequest");

        String name = request.getName();
        GetPokemonIdResponse response = new GetPokemonIdResponse();
        ResponseEntity<Pokemon> responsePokemon = pokeApiClient.getPokemon(name);
        response.setId(responsePokemon.getBody().getId());

        return response;
    }

    @PayloadRoot(namespace = PokemonProps.NAMESPACE, localPart = "getPokemonLocationAreaEncountersByNameRequest")
    @ResponsePayload
    public GetPokemonLocationAreaEncountersResponse getPokemonLocationAreaEncountersByNameRequest(@RequestPayload GetPokemonLocationAreaEncountersByNameRequest request, SoapHeader soapHeader) {
        logger.info("getPokemonLocationAreaEncountersByNameRequest");

        String name = request.getName();
        GetPokemonLocationAreaEncountersResponse response = new GetPokemonLocationAreaEncountersResponse();
        ResponseEntity<Pokemon> responsePokemon = pokeApiClient.getPokemon(name);
        response.setLocationAreaEncounters(responsePokemon.getBody().getLocationAreaEncounters());

        return response;
    }

}
