package com.cefet.apisgr.dtos;

import java.time.LocalDate;

import com.cefet.apisgr.entities.Morador;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoradorResponseDTO {
	
	private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String contatos;
    
    public MoradorResponseDTO(Morador morador) {
    	this.id = morador.getId();
        this.nome = morador.getNome();
        this.cpf = morador.getCpf();
        this.dataNascimento = morador.getDataNascimento();
        this.email = morador.getEmail();
        this.celular = morador.getCelular();
        this.contatos = morador.getContatos();
    }  	

}
