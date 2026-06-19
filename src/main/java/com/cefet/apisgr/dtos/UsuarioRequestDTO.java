package com.cefet.apisgr.dtos;

import com.cefet.apisgr.entities.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "O login é obrigatório.")
    @Size(min = 3, message = "O login deve ter no mínimo 3 caracteres.")
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.")
    private String senha;
    
    @NotNull(message = "O perfil é obrigatório.")
    private Perfil perfil;   
    
    @NotNull(message = "O morador é obrigatório.")
    private Long moradorId;    

}