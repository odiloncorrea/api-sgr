package com.cefet.apisgr.dtos;

import com.cefet.apisgr.entities.Rateio;
import com.cefet.apisgr.entities.enums.SituacaoRateio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateioResponseDTO {

    private Long id;
    private Double valor;
    private SituacaoRateio situacao;

    private Long contaId;

    private Long moradorId;
    private String moradorNome;

    public RateioResponseDTO(Rateio rateio) {
        this.id = rateio.getId();
        this.valor = rateio.getValor();
        this.situacao = rateio.getSituacao();

        this.contaId = rateio.getConta().getId();

        this.moradorId = rateio.getMorador().getId();
        this.moradorNome = rateio.getMorador().getNome();
    }
}