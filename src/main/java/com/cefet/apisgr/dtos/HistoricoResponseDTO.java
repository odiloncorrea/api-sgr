package com.cefet.apisgr.dtos;

import java.time.LocalDateTime;

import com.cefet.apisgr.entities.Historico;
import com.cefet.apisgr.entities.enums.SituacaoConta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoricoResponseDTO {

    private Long id;

    private Long contaId;

    private Long moradorId;
    private String moradorNome;

    private LocalDateTime data;
    private SituacaoConta situacao;

    public HistoricoResponseDTO(Historico historico) {
        this.id = historico.getId();

        this.contaId = historico.getConta().getId();

        this.moradorId = historico.getMorador().getId();
        this.moradorNome = historico.getMorador().getNome();

        this.data = historico.getData();
        this.situacao = historico.getSituacao();
    }
}