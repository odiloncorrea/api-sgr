package com.cefet.apisgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.apisgr.dtos.UsuarioRequestDTO;
import com.cefet.apisgr.dtos.UsuarioResponseDTO;
import com.cefet.apisgr.entities.Morador;
import com.cefet.apisgr.entities.Usuario;
import com.cefet.apisgr.exceptions.BusinessException;
import com.cefet.apisgr.exceptions.ResourceNotFoundException;
import com.cefet.apisgr.repositories.MoradorRepository;
import com.cefet.apisgr.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Autowired
    private MoradorRepository moradorRepository;	

	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> listar() {
		List<Usuario> lista = usuarioRepository.findAll();
		return lista.stream().map(UsuarioResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public UsuarioResponseDTO buscarPorId(Long id) {
		Usuario entity = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não localizada. Id: " + id));

		return new UsuarioResponseDTO(entity);
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {

		if (usuarioRepository.existsByLogin(dto.getLogin())) {
			throw new BusinessException("Login do usuário já cadastrado.");
		}
		
        Morador morador = moradorRepository.findById(dto.getMoradorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Morador não encontrado. Id: " + dto.getMoradorId()));		

		Usuario usuario = new Usuario();
		usuario.setLogin(dto.getLogin());
		usuario.setSenha(dto.getSenha());
		usuario.setPerfil(dto.getPerfil());
		usuario.setMorador(morador);

		return new UsuarioResponseDTO(usuarioRepository.save(usuario));
	}
	
	@Transactional
	public void alterarSenha(Long id, String senha) {
		Usuario entity = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o ID: " + id));

		entity.setSenha(senha);
		entity = usuarioRepository.save(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Usuário não localizada com ID: " + id);
		}

		usuarioRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean verificarLogin(String login) {
		return usuarioRepository.existsByLogin(login);
	}
}
