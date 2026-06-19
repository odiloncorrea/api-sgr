package com.cefet.apisgr.dtos;

import java.time.LocalDateTime;

import com.cefet.apisgr.entities.enums.SituacaoConta;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HistoricoRequestDTO {

    @NotNull(message = "A conta é obrigatória.")
    private Long contaId;

    @NotNull(message = "O morador é obrigatório.")
    private Long moradorId;

    @NotNull(message = "A data é obrigatória.")
    private LocalDateTime data;

    @NotNull(message = "A situação é obrigatória.")
    private SituacaoConta situacao;
}