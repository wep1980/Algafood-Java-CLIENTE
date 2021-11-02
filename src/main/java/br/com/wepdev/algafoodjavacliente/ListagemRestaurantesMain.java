package br.com.wepdev.algafoodjavacliente;

import br.com.wepdev.algafoodjavacliente.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        RestauranteClient restauranteClient = new RestauranteClient( "http://api.algafood.local:8080", restTemplate);

        // Imprime no console cada restaurante que estiver na lista
        restauranteClient.listar().stream().forEach(restaurante -> System.out.println(restaurante));

    }
}
