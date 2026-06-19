package com.cefet.apisgr.dtos;

import com.cefet.apisgr.entities.enums.SituacaoRateio;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RateioRequestDTO {

    @NotNull(message = "O morador é obrigatório.")
    private Long moradorId;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    private Double valor;

    @NotNull(message = "A situação é obrigatória.")
    private SituacaoRateio situacao;
}