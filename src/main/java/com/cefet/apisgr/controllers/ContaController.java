package com.cefet.apisgr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.apisgr.dtos.ContaRequestDTO;
import com.cefet.apisgr.dtos.ContaResponseDTO;
import com.cefet.apisgr.services.ContaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
@Tag(name = "Contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    @Operation(summary = "Listar contas")
    public ResponseEntity<List<ContaResponseDTO>> listar() {
        return ResponseEntity.ok(contaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar conta por ID")
    public ResponseEntity<ContaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar conta")
    public ResponseEntity<ContaResponseDTO> inserir(
            @Valid @RequestBody ContaRequestDTO dto) {

        ContaResponseDTO response = contaService.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar conta")
    public ResponseEntity<ContaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ContaRequestDTO dto) {

        return ResponseEntity.ok(contaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir conta")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}