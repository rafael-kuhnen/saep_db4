-- ========================================
-- SCHEMA - SAEP 2025
-- ========================================

CREATE DATABASE IF NOT EXISTS saep_db1 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE saep_db1;

-- ========================================
-- TABELA USUÁRIO
-- ========================================
CREATE TABLE IF NOT EXISTS usuario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  senha VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- TABELA TIPO
-- ========================================
CREATE TABLE IF NOT EXISTS tipo (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  descricao VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- TABELA PRODUTO
-- ========================================
CREATE TABLE IF NOT EXISTS produto (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  descproduto VARCHAR(255),
  unidmedida VARCHAR(50),
  estoqueatual DECIMAL(10,2),
  estoqueminimo DECIMAL(10,2),
  idtipo BIGINT,
  CONSTRAINT fk_produto_tipo FOREIGN KEY (idtipo)
    REFERENCES tipo(id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- TABELA MOVIMENTO
-- ========================================
CREATE TABLE IF NOT EXISTS movimento (
  idtransacao BIGINT AUTO_INCREMENT PRIMARY KEY,
  datahoramovto DATETIME(6),
  qtdmovto DECIMAL(10,2),
  tipomovto CHAR(1) NOT NULL,
  idproduto BIGINT,
  idusuario BIGINT,
  CONSTRAINT fk_movimento_produto FOREIGN KEY (idproduto)
    REFERENCES produto(id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_movimento_usuario FOREIGN KEY (idusuario)
    REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
