package br.com.wepdev.algafoodjavacliente;

import br.com.wepdev.algafoodjavacliente.api.ClientApiException;
import br.com.wepdev.algafoodjavacliente.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            RestauranteClient restauranteClient = new RestauranteClient( "http://api.algafood.local:8080", restTemplate);

            // Imprime no console cada restaurante que estiver na lista
            restauranteClient.listar().stream().forEach(restaurante -> System.out.println(restaurante));
        } catch (ClientApiException e){
            if(e.getProblem() != null){
                System.out.println(e.getProblem());
                System.out.println(e.getProblem().getUserMessage());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }


}
