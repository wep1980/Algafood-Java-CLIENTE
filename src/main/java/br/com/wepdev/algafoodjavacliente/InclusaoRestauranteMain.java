
package br.com.wepdev.algafoodjavacliente;

import br.com.wepdev.algafoodjavacliente.api.ClientApiException;
import br.com.wepdev.algafoodjavacliente.api.RestauranteClient;
import br.com.wepdev.algafoodjavacliente.model.RestauranteModel;
import br.com.wepdev.algafoodjavacliente.model.input.CidadeIdInput;
import br.com.wepdev.algafoodjavacliente.model.input.CozinhaIdInput;
import br.com.wepdev.algafoodjavacliente.model.input.EnderecoInput;
import br.com.wepdev.algafoodjavacliente.model.input.RestauranteInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {
        try {
            var restTemplate = new RestTemplate();
            var restauranteClient = new RestauranteClient( "http://api.algafood.local:8080", restTemplate);

            var cozinha = new CozinhaIdInput();
            cozinha.setId(1L);

            var cidade = new CidadeIdInput();
            cidade.setId(1L);

            var endereco = new EnderecoInput();
            endereco.setCidade(cidade);
            endereco.setCep("38500-111");
            endereco.setLogradouro("Rua Xyz");
            endereco.setNumero("300");
            endereco.setBairro("Centro");

            var restaurante = new RestauranteInput();
            restaurante.setNome("Comida Mineira");
            restaurante.setTaxaFrete(new BigDecimal(9.5));
            restaurante.setCozinha(new CozinhaIdInput());
            restaurante.setCozinha(cozinha);
            restaurante.setEndereco(endereco);

            RestauranteModel restauranteModel = restauranteClient.adicionar(restaurante);

            System.out.println(restauranteModel);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getUserMessage());

                e.getProblem().getObjects().stream()
                        .forEach(p -> System.out.println("- " + p.getUserMessage()));

            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }

}
