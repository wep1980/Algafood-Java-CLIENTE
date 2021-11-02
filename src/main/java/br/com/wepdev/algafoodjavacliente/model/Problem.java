package br.com.wepdev.algafoodjavacliente.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Problem {


    private Integer status;
    private String userMessage;
    private OffsetDateTime timestamp;

}
