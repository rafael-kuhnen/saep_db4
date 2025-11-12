-- ========================================
-- DADOS INICIAIS - SAEP 2025
-- ========================================

-- USUÁRIO PADRÃO (senha: adm)
INSERT INTO usuario (nome, email, senha)
VALUES ('Administrador', 'admin@saep.com',
        '$2a$10$0omgADneSOBADv2v0OTXk.PHuUwiIjT12HoTalmHzZstKUW93LHJ2');

INSERT INTO usuario (nome, email, senha)
VALUES ('Colaborador', 'colab@saep.com',
        '$2a$10$0omgADneSOBADv2v0OTXk.PHuUwiIjT12HoTalmHzZstKUW93LHJ2');

INSERT INTO usuario (nome, email, senha)
VALUES ('Karize', 'karize@saep.com', 
        '$2a$10$fNoNFpC2OkJlSM.bteD.sOHC9126CS8Wdr7BZdjRhb8NMn1YRZSAG');

-- TIPOS PADRÃO
INSERT INTO tipo (descricao) VALUES
('Corante'),
('Alvejante'),
('Auxiliar'),
('Não definido');

-- PRODUTOS INICIAIS
INSERT INTO produto (descproduto, unidmedida, estoqueatual, estoqueminimo, idtipo)
VALUES
('Corante Azul', 'L', 10, 3, 1),
('Alvejante Branco', 'L', 25, 5, 2),
('Produto Auxiliar X', 'kg', 15, 4, 3);

-- MOVIMENTOS INICIAIS
INSERT INTO movimento (datahoramovto, qtdmovto, tipomovto, idproduto, idusuario)
VALUES
(NOW(), 5.00, 'E', 1, 1),
(NOW(), 2.00, 'S', 2, 1),
(NOW(), 3.00, 'E', 3, 1);
