document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('recomendacoesContainer');
    const filterButtons = document.querySelectorAll('.filter-btn');

    function renderRecomendacoes(filtroPrioridade = 'todas') {
        container.innerHTML = '';
        
        // Filtra os dados
        const filtradas = filtroPrioridade === 'todas' 
            ? recomendacoes 
            : recomendacoes.filter(r => r.prioridade === filtroPrioridade);

        if (filtradas.length === 0) {
            container.innerHTML = `
                <div class="glass-card animate-fade" style="text-align: center; padding: 3rem;">
                    <h3 style="margin-top: 1rem;">Nenhuma recomendação nesta categoria</h3>
                    <p style="color: var(--text-secondary); margin-top: 0.5rem;">Sua lavoura está estável e sob controle.</p>
                </div>
            `;
            return;
        }

        filtradas.forEach(r => {
            let priorityBadgeClass = 'badge-green';
            let priorityClass = 'priority-low';

            if (r.prioridade === 'Alta') {
                priorityBadgeClass = 'badge-red';
                priorityClass = 'priority-high';
            } else if (r.prioridade === 'Média') {
                priorityBadgeClass = 'badge-yellow';
                priorityClass = 'priority-medium';
            }

            const card = document.createElement('div');
            card.className = `glass-card rec-card ${priorityClass} animate-fade`;
            card.innerHTML = `
                <div class="rec-header">
                    <div class="rec-title-group">
                        <div class="rec-title">${r.situacao}</div>
                    </div>
                    <span class="badge ${priorityBadgeClass}">Prioridade ${r.prioridade}</span>
                </div>
                <div class="rec-body">
                    ${r.recomendacaoText}
                </div>
                <div class="rec-footer">
                    <span>Propriedade: <strong>${r.fazendaNome}</strong></span>
                    <span>Captura: ${r.date}</span>
                </div>
            `;
            container.appendChild(card);
        });
    }

    // Trata cliques de filtros
    filterButtons.forEach(btn => {
        btn.addEventListener('click', (e) => {
            // Remove active de todos
            filterButtons.forEach(b => b.classList.remove('active'));
            // Adiciona no clicado
            btn.classList.add('active');

            const priority = btn.getAttribute('data-priority');
            renderRecomendacoes(priority);
        });
    });

    // Renderiza inicialmente todas
    renderRecomendacoes('todas');
});
