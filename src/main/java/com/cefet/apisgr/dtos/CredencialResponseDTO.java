package com.cefet.apisgr.dtos;

import com.cefet.apisgr.entities.Perfil;
import com.cefet.apisgr.entities.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CredencialResponseDTO {

    private Long id;    
    private String login;
    private Perfil perfil;
    private Long moradorId;
    private String moradorNome;    
   
    public CredencialResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.perfil = usuario.getPerfil();
        this.moradorId = usuario.getMorador().getId();
        this.moradorNome = usuario.getMorador().getNome();
    }
}
