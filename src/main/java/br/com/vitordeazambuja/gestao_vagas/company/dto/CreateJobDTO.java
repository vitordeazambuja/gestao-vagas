package br.com.vitordeazambuja.gestao_vagas.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

    private String description;
    private String benefits;
    private String level;
}
