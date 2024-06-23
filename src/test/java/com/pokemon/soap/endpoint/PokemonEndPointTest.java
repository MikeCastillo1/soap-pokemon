package com.pokemon.soap.endpoint;

import com.pokemon.external.PokeApiClient;
import com.pokemon.pkm.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@WebServiceServerTest
class PokemonEndPointTest {

    @Autowired
    MockWebServiceClient mockWebServiceClient;
    @MockBean
    PokeApiClient pokeApiClient;

    @Test
    void getPokemonBaseExperienceByNameRequest() {
        Pokemon pokemon = new Pokemon();
        pokemon.setBaseExperience("10");

        when(pokeApiClient.getPokemon("pikachu"))
                .thenReturn(ResponseEntity.ok(pokemon));

        Source requestPayload = new StringSource(
                                "<pk:getPokemonBaseExperienceByNameRequest xmlns:pk='http://pokemon.com/pkm'>" +
                                "<pk:name>pikachu</pk:name>" +
                                "</pk:getPokemonBaseExperienceByNameRequest>");

        Source responsePayload = new StringSource(

                        "<ns2:getPokemonBaseExperienceResponse xmlns:ns2=\"http://pokemon.com/pkm\">\n" +
                        "<ns2:baseExperience>10</ns2:baseExperience>\n" +
                        "</ns2:getPokemonBaseExperienceResponse>\n" );

        mockWebServiceClient.sendRequest(withPayload(requestPayload)).
                andExpect(payload(responsePayload));
    }
}