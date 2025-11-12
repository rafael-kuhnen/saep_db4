package com.saep2025.saep_db1.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descproduto;

    @ManyToOne
    @JoinColumn(name = "idtipo")
    private Tipo tipo; // relação com a tabela Tipo

    private String unidmedida;
    private BigDecimal estoqueminimo;
    private BigDecimal estoqueatual;
}
