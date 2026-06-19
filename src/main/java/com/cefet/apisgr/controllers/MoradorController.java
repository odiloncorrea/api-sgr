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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.apisgr.dtos.MoradorRequestDTO;
import com.cefet.apisgr.dtos.MoradorResponseDTO;
import com.cefet.apisgr.services.MoradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/moradores")
@Tag(name = "Moradores")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @GetMapping
    @Operation(summary = "Listar moradores")
    public ResponseEntity<List<MoradorResponseDTO>> listar() {
        List<MoradorResponseDTO> lista = moradorService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar morador por ID")
    public ResponseEntity<MoradorResponseDTO> buscarPorId(@PathVariable Long id) {
        MoradorResponseDTO moradorResponseDTO = moradorService.buscarPorId(id);
        return ResponseEntity.ok(moradorResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar morador")
    public ResponseEntity<MoradorResponseDTO> inserir(@Valid @RequestBody MoradorRequestDTO moradorRequestDTO) {
        MoradorResponseDTO moradorResponseDTO = moradorService.inserir(moradorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(moradorResponseDTO);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar morador")
    public ResponseEntity<MoradorResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MoradorRequestDTO moradorRequestDTO) {

        MoradorResponseDTO moradorResponseDTO =
                moradorService.atualizar(id, moradorRequestDTO);

        return ResponseEntity.ok(moradorResponseDTO);
    }    

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir morador")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        moradorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-cpf")
    @Operation(summary = "Verificar CPF")
    public ResponseEntity<Boolean> verificarCpf(@RequestParam String cpf) {
        boolean exists = moradorService.verificarCpf(cpf);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/check-email")
    @Operation(summary = "Verificar e-mail")
    public ResponseEntity<Boolean> verificarEmail(@RequestParam String email) {
        boolean exists = moradorService.verificarEmail(email);
        return ResponseEntity.ok(exists);
    }
}