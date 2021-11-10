package br.com.wepdev.algafoodjavacliente.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteModel {


    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;
    private Boolean aberto;

    private CozinhaModel cozinha;
    private EnderecoModel endereco;


}
