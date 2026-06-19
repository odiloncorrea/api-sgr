package com.cefet.apisgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.ContaRequestDTO;
import com.cefet.apisgr.dtos.ContaResponseDTO;
import com.cefet.apisgr.dtos.RateioRequestDTO;
import com.cefet.apisgr.entities.Conta;
import com.cefet.apisgr.entities.Morador;
import com.cefet.apisgr.entities.Rateio;
import com.cefet.apisgr.entities.Tipo;
import com.cefet.apisgr.entities.enums.SituacaoConta;
import com.cefet.apisgr.exceptions.BusinessException;
import com.cefet.apisgr.exceptions.ResourceNotFoundException;
import com.cefet.apisgr.repositories.ContaRepository;
import com.cefet.apisgr.repositories.MoradorRepository;
import com.cefet.apisgr.repositories.RateioRepository;
import com.cefet.apisgr.repositories.TipoRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private RateioRepository rateioRepository;

    @Transactional(readOnly = true)
    public List<ContaResponseDTO> listar() {
        List<Conta> contas = contaRepository.findAll();

        return contas.stream()
                .map(conta -> {
                    List<Rateio> rateios = rateioRepository.findByContaId(conta.getId());
                    return new ContaResponseDTO(conta, rateios);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResponseDTO buscarPorId(Long id) {
        Conta conta = buscarConta(id);
        List<Rateio> rateios = rateioRepository.findByContaId(id);

        return new ContaResponseDTO(conta, rateios);
    }

    @Transactional
    public ContaResponseDTO inserir(ContaRequestDTO dto) {
        
        Tipo tipo = buscarTipo(dto.getTipoContaId());
        Morador moradorResponsavel = buscarMorador(dto.getMoradorResponsavelId());

        Conta conta = new Conta();
        conta.setDescricao(dto.getDescricao());
        conta.setValor(dto.getValor());
        conta.setVencimento(dto.getVencimento());
        conta.setSituacao(dto.getSituacao());
        conta.setObservacao(dto.getObservacao());
        conta.setTipo(tipo);
        conta.setMorador(moradorResponsavel);

        conta = contaRepository.save(conta);

        salvarRateios(conta, dto.getRateios());

        List<Rateio> rateios = rateioRepository.findByContaId(conta.getId());
        return new ContaResponseDTO(conta, rateios);
    }

    @Transactional
    public ContaResponseDTO atualizar(Long id, ContaRequestDTO dto) {
        Conta conta = buscarConta(id);

        /*	
        if (conta.getSituacao() == SituacaoConta.QUITADA ||
            conta.getSituacao() == SituacaoConta.CANCELADA) {
            throw new BusinessException("Não é permitido alterar contas quitadas ou canceladas.");
        }
        */
		
        
        Tipo tipo = buscarTipo(dto.getTipoContaId());
        Morador moradorResponsavel = buscarMorador(dto.getMoradorResponsavelId());

        conta.setDescricao(dto.getDescricao());
        conta.setValor(dto.getValor());
        conta.setVencimento(dto.getVencimento());
        conta.setSituacao(dto.getSituacao());
        conta.setObservacao(dto.getObservacao());
        conta.setTipo(tipo);
        conta.setMorador(moradorResponsavel);

        conta = contaRepository.save(conta);

        rateioRepository.deleteByContaId(conta.getId());
        salvarRateios(conta, dto.getRateios());

        List<Rateio> rateios = rateioRepository.findByContaId(conta.getId());
        return new ContaResponseDTO(conta, rateios);
    }

    @Transactional
    public void excluir(Long id) {
        Conta conta = buscarConta(id);

        if (conta.getSituacao() == SituacaoConta.QUITADA ||
            conta.getSituacao() == SituacaoConta.CANCELADA) {
            throw new BusinessException("Não é permitido excluir contas quitadas ou canceladas.");
        }

        rateioRepository.deleteByContaId(id);
        contaRepository.delete(conta);
    }

    private void salvarRateios(Conta conta, List<RateioRequestDTO> rateiosDTO) {
        if (rateiosDTO == null || rateiosDTO.isEmpty()) {
            return;
        }

        for (RateioRequestDTO dto : rateiosDTO) {
            Morador morador = buscarMorador(dto.getMoradorId());

            Rateio rateio = new Rateio();
            rateio.setConta(conta);
            rateio.setMorador(morador);
            rateio.setValor(dto.getValor());
            rateio.setSituacao(dto.getSituacao());

            rateioRepository.save(rateio);
        }
    }

    private Conta buscarConta(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada. Id: " + id));
    }

    private Tipo buscarTipo(Long id) {
        return tipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de conta não encontrado. Id: " + id));
    }

    private Morador buscarMorador(Long id) {
        return moradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Morador não encontrado. Id: " + id));
    }
}