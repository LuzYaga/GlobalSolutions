-- ============================================================
-- AGROSAT AI - Script de Criação do Banco de Dados
-- ============================================================
-- Descrição: Sistema de monitoramento agrícola por satélite
-- simulado para auxiliar pequenos produtores rurais.
-- ============================================================

-- ============================================================
-- TABELA: PRODUTOR
-- Armazena os dados dos produtores rurais cadastrados.
-- Classe Java: Produtor.java
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
-- Classe Java: Fazenda.java
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
-- Classe Java: Plantacao.java
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
-- Classe Java: ImagemSatelite.java
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
-- TABELA: SENSOR_CLIMATICO  [NOVO - ausente no script original]
-- Armazena leituras climáticas por fazenda.
-- Relacionamento: FAZENDA (1:N) SENSOR_CLIMATICO
-- Classe Java: SensorClimatico.java
-- ============================================================
CREATE TABLE SENSOR_CLIMATICO (
    id_sensor       NUMBER        PRIMARY KEY,
    temperatura     NUMBER(5,2)   NOT NULL,
    umidade         NUMBER(5,2)   NOT NULL,
    precipitacao    NUMBER(7,2)   NOT NULL,
    data_leitura    DATE          NOT NULL,
    id_fazenda      NUMBER        NOT NULL,
    CONSTRAINT fk_sensor_fazenda
        FOREIGN KEY (id_fazenda)
        REFERENCES FAZENDA (id_fazenda)
);

-- ============================================================
-- TABELA: ALERTA
-- Armazena alertas gerados pelo sistema de monitoramento.
-- Relacionamento: FAZENDA (1:N) ALERTA
-- Classe Java: Alerta.java
-- CORRIGIDO: adicionado campo descricao (existia em Alerta.java)
-- ============================================================
CREATE TABLE ALERTA (
    id_alerta     NUMBER         PRIMARY KEY,
    tipo          VARCHAR2(50)   NOT NULL,
    nivel         VARCHAR2(20)   NOT NULL,
    descricao     VARCHAR2(300)  NOT NULL,
    data_alerta   DATE           NOT NULL,
    id_fazenda    NUMBER         NOT NULL,
    CONSTRAINT fk_alerta_fazenda
        FOREIGN KEY (id_fazenda)
        REFERENCES FAZENDA (id_fazenda)
);

-- ============================================================
-- TABELA: RELATORIO
-- Armazena relatórios agrícolas gerados por plantação.
-- Relacionamento: PLANTACAO (1:N) RELATORIO
-- Classe Java: RelatorioAgricola.java
-- CORRIGIDO: FK alterada de id_fazenda para id_plantacao
-- ============================================================
CREATE TABLE RELATORIO (
    id_relatorio    NUMBER          PRIMARY KEY,
    data_relatorio  DATE            NOT NULL,
    produtividade   NUMBER(10,2)    NOT NULL,
    recomendacao    VARCHAR2(500)   NOT NULL,
    id_plantacao    NUMBER          NOT NULL,
    CONSTRAINT fk_relatorio_plantacao
        FOREIGN KEY (id_plantacao)
        REFERENCES PLANTACAO (id_plantacao)
);

-- ============================================================
-- INSERÇÃO DE DADOS DE EXEMPLO
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

-- Imagens Satelitais
INSERT INTO IMAGEM_SATELITE (id_imagem, data_captura, indice_vegetacao, umidade, id_fazenda)
VALUES (1, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 0.75, 62.5, 1);

INSERT INTO IMAGEM_SATELITE (id_imagem, data_captura, indice_vegetacao, umidade, id_fazenda)
VALUES (2, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 0.28, 25.0, 2);

INSERT INTO IMAGEM_SATELITE (id_imagem, data_captura, indice_vegetacao, umidade, id_fazenda)
VALUES (3, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 0.18, 85.0, 3);

-- Sensores Climáticos  [NOVO]
INSERT INTO SENSOR_CLIMATICO (id_sensor, temperatura, umidade, precipitacao, data_leitura, id_fazenda)
VALUES (1, 28.5, 60.0, 15.0, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 1);

INSERT INTO SENSOR_CLIMATICO (id_sensor, temperatura, umidade, precipitacao, data_leitura, id_fazenda)
VALUES (2, 36.2, 28.0, 0.0, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 2);

INSERT INTO SENSOR_CLIMATICO (id_sensor, temperatura, umidade, precipitacao, data_leitura, id_fazenda)
VALUES (3, 22.1, 75.5, 42.3, TO_DATE('2025-06-01', 'YYYY-MM-DD'), 3);

-- Alertas  [CORRIGIDO: inclui descricao]
INSERT INTO ALERTA (id_alerta, tipo, nivel, descricao, data_alerta, id_fazenda)
VALUES (1, 'Risco de Seca', 'ALTO',
    'Solo seco detectado pela umidade satelital abaixo de 30%.',
    TO_DATE('2025-06-02', 'YYYY-MM-DD'), 2);

INSERT INTO ALERTA (id_alerta, tipo, nivel, descricao, data_alerta, id_fazenda)
VALUES (2, 'Risco de Enchente', 'MEDIO',
    'Umidade do solo saturada. Verificar sistema de drenagem.',
    TO_DATE('2025-06-02', 'YYYY-MM-DD'), 3);

INSERT INTO ALERTA (id_alerta, tipo, nivel, descricao, data_alerta, id_fazenda)
VALUES (3, 'Suspeita de Pragas', 'ALTO',
    'NDVI critico indica possivel ataque de pragas ou doencas.',
    TO_DATE('2025-06-03', 'YYYY-MM-DD'), 3);

INSERT INTO ALERTA (id_alerta, tipo, nivel, descricao, data_alerta, id_fazenda)
VALUES (4, 'Alerta Climatico', 'BAIXO',
    'Temperatura levemente elevada. Monitorar nos proximos dias.',
    TO_DATE('2025-06-03', 'YYYY-MM-DD'), 1);

-- Relatórios  [CORRIGIDO: FK agora referencia PLANTACAO]
INSERT INTO RELATORIO (id_relatorio, data_relatorio, produtividade, recomendacao, id_plantacao)
VALUES (1, TO_DATE('2025-06-05', 'YYYY-MM-DD'), 3450.00,
    'Manter manejo atual. Vegetação em estágio avançado de desenvolvimento.', 1);

INSERT INTO RELATORIO (id_relatorio, data_relatorio, produtividade, recomendacao, id_plantacao)
VALUES (2, TO_DATE('2025-06-05', 'YYYY-MM-DD'), 1950.00,
    'Aumentar irrigação em 15% devido a estresse hídrico moderado detectado.', 3);

INSERT INTO RELATORIO (id_relatorio, data_relatorio, produtividade, recomendacao, id_plantacao)
VALUES (3, TO_DATE('2025-06-05', 'YYYY-MM-DD'), 4500.75,
    'Inspecionar área para possível infestação. Índice de vegetação crítico.', 2);

COMMIT;

-- ============================================================
-- FIM DO SCRIPT
-- ============================================================