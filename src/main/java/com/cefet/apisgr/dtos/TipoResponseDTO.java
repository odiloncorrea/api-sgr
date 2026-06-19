package com.cefet.apisgr.dtos;

import com.cefet.apisgr.entities.Tipo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoResponseDTO {
	
	private Long id;
    private String descricao;
    
    public TipoResponseDTO(Tipo tipo) {
    	this.id = tipo.getId();
        this.descricao = tipo.getDescricao();
    }  	

}
