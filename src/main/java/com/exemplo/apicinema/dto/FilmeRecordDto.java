package com.exemplo.apicinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FilmeRecordDto(@NotBlank @Size(min = 2, max = 100) String nome,
                             @NotBlank @Size(min = 2, max = 40) String classificacao,
                             @NotBlank @Size(min = 2,max = 100) String genero,
                             DiretorRecordDto diretor,
                             int ano) {
}
