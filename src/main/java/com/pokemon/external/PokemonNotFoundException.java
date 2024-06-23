package com.pokemon.external;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class PokemonNotFoundException extends RuntimeException {
    PokemonNotFoundException(String message) {
        super("Pokemon with name [" + message + "] not found, please try another one");
    }
}
