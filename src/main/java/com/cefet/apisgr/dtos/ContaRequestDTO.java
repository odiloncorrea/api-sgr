package com.cefet.apisgr.dtos;

import java.time.LocalDate;
import java.util.List;

import com.cefet.apisgr.entities.enums.SituacaoConta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContaRequestDTO {

    @Size(max = 255, message = "A descrição deve possuir no máximo 255 caracteres.")
    private String descricao;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    private Double valor;

    @NotNull(message = "A data de vencimento é obrigatória.")
    private LocalDate vencimento;

    @NotNull(message = "A situação é obrigatória.")
    private SituacaoConta situacao;

    @Size(max = 2000, message = "A observação deve possuir no máximo 2000 caracteres.")
    private String observacao;

    @NotNull(message = "O tipo da conta é obrigatório.")
    private Long tipoContaId;

    @NotNull(message = "O morador responsável é obrigatório.")
    private Long moradorResponsavelId;

    @Valid
    private List<RateioRequestDTO> rateios;
}