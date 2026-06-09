package br.com.agrosatai;

import br.com.agrosatai.menu.MenuPrincipal;

/**
 * Ponto de entrada do sistema AgroSat AI.
 */
public class Main {
    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.exibir();
    }
}
