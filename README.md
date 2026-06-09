# AgroSat AI - Documentação Completa do Projeto

> **"Seu satélite virtual para proteger pequenas propriedades rurais"**

---

## 1. Descrição Geral do Projeto

O **AgroSat AI** é uma plataforma acadêmica que simula o uso de dados espaciais e monitoramento por satélite para auxiliar pequenos produtores rurais na tomada de decisões sobre suas lavouras.

A plataforma permite que o produtor cadastre informações sobre sua fazenda, cultura plantada, área cultivada e data de plantio. Com base nesses dados, o sistema gera **análises simuladas** inspiradas em tecnologias reais de observação da Terra, incluindo:

- **Índice de Vegetação (NDVI simulado)**: indica a saúde da vegetação
- **Umidade do Solo**: nível de água disponível no solo
- **Risco de Seca e Enchente**: baseado em dados climáticos simulados
- **Probabilidade de Pragas**: correlação com queda no vigor vegetal
- **Produtividade Estimada**: projeção baseada em condições atuais
- **Alertas Preventivos**: notificações automáticas de riscos

O diferencial do sistema é a **geração automática de recomendações** baseadas em regras de negócio que simulam uma inteligência artificial agrícola, orientando o pequeno produtor sobre ações preventivas e corretivas.

---

## 2. Justificativa da Relação com a Indústria Espacial

### 2.1 Observação da Terra

O AgroSat AI se inspira diretamente nos programas de observação terrestre mantidos por agências espaciais como a **NASA** (programa Landsat e MODIS) e a **ESA** (programa Copernicus/Sentinel). Esses programas utilizam satélites em órbita para capturar imagens multiespectrais da superfície terrestre, permitindo a análise de cobertura vegetal, uso do solo e condições ambientais.

No sistema, a classe `ImagemSatelite` simula os dados que seriam obtidos por esses satélites, gerando valores de índice de vegetação e umidade que representam as informações capturadas por sensores orbitais.

### 2.2 Sensoriamento Remoto

O sensoriamento remoto é a técnica de obter informações sobre objetos e áreas à distância, sem contato físico direto. Na agricultura, essa técnica é fundamental para:

- Monitorar o estado de saúde das plantações em grandes áreas
- Detectar estresse hídrico antes que seja visível a olho nu
- Identificar focos de pragas e doenças
- Estimar produtividade agrícola

O AgroSat AI simula esses processos através da classe `SensorClimatico` e do serviço `AnaliseService`, que processam dados simulados de forma análoga ao processamento de dados de sensoriamento remoto.

### 2.3 NDVI - Índice de Vegetação por Diferença Normalizada

O NDVI (Normalized Difference Vegetation Index) é o índice mais utilizado mundialmente para avaliar a saúde da vegetação a partir de dados satelitais. Ele é calculado a partir das bandas espectrais vermelho e infravermelho próximo:

```
NDVI = (NIR - RED) / (NIR + RED)
```

Valores de referência:
- **0.0 a 0.2**: Solo exposto ou vegetação morta
- **0.2 a 0.4**: Vegetação esparsa ou estressada
- **0.4 a 0.6**: Vegetação moderada
- **0.6 a 0.8**: Vegetação densa e saudável
- **0.8 a 1.0**: Vegetação muito densa e vigorosa

No AgroSat AI, o atributo `indiceVegetacao` simula o NDVI para gerar análises e recomendações.

### 2.4 Agricultura de Precisão

A agricultura de precisão é uma abordagem de manejo agrícola que utiliza tecnologias de informação, dados de satélites, GPS, sensores e análise de dados para otimizar o uso de insumos e maximizar a produtividade. O AgroSat AI implementa conceitos dessa abordagem ao:

- Segmentar a fazenda em zonas com diferentes estados de saúde
- Recomendar ações específicas para cada situação identificada
- Monitorar continuamente os indicadores da propriedade

### 2.5 Tecnologias de Referência

| Tecnologia Real | Equivalente no AgroSat AI |
|---|---|
| Satélite Landsat (NASA) | Classe `ImagemSatelite` |
| Sensor MODIS | Classe `SensorClimatico` |
| Índice NDVI | Atributo `indiceVegetacao` |
| Sistema Copernicus (ESA) | `AnaliseService` |
| Estação meteorológica | Dados de temperatura e umidade |
| IA para agricultura | Regras de negócio simuladas |

---

## 3. Estrutura de Pacotes Java

```
br.com.agrosatai
│
├── model/
│   ├── Produtor.java
│   ├── Fazenda.java
│   ├── Plantacao.java
│   ├── ImagemSatelite.java
│   ├── SensorClimatico.java
│   ├── AnaliseIA.java
│   ├── RelatorioAgricola.java
│   └── Alerta.java
│
├── service/
│   ├── ProdutorService.java
│   ├── FazendaService.java
│   ├── PlantacaoService.java
│   ├── AnaliseService.java
│   └── RelatorioService.java
│
├── menu/
│   ├── MenuPrincipal.java
│   ├── MenuProdutor.java
│   ├── MenuFazenda.java
│   └── MenuRelatorios.java
│
├── util/
│   └── GeradorDados.java
│
└── Main.java
```

---

## 4. Casos de Uso

### 4.1 Diagrama de Casos de Uso (Textual)

**Ator Principal**: Produtor Rural

#### Cadastro
| Caso de Uso | Descrição |
|---|---|
| UC01 - Cadastrar Produtor | O produtor informa nome, telefone e email |
| UC02 - Cadastrar Fazenda | O produtor registra uma fazenda com nome, localização e área |
| UC03 - Cadastrar Plantação | O produtor registra uma plantação com cultura, área e data de plantio |

#### Consulta
| Caso de Uso | Descrição |
|---|---|
| UC04 - Listar Produtores | O sistema exibe todos os produtores cadastrados |
| UC05 - Listar Fazendas | O sistema exibe todas as fazendas cadastradas |
| UC06 - Listar Plantações | O sistema exibe todas as plantações cadastradas |

#### Busca
| Caso de Uso | Descrição |
|---|---|
| UC07 - Buscar Produtor | O sistema localiza um produtor pelo ID |
| UC08 - Buscar Fazenda | O sistema localiza uma fazenda pelo ID |
| UC09 - Buscar Plantação | O sistema localiza uma plantação pelo ID |

#### Atualização
| Caso de Uso | Descrição |
|---|---|
| UC10 - Atualizar Produtor | O produtor altera seus dados cadastrais |
| UC11 - Atualizar Fazenda | O produtor altera dados da fazenda |
| UC12 - Atualizar Plantação | O produtor altera dados da plantação |

#### Monitoramento
| Caso de Uso | Descrição |
|---|---|
| UC13 - Gerar Análise Satelital | O sistema gera dados simulados de satélite para uma fazenda |
| UC14 - Gerar Risco Climático | O sistema avalia riscos climáticos com base em sensores simulados |
| UC15 - Gerar Relatório Agrícola | O sistema produz um relatório com produtividade e recomendações |

#### Inteligência Artificial Simulada
| Caso de Uso | Descrição |
|---|---|
| UC16 - Gerar Recomendação IA | O sistema analisa indicadores e gera recomendações automáticas |

---

## 5. UML - Diagrama de Classes (Textual)

```
╔══════════════════════════════════════╗
║            PRODUTOR                  ║
╠══════════════════════════════════════╣
║ - id: int                            ║
║ - nome: String                       ║
║ - telefone: String                   ║
║ - email: String                      ║
╠══════════════════════════════════════╣
║ + Produtor()                         ║
║ + Produtor(id, nome, tel, email)     ║
║ + getId(): int                       ║
║ + setId(id: int): void               ║
║ + getNome(): String                  ║
║ + setNome(nome: String): void        ║
║ + getTelefone(): String              ║
║ + setTelefone(tel: String): void     ║
║ + getEmail(): String                 ║
║ + setEmail(email: String): void      ║
║ + toString(): String                 ║
╚══════════════════════════════════════╝
              │
              │ 1:N
              ▼
╔══════════════════════════════════════╗
║            FAZENDA                   ║
╠══════════════════════════════════════╣
║ - id: int                            ║
║ - nome: String                       ║
║ - localizacao: String                ║
║ - areaTotal: double                  ║
║ - idProdutor: int                    ║
╠══════════════════════════════════════╣
║ + Fazenda()                          ║
║ + Fazenda(id, nome, loc, area, idP)  ║
║ + getId(): int                       ║
║ + setId(id: int): void               ║
║ + getNome(): String                  ║
║ + setNome(nome: String): void        ║
║ + getLocalizacao(): String           ║
║ + setLocalizacao(loc: String): void  ║
║ + getAreaTotal(): double             ║
║ + setAreaTotal(area: double): void   ║
║ + getIdProdutor(): int               ║
║ + setIdProdutor(id: int): void       ║
║ + toString(): String                 ║
╚══════════════════════════════════════╝
      │         │         │         │
    1:N       1:N       1:N       1:N
      ▼         ▼         ▼         ▼
╔══════════╗ ╔═══════════════╗ ╔══════════════════╗ ╔═════════╗
║PLANTACAO ║ ║IMAGEM_SATELITE║ ║ SENSOR_CLIMATICO ║ ║ ALERTA  ║
╠══════════╣ ╠═══════════════╣ ╠══════════════════╣ ╠═════════╣
║- id      ║ ║- id           ║ ║- id              ║ ║- id     ║
║- cultura ║ ║- dataCaptura  ║ ║- temperatura     ║ ║- tipo   ║
║- area    ║ ║- indiceVeg.   ║ ║- umidade         ║ ║- nivel  ║
║- dataPl. ║ ║- umidade      ║ ║- precipitacao    ║ ║- descr. ║
║- idFaz.  ║ ║- idFazenda    ║ ║- dataLeitura     ║ ║- dataAl.║
╠══════════╣ ╠═══════════════╣ ║- idFazenda       ║ ║- idFaz. ║
║+ getters ║ ║+ getters      ║ ╠══════════════════╣ ╠═════════╣
║+ setters ║ ║+ setters      ║ ║+ getters/setters ║ ║+getters ║
║+ toString║ ║+ toString     ║ ║+ toString        ║ ║+setters ║
╚══════════╝ ╚═══════════════╝ ╚══════════════════╝ ╚═════════╝
      │               │
    1:N             1:N
      ▼               ▼
╔══════════════════╗  ╔══════════════════╗
║RELATORIO_AGRICOLA║  ║   ANALISE_IA     ║
╠══════════════════╣  ╠══════════════════╣
║- id              ║  ║- id              ║
║- dataRelatorio   ║  ║- tipo            ║
║- produtividade   ║  ║- descricao       ║
║- recomendacao    ║  ║- recomendacao    ║
║- idPlantacao     ║  ║- nivel           ║
╠══════════════════╣  ║- dataAnalise     ║
║+ getters/setters ║  ║- idFazenda       ║
║+ toString        ║  ╠══════════════════╣
╚══════════════════╝  ║+ getters/setters ║
                      ║+ toString        ║
                      ╚══════════════════╝
```

### Relacionamentos

```
PRODUTOR        ---- 1:N ----> FAZENDA
FAZENDA         ---- 1:N ----> PLANTACAO
FAZENDA         ---- 1:N ----> IMAGEM_SATELITE
FAZENDA         ---- 1:N ----> SENSOR_CLIMATICO
FAZENDA         ---- 1:N ----> ALERTA
PLANTACAO       ---- 1:N ----> RELATORIO_AGRICOLA
IMAGEM_SATELITE ---- 1:N ----> ANALISE_IA
```

---

## 6. Regras de Negócio

O AgroSat AI utiliza regras de negócio simuladas para gerar alertas e recomendações automáticas. Essas regras se inspiram em critérios reais utilizados na agricultura de precisão.

### Regra 1: Índice de Vegetação Baixo (NDVI < 0.3)

| Aspecto | Detalhe |
|---|---|
| **Condição** | Índice de vegetação entre 0.2 e 0.3 |
| **Alerta Gerado** | Alerta Amarelo |
| **Diagnóstico** | Possível deficiência hídrica ou nutricional |
| **Recomendação** | Aumentar irrigação em 15%; monitorar a região afetada; verificar previsão climática |

### Regra 2: Índice de Vegetação Muito Baixo (NDVI < 0.2)

| Aspecto | Detalhe |
|---|---|
| **Condição** | Índice de vegetação abaixo de 0.2 |
| **Alerta Gerado** | Alerta Vermelho |
| **Diagnóstico** | Suspeita de infestação de pragas ou doença severa |
| **Recomendação** | Inspeção imediata do campo; verificar sinais de lagartas, fungos ou bactérias; considerar aplicação emergencial de defensivos |

### Regra 3: Umidade do Solo Abaixo de 30%

| Aspecto | Detalhe |
|---|---|
| **Condição** | Umidade do solo inferior a 30% |
| **Alerta Gerado** | Risco de Seca |
| **Diagnóstico** | Solo com déficit hídrico significativo |
| **Recomendação** | Ativar sistema de irrigação; aplicar cobertura morta (mulching); avaliar replantio de áreas comprometidas |

### Regra 4: Umidade do Solo Acima de 80%

| Aspecto | Detalhe |
|---|---|
| **Condição** | Umidade do solo superior a 80% |
| **Alerta Gerado** | Risco de Enchente |
| **Diagnóstico** | Excesso de água no solo pode causar apodrecimento de raízes |
| **Recomendação** | Verificar sistema de drenagem; suspender irrigação; monitorar previsão de chuvas |

### Regra 5: Temperatura Acima de 35°C

| Aspecto | Detalhe |
|---|---|
| **Condição** | Temperatura ambiente superior a 35°C |
| **Alerta Gerado** | Alerta Climático |
| **Diagnóstico** | Estresse térmico nas plantas |
| **Recomendação** | Irrigar nas horas mais frescas; aplicar sombreamento parcial; monitorar sinais de murcha |

### Regra Combinada: Múltiplos Indicadores

Quando mais de uma condição é detectada simultaneamente, o sistema combina os alertas e prioriza a recomendação mais urgente, gerando um relatório consolidado.

---

## 7. Script SQL

O script SQL completo está localizado em `script_corrigido.sql` e contém:

- **7 tabelas**: PRODUTOR, FAZENDA, PLANTACAO, IMAGEM_SATELITE, SENSOR_CLIMATICO, ALERTA, RELATORIO
- Todas as PRIMARY KEYs definidas
- Todas as FOREIGN KEYs com constraints nomeadas
- Dados de exemplo para demonstração

> **Alterações em relação ao modelo original:**
> - Tabela `SENSOR_CLIMATICO` adicionada (campos: `temperatura`, `umidade`, `precipitacao`, `data_leitura`, `id_fazenda`)
> - Campo `descricao` adicionado na tabela `ALERTA`
> - Tabela `RELATORIO` passou a referenciar `id_plantacao` (era `id_fazenda`)

---

## 8. Estrutura de Pastas do Projeto Web

```
web/
├── index.html              (Tela de Login)
├── dashboard.html          (Dashboard Principal)
├── monitoramento.html      (Monitoramento Satelital)
├── climatica.html          (Central Climática)
├── recomendacoes.html      (Recomendações da IA)
├── css/
│   └── style.css           (Estilos globais)
└── js/
    ├── dados.js            (Dados simulados compartilhados — alinhado com script_corrigido.sql)
    ├── login.js            (Lógica da tela de login)
    ├── dashboard.js        (Lógica do dashboard)
    ├── monitoramento.js    (Lógica do monitoramento)
    ├── climatica.js        (Lógica da central climática)
    └── recomendacoes.js    (Lógica das recomendações)
```

---

## 9. ODS Atendidas

O projeto AgroSat AI contribui diretamente para 4 Objetivos de Desenvolvimento Sustentável da ONU:

### ODS 2 - Fome Zero e Agricultura Sustentável

O AgroSat AI auxilia pequenos produtores a **aumentar a produtividade agrícola** através de:

- Monitoramento contínuo da saúde das plantações
- Alertas preventivos sobre riscos climáticos
- Recomendações para uso eficiente de água e insumos
- Redução de perdas por pragas e condições climáticas adversas

Ao empoderar pequenos agricultores com informações semelhantes às disponíveis para grandes produtores, o sistema contribui para a **segurança alimentar** e para a **sustentabilidade da agricultura familiar**.

### ODS 8 - Trabalho Decente e Crescimento Econômico

O sistema promove o **crescimento econômico sustentável** do pequeno produtor ao:

- Reduzir custos com perdas de safra
- Otimizar o uso de recursos (água, fertilizantes, defensivos)
- Aumentar a previsibilidade da produção
- Permitir melhor planejamento financeiro

Com recomendações baseadas em dados, o produtor pode **aumentar sua renda** e **reinvestir** na propriedade, gerando um ciclo virtuoso de crescimento.

### ODS 9 - Indústria, Inovação e Infraestrutura

O AgroSat AI demonstra como a **inovação tecnológica** pode ser democratizada:

- Tecnologias antes restritas a grandes corporações são simuladas e disponibilizadas para pequenos produtores
- O uso de dados satelitais simulados representa uma **infraestrutura digital** acessível
- A integração de diferentes fontes de dados (vegetação, clima, solo) demonstra **inovação** na análise agrícola
- O projeto incentiva a adoção de tecnologia no campo

### ODS 13 - Ação Contra a Mudança Global do Clima

O sistema contribui para o enfrentamento das **mudanças climáticas** ao:

- Monitorar riscos climáticos (seca, enchente, ondas de calor)
- Alertar o produtor sobre eventos climáticos extremos com antecedência
- Recomendar práticas de adaptação às condições climáticas
- Promover o uso racional de água, reduzindo o impacto ambiental
- Incentivar a agricultura resiliente ao clima

---

## 10. Conexão com Tecnologias Espaciais

### Como Satélites Reais Monitoram a Agricultura

```
  Satélite em Órbita
       │
       │ Captura imagens multiespectrais
       ▼
  Estação Terrestre
       │
       │ Recebe e processa dados brutos
       ▼
  Centro de Processamento
       │
       │ Calcula índices (NDVI, EVI, NDWI)
       ▼
  Plataforma de Análise
       │
       │ Gera mapas, alertas e relatórios
       ▼
  Produtor Rural
       │
       │ Recebe recomendações
       ▼
  Ação no Campo
```

### Como o AgroSat AI Simula Esse Processo

```
  Gerador de Dados (GeradorDados.java)
       │
       │ Simula dados satelitais e climáticos
       ▼
  ImagemSatelite + SensorClimatico
       │
       │ Armazena dados simulados
       ▼
  AnaliseService
       │
       │ Aplica regras de negócio (simula IA)
       ▼
  AnaliseIA + Alerta + RelatorioAgricola
       │
       │ Gera recomendações automáticas
       ▼
  Produtor visualiza no console/web
       │
       │ Toma decisões informadas
       ▼
  Ação no Campo
```

### Programas Espaciais de Referência

| Programa | Agência | Aplicação |
|---|---|---|
| Landsat | NASA/USGS | Monitoramento de uso do solo e vegetação desde 1972 |
| MODIS | NASA | Dados diários de vegetação, temperatura e incêndios |
| Sentinel-2 | ESA | Imagens de alta resolução para agricultura (10m) |
| Copernicus | ESA/UE | Programa de observação da Terra mais completo do mundo |
| CBERS | INPE/China | Satélite brasileiro para monitoramento ambiental |

---

## 11. Conclusão

O projeto **AgroSat AI** demonstra de forma prática e acadêmica como tecnologias da **Indústria Espacial** podem ser adaptadas e simplificadas para gerar impacto positivo na vida de **pequenos produtores rurais**.

Mesmo utilizando **dados simulados**, o sistema reproduz o fluxo completo de uma solução real de monitoramento agrícola por satélite: desde a captura de dados (simulada), passando pelo processamento e análise, até a geração de recomendações acionáveis para o produtor.

### Contribuições do Projeto:

**Técnica**: Aplicação prática de conceitos de POO, banco de dados relacional e desenvolvimento web  
**Social**: Democratização do acesso à tecnologia agrícola para pequenos produtores  
**Ambiental**: Promoção de práticas sustentáveis e resilientes ao clima  
**Econômica**: Redução de perdas e aumento de produtividade no campo  
**Educacional**: Demonstração da relevância da Indústria Espacial para a sociedade  

O AgroSat AI é um **satélite virtual** que coloca nas mãos do pequeno produtor o poder da observação da Terra, da análise de dados e da inteligência artificial simulada - tudo isso de forma acessível, simples e eficaz.

> *"A tecnologia espacial não precisa estar no espaço para transformar vidas na Terra."*

---