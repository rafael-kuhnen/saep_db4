package com.saep2025.saep_db1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saep2025.saep_db1.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

List<Produto> findByDescprodutoContainingIgnoreCaseOrTipoDescricaoContainingIgnoreCase(String descproduto, String tipoDescricao);
}
