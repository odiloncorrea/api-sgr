package com.cefet.apisgr.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cefet.apisgr.dtos.ExtratoResponseDTO;
import com.cefet.apisgr.dtos.GraficoResponseDTO;
import com.cefet.apisgr.dtos.SaldoResponseDTO;
import com.cefet.apisgr.entities.Rateio;
import com.cefet.apisgr.entities.enums.SituacaoRateio;

public interface RateioRepository extends JpaRepository<Rateio, Long>{
	List<Rateio> findByContaId(Long contaId);
    void deleteByContaId(Long contaId);
    
    @Query("""
    	    SELECT new com.cefet.apisgr.dtos.ExtratoResponseDTO(
    	        r.id,
    	        c.tipo.descricao,
    	        r.valor,
    	        c.vencimento,
    	        r.situacao,
    	        m.id,
    	        m.nome,
    	        c.id,
    	        c.descricao
    	    )
    	    FROM Rateio r
    	    JOIN r.conta c
    	    JOIN r.morador m
    	    WHERE (:dataInicio IS NULL OR c.vencimento >= :dataInicio)
    	      AND (:dataFim IS NULL OR c.vencimento <= :dataFim)
    	      AND (:situacao IS NULL OR r.situacao = :situacao)
    	      AND (:moradorId IS NULL OR m.id = :moradorId)
    	    ORDER BY c.vencimento, m.nome
    	""")
    	List<ExtratoResponseDTO> consultarExtrato(
    	        LocalDate dataInicio,
    	        LocalDate dataFim,
    	        SituacaoRateio situacao,
    	        Long moradorId
    	);
    
    @Query("""
    	    SELECT new com.cefet.apisgr.dtos.SaldoResponseDTO(
    	        m.id,
    	        m.nome,
    	        COALESCE(SUM(r.valor), 0)
    	    )
    	    FROM Rateio r
    	    JOIN r.morador m
    	    WHERE r.situacao = :situacao
    	      AND (:moradorId IS NULL OR m.id = :moradorId)
    	    GROUP BY m.id, m.nome
    	    ORDER BY m.nome
    	""")
    	List<SaldoResponseDTO> consultarSaldo(
    	        Long moradorId,
    	        SituacaoRateio situacao
    	);
    
    @Query("""
    	    SELECT new com.cefet.apisgr.dtos.GraficoResponseDTO(
    	        c.tipo.descricao,
    	        SUM(r.valor)
    	    )
    	    FROM Rateio r
    	    JOIN r.conta c
    	    WHERE c.vencimento BETWEEN :dataInicio AND :dataFim
    	    GROUP BY c.tipo.descricao
    	    ORDER BY c.tipo.descricao
    	""")
    	List<GraficoResponseDTO> consultarGastosPorTipo(
    	        LocalDate dataInicio,
    	        LocalDate dataFim
    	);

    	@Query("""
    	    SELECT new com.cefet.apisgr.dtos.GraficoResponseDTO(
    	        m.nome,
    	        SUM(r.valor)
    	    )
    	    FROM Rateio r
    	    JOIN r.morador m
    	    JOIN r.conta c
    	    WHERE c.vencimento BETWEEN :dataInicio AND :dataFim
    	    GROUP BY m.nome
    	    ORDER BY m.nome
    	""")
    	List<GraficoResponseDTO> consultarGastosPorMorador(
    	        LocalDate dataInicio,
    	        LocalDate dataFim
    	);
    	
    	
}
