package com.cefet.apisgr.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaldoResponseDTO {

    private Long moradorId;
    private String moradorNome;
    private Double valor;
}