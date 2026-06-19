package com.cefet.apisgr.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TipoRequestDTO {

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

}