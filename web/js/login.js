document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const senhaInput = document.getElementById('senha');
    const errorDiv = document.getElementById('loginError');

    // Limpa login anterior caso exista
    localStorage.removeItem('agrosat_login');

    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const email = emailInput.value.trim();
        const senha = senhaInput.value.trim();

        if (!email || !senha) {
            errorDiv.textContent = 'Por favor, preencha todos os campos.';
            return;
        }

        // Validação básica para demonstração acadêmica
        if (email.includes('@') && senha.length >= 4) {
            // Sucesso! Salva sessão e redireciona
            localStorage.setItem('agrosat_login', email);
            errorDiv.textContent = '';
            
            // Pequeno delay para efeito visual premium
            errorDiv.style.color = '#00d4aa';
            errorDiv.textContent = 'Autenticado com sucesso! Carregando dados satelitais...';
            
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 1000);
        } else {
            errorDiv.style.color = '#ff5555';
            errorDiv.textContent = 'Credenciais inválidas. Use um e-mail válido e senha maior que 4 caracteres.';
        }
    });
});
