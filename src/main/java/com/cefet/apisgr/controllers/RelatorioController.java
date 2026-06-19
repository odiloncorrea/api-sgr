package com.cefet.apisgr.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.apisgr.dtos.ContaPendenteResponseDTO;
import com.cefet.apisgr.dtos.ExtratoResponseDTO;
import com.cefet.apisgr.dtos.GraficoResponseDTO;
import com.cefet.apisgr.dtos.SaldoResponseDTO;
import com.cefet.apisgr.entities.enums.SituacaoRateio;
import com.cefet.apisgr.services.RelatorioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatórios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/extrato")
    @Operation(summary = "Consultar extrato de rateios")
    public ResponseEntity<List<ExtratoResponseDTO>> consultarExtrato(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,

            @RequestParam(required = false) SituacaoRateio situacao,
            
            @RequestParam(required = false) Long moradorId) {
    	
        List<ExtratoResponseDTO> lista = relatorioService.consultarExtrato(dataInicio, dataFim, situacao, moradorId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/saldo")
    @Operation(summary = "Consultar saldo em aberto por morador")
    public ResponseEntity<List<SaldoResponseDTO>> consultarSaldo(@RequestParam(required = false) Long moradorId) {
    	
        List<SaldoResponseDTO> lista = relatorioService.consultarSaldo(moradorId);
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/gastos-por-tipo")
    @Operation(summary = "Consultar gastos por tipo no mês")
    public ResponseEntity<List<GraficoResponseDTO>> consultarGastosPorTipo(
            @RequestParam Integer mes,
            @RequestParam Integer ano) {

        List<GraficoResponseDTO> lista =
                relatorioService.consultarGastosPorTipo(mes, ano);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/gastos-por-morador")
    @Operation(summary = "Consultar gastos por morador no mês")
    public ResponseEntity<List<GraficoResponseDTO>> consultarGastosPorMorador(
            @RequestParam Integer mes,
            @RequestParam Integer ano) {

        List<GraficoResponseDTO> lista =
                relatorioService.consultarGastosPorMorador(mes, ano);

        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/contas-pendentes")
    @Operation(summary = "Consultar contas pendentes no mês")
    public ResponseEntity<List<ContaPendenteResponseDTO>> consultarContasPendentes(
            @RequestParam Integer mes,
            @RequestParam Integer ano) {

        List<ContaPendenteResponseDTO> lista =
                relatorioService.consultarContasPendentes(mes, ano);

        return ResponseEntity.ok(lista);
    }
    
    
    
    
}