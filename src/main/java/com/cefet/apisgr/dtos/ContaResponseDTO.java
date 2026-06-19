package com.cefet.apisgr.dtos;

import java.time.LocalDate;
import java.util.List;

import com.cefet.apisgr.entities.Conta;
import com.cefet.apisgr.entities.Rateio;
import com.cefet.apisgr.entities.enums.SituacaoConta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContaResponseDTO {

    private Long id;
    private String descricao;
    private Double valor;
    private LocalDate vencimento;
    private SituacaoConta situacao;
    private String observacao;

    private Long tipoContaId;
    private String tipoDescricao;

    private Long moradorResponsavelId;
    private String moradorResponsavelNome;

    private List<RateioResponseDTO> rateios;

    public ContaResponseDTO(Conta conta, List<Rateio> rateios) {
        this.id = conta.getId();
        this.descricao = conta.getDescricao();
        this.valor = conta.getValor();
        this.vencimento = conta.getVencimento();
        this.situacao = conta.getSituacao();
        this.observacao = conta.getObservacao();

        this.tipoContaId = conta.getTipo().getId();
        this.tipoDescricao = conta.getTipo().getDescricao();

        this.moradorResponsavelId = conta.getMorador().getId();
        this.moradorResponsavelNome = conta.getMorador().getNome();

        this.rateios = rateios.stream().map(RateioResponseDTO::new).toList();
    }
}