package br.com.agrosatai.menu;

import br.com.agrosatai.model.Plantacao;
import br.com.agrosatai.service.RelatorioService;
import br.com.agrosatai.service.PlantacaoService;
import br.com.agrosatai.service.AnaliseService;

import java.util.Scanner;

public class MenuRelatorios {
    private final RelatorioService relatorioService;
    private final PlantacaoService plantacaoService;
    private final AnaliseService analiseService;
    private final Scanner scanner;

    public MenuRelatorios(RelatorioService relatorioService, PlantacaoService plantacaoService, AnaliseService analiseService, Scanner scanner) {
        this.relatorioService = relatorioService;
        this.plantacaoService = plantacaoService;
        this.analiseService = analiseService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 5) {
            System.out.println("\n══════════════════════════════════════════");
            System.out.println("          MONITORAMENTO E RELATÓRIOS");
            System.out.println("══════════════════════════════════════════");
            System.out.println("1. Gerar Relatório Agrícola");
            System.out.println("2. Listar Todos os Relatórios");
            System.out.println("3. Ver Alertas Ativos por Fazenda");
            System.out.println("4. Consultar Todas as Recomendações da IA");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        gerarRelatorio();
                        break;
                    case 2:
                        listarRelatorios();
                        break;
                    case 3:
                        verAlertas();
                        break;
                    case 4:
                        consultarRecomendacoes();
                        break;
                    case 5:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private void gerarRelatorio() {
        System.out.println("\n--- Gerar Relatório Agrícola ---");
        System.out.print("Digite o ID da Plantação: ");
        try {
            int idPlantacao = Integer.parseInt(scanner.nextLine());
            Plantacao p = plantacaoService.buscarPorId(idPlantacao);
            if (p == null) {
                System.out.println("Plantação não encontrada!");
                return;
            }

            var rel = relatorioService.gerarRelatorio(idPlantacao, p);
            System.out.println("Relatorio Agricola Gerado com Sucesso!");
            System.out.println(rel);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void listarRelatorios() {
        System.out.println("\n--- Lista Geral de Relatórios ---");
        var lista = relatorioService.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum relatório emitido até o momento.");
        } else {
            for (var r : lista) {
                System.out.println(r);
            }
        }
    }

    private void verAlertas() {
        System.out.println("\n--- Consultar Alertas por Fazenda ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int idFazenda = Integer.parseInt(scanner.nextLine());
            var alertas = analiseService.listarAlertasPorFazenda(idFazenda);
            System.out.println("Alertas Ativos:");
            if (alertas.isEmpty()) {
                System.out.println("  Nenhum alerta ativo nesta fazenda.");
            } else {
                for (var a : alertas) {
                    System.out.println("  - " + a);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void consultarRecomendacoes() {
        System.out.println("\n--- Consultar Recomendações da IA (Banco de Análises) ---");
        var lista = analiseService.listarTodasAnalises();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma análise de recomendação registrada. Utilize 'Gerar Recomendação IA' no menu principal.");
        } else {
            for (var a : lista) {
                System.out.println("----------------------------------------");
                System.out.println("ID: " + a.getId() + " | Fazenda: " + a.getIdFazenda() + " | Data: " + a.getDataAnalise());
                System.out.println("Tipo: " + a.getTipo() + " | Gravidade: " + a.getNivel());
                System.out.println("Descrição: " + a.getDescricao());
                System.out.println("Recomendacao: " + a.getRecomendacao());
            }
        }
    }
}
