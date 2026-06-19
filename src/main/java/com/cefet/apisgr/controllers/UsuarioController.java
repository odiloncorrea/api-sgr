package com.cefet.apisgr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.apisgr.dtos.UsuarioRequestDTO;
import com.cefet.apisgr.dtos.UsuarioResponseDTO;
import com.cefet.apisgr.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar usuários")
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> lista = usuarioService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário")
    public ResponseEntity<UsuarioResponseDTO> inserir(
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.inserir(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @PatchMapping("/{id}/senha")
    @Operation(summary = "Alterar senha do usuário")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @RequestParam String senha) {

        usuarioService.alterarSenha(id, senha);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-login")
    @Operation(summary = "Verificar login")
    public ResponseEntity<Boolean> verificarLogin(@RequestParam String login) {
        boolean exists = usuarioService.verificarLogin(login);
        return ResponseEntity.ok(exists);
    }
}