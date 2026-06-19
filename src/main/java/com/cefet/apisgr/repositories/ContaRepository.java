package com.cefet.apisgr.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cefet.apisgr.dtos.ContaPendenteResponseDTO;
import com.cefet.apisgr.entities.Conta;
import com.cefet.apisgr.entities.enums.SituacaoConta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	@Query("""
		    SELECT new com.cefet.apisgr.dtos.ContaPendenteResponseDTO(
		        c.id,
		        c.tipo.descricao,
		        c.valor,
		        c.vencimento,
		        c.situacao
		    )
		    FROM Conta c
		    WHERE c.situacao = :situacao
		      AND c.vencimento BETWEEN :dataInicio AND :dataFim
		    ORDER BY c.vencimento
		""")
		List<ContaPendenteResponseDTO> consultarContasPendentes(
		        LocalDate dataInicio,
		        LocalDate dataFim,
		        SituacaoConta situacao
		);
}
