package com.cefet.apisgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.apisgr.dtos.CredencialRequestDTO;
import com.cefet.apisgr.dtos.CredencialResponseDTO;
import com.cefet.apisgr.services.AutenticacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Autenticações")
public class AutenticacaoController {

	@Autowired
	private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário")
    public ResponseEntity<CredencialResponseDTO> autenticar(@Valid @RequestBody CredencialRequestDTO dto) {     
    	String aplicacao = "APP";
    	CredencialResponseDTO response = autenticacaoService.autenticar(dto.getLogin(), dto.getSenha(), aplicacao);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }	

}
