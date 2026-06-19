package com.cefet.apisgr.dtos;

import java.time.LocalDate;

import com.cefet.apisgr.entities.enums.SituacaoConta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaPendenteResponseDTO {

    private Long contaId;
    private String tipo;
    private Double valor;
    private LocalDate vencimento;
    private SituacaoConta situacao;
}