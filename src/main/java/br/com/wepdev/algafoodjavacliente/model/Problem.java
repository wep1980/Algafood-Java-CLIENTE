package br.com.wepdev.algafoodjavacliente.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Problem {


    private Integer status;
    private String userMessage;
    private OffsetDateTime timestamp;

    private List<Object> objects = new ArrayList<>();



    @Data
    public static class Object {

        private String name;
        private String userMessage;

    }

}
