package br.com.agrosatai.menu;

import br.com.agrosatai.service.*;
import java.util.Scanner;

public class MenuPrincipal {
    private final ProdutorService produtorService;
    private final FazendaService fazendaService;
    private final PlantacaoService plantacaoService;
    private final AnaliseService analiseService;
    private final RelatorioService relatorioService;
    private final Scanner scanner;

    public MenuPrincipal() {
        this.produtorService = new ProdutorService();
        this.fazendaService = new FazendaService();
        this.plantacaoService = new PlantacaoService();
        this.analiseService = new AnaliseService();
        this.relatorioService = new RelatorioService();
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 6) {
            System.out.println("\n========================================================");
            System.out.println("                   AGROSAT AI");
            System.out.println("   \"Seu satélite virtual para proteger pequenas");
            System.out.println("              propriedades rurais\"");
            System.out.println("========================================================");
            System.out.println("1. Gestão de Produtores");
            System.out.println("2. Gestão de Fazendas e Plantações");
            System.out.println("3. Monitoramento e Relatórios");
            System.out.println("4. Gerar Análise Satelital Simulada");
            System.out.println("5. Gerar Recomendação IA (Análise de Dados)");
            System.out.println("6. Sair do Sistema");
            System.out.println("========================================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        new MenuProdutor(produtorService, fazendaService, scanner).exibir();
                        break;
                    case 2:
                        new MenuFazenda(fazendaService, plantacaoService, analiseService, scanner).exibir();
                        break;
                    case 3:
                        new MenuRelatorios(relatorioService, plantacaoService, analiseService, scanner).exibir();
                        break;
                    case 4:
                        gerarAnaliseRapida();
                        break;
                    case 5:
                        gerarRecomendacaoIA();
                        break;
                    case 6:
                        System.out.println("\nObrigado por utilizar o AgroSat AI. Protegendo a terra de quem nos alimenta!");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número correspondente à opção.");
            }
        }
        scanner.close();
    }

    private void gerarAnaliseRapida() {
        System.out.println("\n--- Gerar Análise Satelital Direta ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int idFazenda = Integer.parseInt(scanner.nextLine());
            var f = fazendaService.buscarPorId(idFazenda);
            if (f == null) {
                System.out.println("Fazenda não encontrada!");
                return;
            }

            // Geramos ambos dados para enriquecer o modelo
            var img = analiseService.gerarAnaliseSatelital(idFazenda);
            analiseService.gerarRiscoClimatico(idFazenda);

            System.out.println("Captura executada com sucesso!");
            System.out.println("  Índice de Vegetação (NDVI): " + String.format("%.2f", img.getIndiceVegetacao()));
            System.out.println("  Umidade do Solo: " + String.format("%.1f", img.getUmidade()) + "%");
            System.out.println("Use a opção 5 para rodar o mecanismo de Recomendação da IA.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void gerarRecomendacaoIA() {
        System.out.println("\n--- Gerar Recomendação Inteligente (IA) ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int idFazenda = Integer.parseInt(scanner.nextLine());
            var f = fazendaService.buscarPorId(idFazenda);
            if (f == null) {
                System.out.println("Fazenda não encontrada!");
                return;
            }

            var recomendacoes = analiseService.gerarRecomendacaoIA(idFazenda);
            System.out.println("\nAgroSat AI processou os últimos dados...");
            System.out.println("════════════════════════════════════════════════════════");
            for (var rec : recomendacoes) {
                System.out.println("Diagnóstico: " + rec.getDescricao());
                System.out.println("Nível de Alerta: " + rec.getNivel());
                System.out.println("Recomendação: " + rec.getRecomendacao());
                System.out.println("────────────────────────────────────────────────────────");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
}
