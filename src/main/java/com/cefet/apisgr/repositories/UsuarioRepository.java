package com.cefet.apisgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.apisgr.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	boolean existsByLogin(String login);
	Usuario findByLoginAndSenha(String login, String senha);
	Usuario findByLogin(String login);
}
