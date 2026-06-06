// Dados Simulados do AgroSat AI
// Compartilhados entre as telas do protótipo web

const fazendas = [
    { id: 1, nome: "Fazenda Esperança", localizacao: "Ribeirão Preto - SP", areaTotal: 150.5, status: "Saudável" },
    { id: 2, nome: "Sítio Boa Vista", localizacao: "Campinas - SP", areaTotal: 45.0, status: "Atenção" },
    { id: 3, nome: "Fazenda Sol Nascente", localizacao: "Piracicaba - SP", areaTotal: 200.0, status: "Risco Elevado" }
];

const plantacoes = [
    { id: 1, cultura: "Soja", area: 80.0, dataPlantio: "15/02/2025", ndvi: 0.78, umidade: 65.0, idFazenda: 1 },
    { id: 2, cultura: "Milho", area: 50.0, dataPlantio: "01/03/2025", ndvi: 0.72, umidade: 60.0, idFazenda: 1 },
    { id: 3, cultura: "Café", area: 30.0, dataPlantio: "10/01/2025", ndvi: 0.28, umidade: 28.0, idFazenda: 2 },
    { id: 4, cultura: "Cana-de-Açúcar", area: 120.0, dataPlantio: "01/04/2025", ndvi: 0.55, umidade: 52.0, idFazenda: 3 },
    { id: 5, cultura: "Algodão", area: 60.0, dataPlantio: "20/03/2025", ndvi: 0.15, umidade: 85.0, idFazenda: 3 }
];

const alertas = [
    { id: 1, tipo: "Risco de Seca", nivel: "Alto", descricao: "Solo com umidade crítica de 28% no Sítio Boa Vista.", dataAlerta: "05/06/2025", idFazenda: 2 },
    { id: 2, tipo: "Risco de Enchente", nivel: "Médio", descricao: "Umidade do solo saturada em 85% na Fazenda Sol Nascente.", dataAlerta: "06/06/2025", idFazenda: 3 },
    { id: 3, tipo: "Suspeita de Pragas", nivel: "Alto", descricao: "Índice de vegetação crítico (NDVI = 0.15) na cultura de Algodão.", dataAlerta: "06/06/2025", idFazenda: 3 },
    { id: 4, tipo: "Alerta Climático", nivel: "Baixo", descricao: "Temperatura de 36.5°C registrada na Fazenda Esperança.", dataAlerta: "04/06/2025", idFazenda: 1 }
];

const sensores = [
    { id: 1, temperatura: 28.5, umidade: 65.0, precipitacao: 15.0, dataLeitura: "06/06/2025", idFazenda: 1 },
    { id: 2, temperatura: 36.5, umidade: 28.0, precipitacao: 0.0, dataLeitura: "06/06/2025", idFazenda: 2 },
    { id: 3, temperatura: 24.2, umidade: 85.0, precipitacao: 85.0, dataLeitura: "06/06/2025", idFazenda: 3 }
];

const relatorios = [
    { id: 1, dataRelatorio: "05/06/2025", produtividade: 3200.5, recomendacao: "Manter irrigação atual. Monitorar índice de vegetação semanalmente.", idFazenda: 1 },
    { id: 2, dataRelatorio: "05/06/2025", produtividade: 1800.0, recomendacao: "Aumentar irrigação em 20%. Risco de seca identificado.", idFazenda: 2 },
    { id: 3, dataRelatorio: "06/06/2025", produtividade: 4500.7, recomendacao: "Inspecionar área para possível infestação. Índice de vegetação crítico.", idFazenda: 3 }
];

const recomendacoes = [
    {
        id: 1,
        prioridade: "Alta",
        situacao: "Queda brusca de NDVI (0.15) na cultura de Algodão (Fazenda Sol Nascente)",
        recomendacaoText: "Possível infestação de lagartas ou doença foliar. Recomenda-se realizar inspeção em campo imediatamente e planejar aplicação localizada de defensivos biológicos para conter o foco.",
        fazendaNome: "Fazenda Sol Nascente",
        date: "06/06/2025",
        icon: "[Pragas]"
    },
    {
        id: 2,
        prioridade: "Alta",
        situacao: "Déficit Hídrico no solo (Umidade 28%) detectado no Sítio Boa Vista",
        recomendacaoText: "Ativar o sistema de irrigação com incremento de 20% no volume diário. Recomenda-se aplicar cobertura morta (mulching) nas entrelinhas para reduzir a evaporação do solo.",
        fazendaNome: "Sítio Boa Vista",
        date: "05/06/2025",
        icon: "[Seca]"
    },
    {
        id: 3,
        prioridade: "Média",
        situacao: "Temperatura ambiente de 36.5°C registrada na Fazenda Esperança",
        recomendacaoText: "Risco de estresse térmico na cultura de Soja. Ajustar os turnos de irrigação para o período noturno ou início da manhã, minimizando perdas por evapotranspiração.",
        fazendaNome: "Fazenda Esperança",
        date: "04/06/2025",
        icon: "[Clima]"
    },
    {
        id: 4,
        prioridade: "Média",
        situacao: "Excesso de umidade (85%) na Fazenda Sol Nascente",
        recomendacaoText: "Risco de saturação radicular e proliferação de fungos. Desligar sistemas de irrigação artificial temporariamente e inspecionar canais de drenagem da lavoura.",
        fazendaNome: "Fazenda Sol Nascente",
        date: "06/06/2025",
        icon: "[Solo]"
    },
    {
        id: 5,
        prioridade: "Baixa",
        situacao: "Condições ideais de vigor vegetativo (NDVI = 0.78) na Fazenda Esperança",
        recomendacaoText: "Excelente estado de saúde foliar. Manter o manejo atual e planejar a adubação de cobertura programada para o fim desta semana.",
        fazendaNome: "Fazenda Esperança",
        date: "06/06/2025",
        icon: "[Ok]"
    }
];

const indicadores = {
    fazendasMonitoradas: fazendas.length,
    alertasAtivos: alertas.length,
    produtividadeMedia: "3.167 kg/ha",
    areaMonitorada: "395.5 ha"
};

// Funções para manipulação local (LocalStorage para simular persistência básica)
function obterDados() {
    if (!localStorage.getItem('agrosat_login')) {
        // Redireciona para login se não estiver autenticado (exceto na index.html)
        const path = window.location.pathname;
        if (!path.endsWith('index.html') && path !== '/' && path !== '') {
            window.location.href = 'index.html';
        }
    }
}
obterDados();
