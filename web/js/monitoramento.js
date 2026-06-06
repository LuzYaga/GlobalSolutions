document.addEventListener('DOMContentLoaded', () => {
    const select = document.getElementById('farmSelect');
    const mapGrid = document.getElementById('mapGrid');
    
    // Elementos de detalhes
    const detailLoc = document.getElementById('detailLoc');
    const detailArea = document.getElementById('detailArea');
    const detailCrops = document.getElementById('detailCrops');
    const detailNdvi = document.getElementById('detailNdvi');
    const detailMoisture = document.getElementById('detailMoisture');
    const detailStatus = document.getElementById('detailStatus');
    const mapTitle = document.getElementById('mapTitle');

    // 1. Popular dropdown
    select.innerHTML = '';
    fazendas.forEach(f => {
        const opt = document.createElement('option');
        opt.value = f.id;
        opt.textContent = f.nome;
        select.appendChild(opt);
    });

    // 2. Tratar fazenda selecionada via query string (se houver)
    const urlParams = new URLSearchParams(window.location.search);
    const farmIdParam = urlParams.get('id');
    if (farmIdParam) {
        select.value = farmIdParam;
    }

    // 3. Atualizar dados da fazenda
    function atualizarFazenda(id) {
        const farm = fazendas.find(f => f.id === parseInt(id));
        if (!farm) return;

        // Atualizar textos cadastrais
        mapTitle.textContent = `Vetorização: ${farm.nome}`;
        detailLoc.textContent = farm.localizacao;
        detailArea.textContent = `${farm.areaTotal} Hectares`;
        
        // Filtra plantações da fazenda
        const crops = plantacoes.filter(p => p.idFazenda === farm.id);
        if (crops.length === 0) {
            detailCrops.textContent = "Nenhuma plantação ativa";
            detailNdvi.textContent = "N/A";
            detailMoisture.textContent = "N/A";
        } else {
            const cropNames = crops.map(p => p.cultura).join(', ');
            detailCrops.textContent = cropNames;

            // Calcula médias
            let somaNdvi = 0;
            let somaUmidade = 0;
            crops.forEach(c => {
                somaNdvi += c.ndvi;
                somaUmidade += c.umidade;
            });
            const mediaNdvi = somaNdvi / crops.length;
            const mediaUmidade = somaMoisture = somaUmidade / crops.length;

            detailNdvi.textContent = mediaNdvi.toFixed(2);
            detailMoisture.textContent = `${mediaUmidade.toFixed(1)}%`;
        }

        // Status
        let statusBadge = `<span class="badge badge-green">${farm.status}</span>`;
        if (farm.status === 'Atenção') statusBadge = `<span class="badge badge-yellow">${farm.status}</span>`;
        if (farm.status === 'Risco Elevado') statusBadge = `<span class="badge badge-red">${farm.status}</span>`;
        detailStatus.innerHTML = statusBadge;

        // Renderizar Mapa de Grade Simulando NDVI de satélite
        renderizarMapa(farm.id);
    }

    // Função para desenhar a grade do mapa
    function renderizarMapa(farmId) {
        mapGrid.innerHTML = '';
        
        // 48 células no total (8x6)
        const totalCells = 48;
        
        // Determina a proporção de cores baseado no ID da fazenda para simular diferentes condições reais
        // Fazenda 1: Majoritariamente verde (Saudável)
        // Fazenda 2: Amarela e verde (Atenção)
        // Fazenda 3: Vermelha e amarela (Risco)
        
        for (let i = 1; i <= totalCells; i++) {
            const cell = document.createElement('div');
            cell.className = 'map-cell';
            
            let status = 'Saudável';
            let ndviVal = 0.7 + (Math.random() * 0.25);
            let cellClass = 'zone-healthy';

            if (farmId === 1) {
                // Majoritariamente saudável, poucas áreas de atenção
                if (i % 9 === 0) {
                    status = 'Atenção (Estresse leve)';
                    ndviVal = 0.45;
                    cellClass = 'zone-attention';
                }
            } else if (farmId === 2) {
                // Atenção e seca moderada
                if (i % 3 === 0) {
                    status = 'Risco (Déficit Hídrico)';
                    ndviVal = 0.22;
                    cellClass = 'zone-risk';
                } else if (i % 2 === 0) {
                    status = 'Atenção (Umidade baixa)';
                    ndviVal = 0.42;
                    cellClass = 'zone-attention';
                }
            } else if (farmId === 3) {
                // Risco elevado (infestação de pragas e encharcamento simulado)
                if (i % 2 === 0) {
                    status = 'Risco (Queda Severa Vigor)';
                    ndviVal = 0.14;
                    cellClass = 'zone-risk';
                } else if (i % 5 === 0) {
                    status = 'Atenção';
                    ndviVal = 0.48;
                    cellClass = 'zone-attention';
                }
            }

            cell.classList.add(cellClass);
            cell.setAttribute('data-tooltip', `Zona ${i} | NDVI: ${ndviVal.toFixed(2)} | Status: ${status}`);
            
            mapGrid.appendChild(cell);
        }
    }

    // Escuta mudanças de fazenda
    select.addEventListener('change', (e) => {
        atualizarFazenda(e.target.value);
    });

    // Inicialização da primeira fazenda
    atualizarFazenda(select.value);
});
