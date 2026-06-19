package com.cefet.apisgr.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.ContaPendenteResponseDTO;
import com.cefet.apisgr.dtos.ExtratoResponseDTO;
import com.cefet.apisgr.dtos.GraficoResponseDTO;
import com.cefet.apisgr.dtos.SaldoResponseDTO;
import com.cefet.apisgr.entities.enums.SituacaoConta;
import com.cefet.apisgr.entities.enums.SituacaoRateio;
import com.cefet.apisgr.repositories.ContaRepository;
import com.cefet.apisgr.repositories.RateioRepository;

@Service
public class RelatorioService {

    @Autowired
    private RateioRepository rateioRepository;
    
    @Autowired
    private ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public List<ExtratoResponseDTO> consultarExtrato(LocalDate dataInicio, LocalDate dataFim, SituacaoRateio situacao, Long moradorId) {
        return rateioRepository.consultarExtrato(dataInicio, dataFim, situacao, moradorId);
    }

    @Transactional(readOnly = true)
    public List<SaldoResponseDTO> consultarSaldo(Long moradorId) {
        return rateioRepository.consultarSaldo(moradorId, SituacaoRateio.EM_ABERTO);
    }
    
    @Transactional(readOnly = true)
    public List<GraficoResponseDTO> consultarGastosPorTipo(Integer mes, Integer ano) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());

        return rateioRepository.consultarGastosPorTipo(dataInicio, dataFim);
    }

    @Transactional(readOnly = true)
    public List<GraficoResponseDTO> consultarGastosPorMorador(Integer mes, Integer ano) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());

        return rateioRepository.consultarGastosPorMorador(dataInicio, dataFim);
    }

    @Transactional(readOnly = true)
    public List<ContaPendenteResponseDTO> consultarContasPendentes(Integer mes, Integer ano) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());

        return contaRepository.consultarContasPendentes(dataInicio, dataFim, SituacaoConta.PENDENTE);
    }    
    
}