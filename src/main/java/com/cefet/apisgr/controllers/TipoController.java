package com.cefet.apisgr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.apisgr.dtos.TipoRequestDTO;
import com.cefet.apisgr.dtos.TipoResponseDTO;
import com.cefet.apisgr.services.TipoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipos")
@Tag(name = "Tipos")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping
    @Operation(summary = "Listar tipos")
    public ResponseEntity<List<TipoResponseDTO>> listar() {
        List<TipoResponseDTO> lista = tipoService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo por ID")
    public ResponseEntity<TipoResponseDTO> buscarPorId(@PathVariable Long id) {
    	TipoResponseDTO tipoResponseDTO = tipoService.buscarPorId(id);
        return ResponseEntity.ok(tipoResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar tipo")
    public ResponseEntity<TipoResponseDTO> inserir(@Valid @RequestBody TipoRequestDTO tipoRequestDTO) {
    	TipoResponseDTO tipoResponseDTO = tipoService.inserir(tipoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoResponseDTO);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo")
    public ResponseEntity<TipoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoRequestDTO tipoRequestDTO) {

    	TipoResponseDTO tipoResponseDTO = tipoService.atualizar(id, tipoRequestDTO);

        return ResponseEntity.ok(tipoResponseDTO);
    }    

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tipo")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tipoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}