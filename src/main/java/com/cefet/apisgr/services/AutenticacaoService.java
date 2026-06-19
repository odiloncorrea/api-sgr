package com.cefet.apisgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.CredencialResponseDTO;
import com.cefet.apisgr.entities.Usuario;
import com.cefet.apisgr.exceptions.ResourceNotFoundException;
import com.cefet.apisgr.repositories.UsuarioRepository;

@Service
public class AutenticacaoService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public CredencialResponseDTO autenticar(String login, String senha, String aplicacao) {
		Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);

		if (usuario == null) {
			throw new ResourceNotFoundException("Login ou senha inválidos.");
		}

		return new CredencialResponseDTO(usuario);
	}
}
