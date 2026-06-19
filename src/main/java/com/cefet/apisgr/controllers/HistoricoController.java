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

import com.cefet.apisgr.dtos.HistoricoRequestDTO;
import com.cefet.apisgr.dtos.HistoricoResponseDTO;
import com.cefet.apisgr.services.HistoricoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/historicos")
@Tag(name = "Históricos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    @Operation(summary = "Listar históricos")
    public ResponseEntity<List<HistoricoResponseDTO>> listar() {
        List<HistoricoResponseDTO> lista = historicoService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar histórico por ID")
    public ResponseEntity<HistoricoResponseDTO> buscarPorId(@PathVariable Long id) {
        HistoricoResponseDTO historicoResponseDTO = historicoService.buscarPorId(id);
        return ResponseEntity.ok(historicoResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar histórico")
    public ResponseEntity<HistoricoResponseDTO> inserir(
            @Valid @RequestBody HistoricoRequestDTO historicoRequestDTO) {

        HistoricoResponseDTO historicoResponseDTO =
                historicoService.inserir(historicoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(historicoResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar histórico")
    public ResponseEntity<HistoricoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody HistoricoRequestDTO historicoRequestDTO) {

        HistoricoResponseDTO historicoResponseDTO =
                historicoService.atualizar(id, historicoRequestDTO);

        return ResponseEntity.ok(historicoResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir histórico")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        historicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}