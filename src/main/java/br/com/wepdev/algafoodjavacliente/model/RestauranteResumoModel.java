package br.com.wepdev.algafoodjavacliente.model;

import lombok.Data;

import java.math.BigDecimal;

@Data // Lombok, gera os getters , setters, toString e etc....
public class RestauranteResumoModel {


    private Long id;
    private String nome;
    private BigDecimal taxaFrete;

    private CozinhaModel cozinha;
}
