package com.cefet.apisgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.HistoricoRequestDTO;
import com.cefet.apisgr.dtos.HistoricoResponseDTO;
import com.cefet.apisgr.entities.Conta;
import com.cefet.apisgr.entities.Historico;
import com.cefet.apisgr.entities.Morador;
import com.cefet.apisgr.exceptions.ResourceNotFoundException;
import com.cefet.apisgr.repositories.ContaRepository;
import com.cefet.apisgr.repositories.HistoricoRepository;
import com.cefet.apisgr.repositories.MoradorRepository;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Transactional(readOnly = true)
    public List<HistoricoResponseDTO> listar() {
        List<Historico> lista = historicoRepository.findAll();
        return lista.stream().map(HistoricoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public HistoricoResponseDTO buscarPorId(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Histórico não encontrado. Id: " + id));

        return new HistoricoResponseDTO(historico);
    }

    @Transactional
    public HistoricoResponseDTO inserir(HistoricoRequestDTO dto) {

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conta não encontrada. Id: " + dto.getContaId()));

        Morador morador = moradorRepository.findById(dto.getMoradorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Morador não encontrado. Id: " + dto.getMoradorId()));

        Historico historico = new Historico();
        historico.setConta(conta);
        historico.setMorador(morador);
        historico.setData(dto.getData());
        historico.setSituacao(dto.getSituacao());

        return new HistoricoResponseDTO(historicoRepository.save(historico));
    }

    @Transactional
    public HistoricoResponseDTO atualizar(Long id, HistoricoRequestDTO dto) {

        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Histórico não encontrado. Id: " + id));

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conta não encontrada. Id: " + dto.getContaId()));

        Morador morador = moradorRepository.findById(dto.getMoradorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Morador não encontrado. Id: " + dto.getMoradorId()));

        historico.setConta(conta);
        historico.setMorador(morador);
        historico.setData(dto.getData());
        historico.setSituacao(dto.getSituacao());

        return new HistoricoResponseDTO(historicoRepository.save(historico));
    }

    @Transactional
    public void excluir(Long id) {
        if (!historicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Histórico não encontrado. Id: " + id);
        }

        historicoRepository.deleteById(id);
    }
}