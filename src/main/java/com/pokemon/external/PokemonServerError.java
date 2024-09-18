package com.pokemon.external;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class PokemonServerError extends RuntimeException {
    public PokemonServerError() {
        super("PokeServer error, please try again later");
    }
}
