package com.saep2025.saep_db1.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtransacao;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    private char tipomovto; // E=Entrada, S=Saída
    private BigDecimal qtdmovto;
    private LocalDateTime datahoramovto = LocalDateTime.now();
}
