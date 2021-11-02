package br.com.wepdev.algafoodjavacliente.api;

import br.com.wepdev.algafoodjavacliente.model.Problem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
public class ClientApiException extends RuntimeException{


    @Getter
    private Problem problem;


    /**
     * Metodo construtor
     * @param message
     * @param cause
     */
    public ClientApiException(String message, RestClientResponseException cause) {

        //System.out.println(cause.getResponseBodyAsString());
        deserializeProblem(cause);
    }


    /**
     * Metodo que deserializa a String e transforma em um objeto java
     * @param cause
     */
    private void deserializeProblem(RestClientResponseException cause){

        ObjectMapper mapper = new ObjectMapper(); // deserializa a String em objeto java
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Desabilita a falha caso haja propriedades no JSON que nao existam aqui
        mapper.registerModule(new JavaTimeModule()); // Adicionando um modulo do jackson que tem a capaciadade de serializar e deserializar objetos do java Time(Data, Hora)
        mapper.findAndRegisterModules();

        try {
            this.problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class); // Transforma a String em um objeto Problem
        } catch (JsonProcessingException e) { // Se der esse erro JsonProcessingException, a mensagem de warning sera exibida
            log.warn("Não foi possível desserializar a resposta em um problema", e);
        }

    }
}
