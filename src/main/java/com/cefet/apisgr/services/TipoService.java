package com.cefet.apisgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.TipoRequestDTO;
import com.cefet.apisgr.dtos.TipoResponseDTO;
import com.cefet.apisgr.entities.Tipo;
import com.cefet.apisgr.exceptions.ResourceNotFoundException;
import com.cefet.apisgr.repositories.TipoRepository;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    @Transactional(readOnly = true)
    public List<TipoResponseDTO> listar() {
        List<Tipo> lista = tipoRepository.findAll();
        return lista.stream().map(TipoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TipoResponseDTO buscarPorId(Long id) {
        Tipo entity = tipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo não encontrado. Id: " + id));

        return new TipoResponseDTO(entity);
    }

    @Transactional
    public TipoResponseDTO inserir(TipoRequestDTO dto) {

    	Tipo tipo = new Tipo();
        tipo.setDescricao(dto.getDescricao());

        return new TipoResponseDTO(tipoRepository.save(tipo));
    }
    
    @Transactional
    public TipoResponseDTO atualizar(Long id, TipoRequestDTO dto) {

        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo não encontrado. Id: " + id));

        tipo.setDescricao(dto.getDescricao());

        return new TipoResponseDTO(tipoRepository.save(tipo));
    }    

    @Transactional
    public void excluir(Long id) {
        if (!tipoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo não encontrado com ID: " + id);
        }

        tipoRepository.deleteById(id);
    }
}