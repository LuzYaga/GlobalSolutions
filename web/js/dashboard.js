document.addEventListener('DOMContentLoaded', () => {
    // 1. Atualizar informações do Usuário logado
    const email = localStorage.getItem('agrosat_login') || 'produtor.ead@fiap.com.br';
    const userNameElement = document.getElementById('userName');
    if (userNameElement) {
        // Pega a parte antes do @ e capitaliza
        const namePart = email.split('@')[0];
        const formattedName = namePart.charAt(0).toUpperCase() + namePart.slice(1).replace('.', ' ');
        userNameElement.textContent = formattedName;
    }

    // 2. Preencher cards estatísticos
    document.getElementById('statFarms').textContent = indicadores.fazendasMonitoradas;
    document.getElementById('statAlerts').textContent = indicadores.alertasAtivos;
    document.getElementById('statProductivity').textContent = indicadores.produtividadeMedia;
    document.getElementById('statArea').textContent = indicadores.areaMonitorada;

    // 3. Preencher tabela de fazendas
    const tbody = document.getElementById('farmsTableBody');
    tbody.innerHTML = '';
    
    fazendas.forEach(f => {
        let badgeClass = 'badge-green';
        if (f.status === 'Atenção') badgeClass = 'badge-yellow';
        if (f.status === 'Risco Elevado') badgeClass = 'badge-red';

        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td><strong>${f.nome}</strong></td>
            <td>${f.localizacao}</td>
            <td>${f.areaTotal} ha</td>
            <td><span class="badge ${badgeClass}">${f.status}</span></td>
            <td><a href="monitoramento.html?id=${f.id}" class="badge badge-blue" style="text-decoration: none;">Ver Mapa</a></td>
        `;
        tbody.appendChild(tr);
    });

    // 4. Preencher Alertas Recentes (limita em 3)
    const list = document.getElementById('alertsList');
    list.innerHTML = '';

    const ultimosAlertas = alertas.slice(0, 3);
    ultimosAlertas.forEach(a => {
        let bulletClass = 'bullet-yellow';
        if (a.nivel === 'Alto') bulletClass = 'bullet-red';
        if (a.tipo === 'Alerta Climático') bulletClass = 'bullet-blue';

        const item = document.createElement('div');
        item.className = 'alert-item';
        item.innerHTML = `
            <div class="alert-item-left">
                <div class="alert-bullet ${bulletClass}"></div>
                <div>
                    <div class="alert-title">${a.tipo} - Nível ${a.nivel}</div>
                    <div class="alert-desc">${a.descricao}</div>
                </div>
            </div>
            <div class="alert-date">${a.dataAlerta}</div>
        `;
        list.appendChild(item);
    });
});
