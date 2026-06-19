package com.cefet.apisgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.MoradorRequestDTO;
import com.cefet.apisgr.dtos.MoradorResponseDTO;
import com.cefet.apisgr.entities.Morador;
import com.cefet.apisgr.exceptions.BusinessException;
import com.cefet.apisgr.exceptions.ResourceNotFoundException;
import com.cefet.apisgr.repositories.MoradorRepository;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Transactional(readOnly = true)
    public List<MoradorResponseDTO> listar() {
        List<Morador> lista = moradorRepository.findAll();
        return lista.stream().map(MoradorResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public MoradorResponseDTO buscarPorId(Long id) {
        Morador entity = moradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Morador não encontrado. Id: " + id));

        return new MoradorResponseDTO(entity);
    }

    @Transactional
    public MoradorResponseDTO inserir(MoradorRequestDTO dto) {

        if (moradorRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("CPF do morador já cadastrado.");
        }

        if (moradorRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("E-mail do morador já cadastrado.");
        }

        Morador morador = new Morador();
        morador.setNome(dto.getNome());
        morador.setCpf(dto.getCpf());
        morador.setDataNascimento(dto.getDataNascimento());
        morador.setEmail(dto.getEmail());
        morador.setCelular(dto.getCelular());
        morador.setContatos(dto.getContatos());

        return new MoradorResponseDTO(moradorRepository.save(morador));
    }
    
    @Transactional
    public MoradorResponseDTO atualizar(Long id, MoradorRequestDTO dto) {

        Morador morador = moradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Morador não encontrado. Id: " + id));
        
        

        if (!morador.getCpf().equals(dto.getCpf())
                && moradorRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("CPF do morador já cadastrado.");
        }

        if (!morador.getEmail().equals(dto.getEmail())
                && moradorRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("E-mail do morador já cadastrado.");
        }

        morador.setNome(dto.getNome());
        morador.setCpf(dto.getCpf());
        morador.setDataNascimento(dto.getDataNascimento());
        morador.setEmail(dto.getEmail());
        morador.setCelular(dto.getCelular());
        morador.setContatos(dto.getContatos());

        return new MoradorResponseDTO(moradorRepository.save(morador));
    }    

    @Transactional
    public void excluir(Long id) {
        if (!moradorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Morador não encontrado com ID: " + id);
        }

        moradorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean verificarCpf(String cpf) {
        return moradorRepository.existsByCpf(cpf);
    }

    @Transactional(readOnly = true)
    public boolean verificarEmail(String email) {
        return moradorRepository.existsByEmail(email);
    }
}