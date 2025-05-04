package com.exemplo.apicinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DiretorRecordDto(UUID idDiretor,
                               @NotBlank @Size(min = 2, max = 100) String nome,
                               @NotBlank String nacionalidade) {
}
