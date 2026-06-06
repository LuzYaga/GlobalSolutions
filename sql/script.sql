-- ============================================================
-- AGROSAT AI - Script de Criação do Banco de Dados
-- Global Solutions 2025 - FIAP
-- Engenharia de Software - 3º Semestre
-- ============================================================
-- Descrição: Sistema de monitoramento agrícola por satélite
-- simulado para auxiliar pequenos produtores rurais.
-- ============================================================

-- ============================================================
-- TABELA: PRODUTOR
-- Armazena os dados dos produtores rurais cadastrados.
-- ============================================================
CREATE TABLE PRODUTOR (
    id_produtor   NUMBER        PRIMARY KEY,
    nome          VARCHAR2(100) NOT NULL,
    telefone      VARCHAR2(20)  NOT NULL,
    email         VARCHAR2(100) NOT NULL
);

-- ============================================================
-- TABELA: FAZENDA
-- Armazena as fazendas vinculadas a um produtor.
-- Relacionamento: PRODUTOR (1:N) FAZENDA
-- ============================================================
CREATE TABLE FAZENDA (
    id_fazenda    NUMBER         PRIMARY KEY,
    nome          VARCHAR2(100)  NOT NULL,
    localizacao   VARCHAR2(200)  NOT NULL,
    area_total    NUMBER(10,2)   NOT NULL,
    id_produtor   NUMBER         NOT NULL,
    CONSTRAINT fk_fazenda_produtor
        FOREIGN KEY (id_produtor)
        REFERENCES PRODUTOR (id_produtor)
);

-- ============================================================
-- TABELA: PLANTACAO
-- Armazena as plantações dentro de uma fazenda.
-- Relacionamento: FAZENDA (1:N) PLANTACAO
-- ============================================================
CREATE TABLE PLANTACAO (
    id_plantacao  NUMBER         PRIMARY KEY,
    cultura       VARCHAR2(100)  NOT NULL,
    area          NUMBER(10,2)   NOT NULL,
    data_plantio  DATE           NOT NULL,
    id_fazenda    NUMBER         NOT NULL,
    CONSTRAINT fk_plantacao_fazenda
        FOREIGN KEY (id_fazenda)
        REFERENCES FAZENDA (id_fazenda)
);

-- ============================================================
-- TABELA: IMAGEM_SATELITE
-- Armazena as imagens satelitais simuladas capturadas.
-- Relacionamento: FAZENDA (1:N) IMAGEM_SATELITE
-- ============================================================
CREATE TABLE IMAGEM_SATELITE (
    id_imagem         NUMBER       PRIMARY KEY,
    data_captura      DATE         NOT NULL,
    indice_vegetacao  NUMBER(5,4)  NOT NULL,
    umidade           NUMBER(5,2)  NOT NULL,
    id_fazenda        NUMBER       NOT NULL,
    CONSTRAINT fk_imagem_fazenda
        FOREIGN KEY (id_fazenda)
        REFERENCES FAZENDA (id_fazenda)
);

-- ============================================================
-- TABELA: ALERTA
-- Armazena alertas gerados pelo sistema de monitoramento.
-- Relacionamento: FAZENDA (1:N) ALERTA
-- ============================================================
CREATE TABLE ALERTA (
    id_alerta     NUMBER         PRIMARY KEY,
    tipo          VARCHAR2(50)   NOT NULL,
    nivel         VARCHAR2(20)   NOT NULL,
    data_alerta   DATE           NOT NULL,
    id_fazenda    NUMBER         NOT NULL,
    CONSTRAINT fk_alerta_fazenda
        FOREIGN KEY (id_fazenda)
        REFERENCES FAZENDA (id_fazenda)
);

-- ============================================================
-- TABELA: RELATORIO
-- Armazena relatórios agrícolas gerados para as fazendas.
-- Relacionamento: FAZENDA (1:N) RELATORIO
-- ============================================================
CREATE TABLE RELATORIO (
    id_relatorio    NUMBER          PRIMARY KEY,
    data_relatorio  DATE            NOT NULL,
    produtividade   NUMBER(10,2)    NOT NULL,
    recomendacao    VARCHAR2(500)   NOT NULL,
    id_fazenda      NUMBER          NOT NULL,
    CONSTRAINT fk_relatorio_fazenda
        FOREIGN KEY (id_fazenda)
        REFERENCES FAZENDA (id_fazenda)
);

-- ============================================================
-- INSERÇÃO DE DADOS DE EXEMPLO (OPCIONAL)
-- ============================================================

-- Produtores
INSERT INTO PRODUTOR (id_produtor, nome, telefone, email)
VALUES (1, 'João da Silva', '(11) 99999-1111', 'joao.silva@email.com');

INSERT INTO PRODUTOR (id_produtor, nome, telefone, email)
VALUES (2, 'Maria Oliveira', '(11) 99999-2222', 'maria.oliveira@email.com');

INSERT INTO PRODUTOR (id_produtor, nome, telefone, email)
VALUES (3, 'Carlos Santos', '(19) 99888-3333', 'carlos.santos@email.com');

-- Fazendas
INSERT INTO FAZENDA (id_fazenda, nome, localizacao, area_total, id_produtor)
VALUES (1, 'Fazenda Esperança', 'Ribeirão Preto - SP', 150.50, 1);

INSERT INTO FAZENDA (id_fazenda, nome, localizacao, area_total, id_produtor)
VALUES (2, 'Sítio Boa Vista', 'Campinas - SP', 45.00, 2);

INSERT INTO FAZENDA (id_fazenda, nome, localizacao, area_total, id_produtor)
VALUES (3, 'Fazenda Sol Nascente', 'Piracicaba - SP', 200.00, 3);

-- Plantações
INSERT INTO PLANTACAO (id_plantacao, cultura, area, data_plantio, id_fazenda)
VALUES (1, 'Soja', 80.00, TO_DATE('2025-02-15', 'YYYY-MM-DD'), 1);

INSERT INTO PLANTACAO (id_plantacao, cultura, area, data_plantio, id_fazenda)
VALUES (2, 'Milho', 50.00, TO_DATE('2025-03-01', 'YYYY-MM-DD'), 1);

INSERT INTO PLANTACAO (id_plantacao, cultura, area, data_plantio, id_fazenda)
VALUES (3, 'Café', 30.00, TO_DATE('2025-01-10', 'YYYY-MM-DD'), 2);

INSERT INTO PLANTACAO (id_plantacao, cultura, area, data_plantio, id_fazenda)
VALUES (4, 'Cana-de-Açúcar', 120.00, TO_DATE('2025-04-01', 'YYYY-MM-DD'), 3);

INSERT INTO PLANTACAO (id_plantacao, cultura, area, data_plantio, id_fazenda)
VALUES (5, 'Algodão', 60.00, TO_DATE('2025-03-20', 'YYYY-MM-DD'), 3);

-- Imagens Satelitais Simuladas
INSERT INTO IMAGEM_SATELITE (id_imagem, data_captura, indice_vegetacao, umidade, id_fazenda)
VALUES (1, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 0.75, 62.5, 1);

INSERT INTO IMAGEM_SATELITE (id_imagem, data_captura, indice_vegetacao, umidade, id_fazenda)
VALUES (2, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 0.45, 28.0, 2);

INSERT INTO IMAGEM_SATELITE (id_imagem, data_captura, indice_vegetacao, umidade, id_fazenda)
VALUES (3, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 0.18, 85.0, 3);

-- Alertas
INSERT INTO ALERTA (id_alerta, tipo, nivel, data_alerta, id_fazenda)
VALUES (1, 'Risco de Seca', 'ALTO', TO_DATE('2025-06-02', 'YYYY-MM-DD'), 2);

INSERT INTO ALERTA (id_alerta, tipo, nivel, data_alerta, id_fazenda)
VALUES (2, 'Risco de Enchente', 'MEDIO', TO_DATE('2025-06-02', 'YYYY-MM-DD'), 3);

INSERT INTO ALERTA (id_alerta, tipo, nivel, data_alerta, id_fazenda)
VALUES (3, 'Suspeita de Pragas', 'ALTO', TO_DATE('2025-06-03', 'YYYY-MM-DD'), 3);

INSERT INTO ALERTA (id_alerta, tipo, nivel, data_alerta, id_fazenda)
VALUES (4, 'Alerta Climático', 'BAIXO', TO_DATE('2025-06-03', 'YYYY-MM-DD'), 1);

-- Relatórios
INSERT INTO RELATORIO (id_relatorio, data_relatorio, produtividade, recomendacao, id_fazenda)
VALUES (1, TO_DATE('2025-06-05', 'YYYY-MM-DD'), 3200.50,
    'Manter irrigação atual. Monitorar índice de vegetação semanalmente.', 1);

INSERT INTO RELATORIO (id_relatorio, data_relatorio, produtividade, recomendacao, id_fazenda)
VALUES (2, TO_DATE('2025-06-05', 'YYYY-MM-DD'), 1800.00,
    'Aumentar irrigação em 20%. Risco de seca identificado.', 2);

INSERT INTO RELATORIO (id_relatorio, data_relatorio, produtividade, recomendacao, id_fazenda)
VALUES (3, TO_DATE('2025-06-05', 'YYYY-MM-DD'), 4500.75,
    'Inspecionar área para possível infestação. Índice de vegetação crítico.', 3);

COMMIT;

-- ============================================================
-- FIM DO SCRIPT
-- ============================================================
