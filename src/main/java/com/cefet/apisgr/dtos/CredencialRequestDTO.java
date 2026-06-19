package com.cefet.apisgr.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CredencialRequestDTO {

    @NotBlank(message = "O campo 'login' é obrigatório.")
    @Size(min = 3, message = "O login deve ter no mínimo 3 caracteres.")
    private String login; 

    @NotBlank(message = "O campo 'senha' é obrigatório.")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.")
    private String senha;
               
}
