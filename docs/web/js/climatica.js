document.addEventListener('DOMContentLoaded', () => {
    const select = document.getElementById('farmSelect');
    
    // Cards
    const tempValue = document.getElementById('tempValue');
    const humidityValue = document.getElementById('humidityValue');
    const precipValue = document.getElementById('precipValue');
    const droughtValue = document.getElementById('droughtValue');

    // Gráficos
    const barChartContainer = document.getElementById('barChartContainer');
    const lineChartContainer = document.getElementById('lineChartContainer');

    // 1. Popular dropdown
    select.innerHTML = '';
    fazendas.forEach(f => {
        const opt = document.createElement('option');
        opt.value = f.id;
        opt.textContent = f.nome;
        select.appendChild(opt);
    });

    function atualizarClima(id) {
        const farmId = parseInt(id);
        const sensor = sensores.find(s => s.idFazenda === farmId);
        if (!sensor) return;

        // Atualiza cards
        tempValue.innerHTML = `${sensor.temperatura.toFixed(1)}<span class="climate-unit">°C</span>`;
        humidityValue.innerHTML = `${sensor.umidade.toFixed(1)}<span class="climate-unit">%</span>`;
        precipValue.innerHTML = `${sensor.precipitacao.toFixed(1)}<span class="climate-unit">mm</span>`;

        // Avalia risco de seca de acordo com as regras de negócio
        let risco = "Baixo";
        let riscoColor = "var(--accent-green)";
        if (sensor.umidade < 30.0) {
            risco = "ALTO";
            riscoColor = "var(--accent-red)";
        } else if (sensor.umidade < 50.0) {
            risco = "MÉDIO";
            riscoColor = "var(--accent-amber)";
        }

        droughtValue.textContent = risco;
        droughtValue.style.color = riscoColor;

        // Gera Gráfico de Barras de Temperatura (7 dias) com variação baseada na temperatura do sensor
        gerarGraficoBarras(sensor.temperatura);

        // Gera Gráfico de Linha de Umidade (7 dias)
        gerarGraficoLinha(sensor.umidade);
    }

    function gerarGraficoBarras(tempBase) {
        barChartContainer.innerHTML = '';
        const diasSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];
        
        // Variação de temperatura ao longo de 7 dias
        diasSemana.forEach((dia, index) => {
            // Gera oscilação de temp em torno do valor base
            const oscilacao = (Math.sin(index) * 4) + (Math.cos(index) * 2);
            const tempDia = tempBase + oscilacao;
            
            // Calcula a altura da barra em porcentagem (limite máximo de 45°C como 100%)
            const alturaPct = Math.max(10, Math.min(100, (tempDia / 45) * 100));

            const wrapper = document.createElement('div');
            wrapper.className = 'bar-wrapper';
            wrapper.innerHTML = `
                <div class="bar-column" style="height: ${alturaPct}%;">
                    <div class="bar-tooltip">${tempDia.toFixed(1)}°C</div>
                </div>
                <div class="bar-label">${dia}</div>
            `;
            barChartContainer.appendChild(wrapper);
        });
    }

    function gerarGraficoLinha(umidadeBase) {
        lineChartContainer.innerHTML = '';
        const diasSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];

        diasSemana.forEach((dia, index) => {
            // Gera oscilação da umidade
            const oscilacao = (Math.cos(index * 1.5) * 12) + (Math.sin(index) * 5);
            const umidadeDia = Math.max(5, Math.min(100, umidadeBase + oscilacao));

            const row = document.createElement('div');
            row.className = 'line-chart-row';
            row.innerHTML = `
                <div class="line-chart-day">${dia}</div>
                <div class="line-chart-bar-bg">
                    <div class="line-chart-bar-fill" style="width: ${umidadeDia}%;"></div>
                </div>
                <div class="line-chart-val">${umidadeDia.toFixed(0)}%</div>
            `;
            lineChartContainer.appendChild(row);
        });
    }

    select.addEventListener('change', (e) => {
        atualizarClima(e.target.value);
    });

    // Inicialização da primeira fazenda
    atualizarClima(select.value);
});
