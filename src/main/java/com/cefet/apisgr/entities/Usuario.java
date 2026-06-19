package com.cefet.apisgr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       
    @Column(nullable = false, unique = true)
    private String login;
    
    @Column(nullable = false)
    private String senha;  
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;      
    
    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;    
}
