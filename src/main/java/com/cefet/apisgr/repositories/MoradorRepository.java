package com.cefet.apisgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.apisgr.entities.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long>{
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
