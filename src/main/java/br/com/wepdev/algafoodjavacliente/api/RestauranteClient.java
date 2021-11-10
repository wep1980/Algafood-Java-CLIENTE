package br.com.wepdev.algafoodjavacliente.api;

import br.com.wepdev.algafoodjavacliente.model.RestauranteModel;
import br.com.wepdev.algafoodjavacliente.model.RestauranteResumoModel;
import br.com.wepdev.algafoodjavacliente.model.input.RestauranteInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que representa o modelo de Restaurante que vem na resposta do payload(corpo)
 *
 * Cliente, consome a api algafood
 */
@AllArgsConstructor // lombok -> cria construtor com as propriedades da classe
public class RestauranteClient {


    private static final String RESOURCE_PATH = "/restaurantes";

    private String url;

    /**
     * Para fazer requisições HTTP
     */
    private RestTemplate restTemplate;



    public List<RestauranteResumoModel> listar(){

        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            RestauranteResumoModel[] restaurantes = restTemplate
                    .getForObject(resourceUri, RestauranteResumoModel[].class); // Faz um get na uri passada e retorna a representação descerializada

            return Arrays.asList(restaurantes); // Retornando o array trasnformado em lista
        } catch (RestClientResponseException e) { // Em caso de erro dessa exception ela sera relançada como ClientApiException
            throw new ClientApiException(e.getMessage(), e);

        }
    }



    public RestauranteModel adicionar(RestauranteInput restaurante) {
        var resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            return restTemplate
                    .postForObject(resourceUri, restaurante, RestauranteModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}
