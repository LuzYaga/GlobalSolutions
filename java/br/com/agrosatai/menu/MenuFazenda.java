package br.com.agrosatai.menu;

import br.com.agrosatai.model.Fazenda;
import br.com.agrosatai.model.Plantacao;
import br.com.agrosatai.service.FazendaService;
import br.com.agrosatai.service.PlantacaoService;
import br.com.agrosatai.service.AnaliseService;

import java.util.Scanner;

public class MenuFazenda {
    private final FazendaService fazendaService;
    private final PlantacaoService plantacaoService;
    private final AnaliseService analiseService;
    private final Scanner scanner;

    public MenuFazenda(FazendaService fazendaService, PlantacaoService plantacaoService, AnaliseService analiseService, Scanner scanner) {
        this.fazendaService = fazendaService;
        this.plantacaoService = plantacaoService;
        this.analiseService = analiseService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 7) {
            System.out.println("\n══════════════════════════════════════════");
            System.out.println("          GESTÃO DE FAZENDAS");
            System.out.println("══════════════════════════════════════════");
            System.out.println("1. Listar Fazendas");
            System.out.println("2. Buscar Fazenda por ID");
            System.out.println("3. Cadastrar Plantação na Fazenda");
            System.out.println("4. Listar Plantações");
            System.out.println("5. Gerar Análise Satelital (Simulado)");
            System.out.println("6. Gerar Risco Climático (Simulado)");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        listarFazendas();
                        break;
                    case 2:
                        buscarFazenda();
                        break;
                    case 3:
                        cadastrarPlantacao();
                        break;
                    case 4:
                        listarPlantacoes();
                        break;
                    case 5:
                        gerarAnaliseSatelital();
                        break;
                    case 6:
                        gerarRiscoClimatico();
                        break;
                    case 7:
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

    private void listarFazendas() {
        System.out.println("\n--- Lista de Fazendas ---");
        var lista = fazendaService.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma fazenda cadastrada.");
        } else {
            for (Fazenda f : lista) {
                System.out.println(f);
            }
        }
    }

    private void buscarFazenda() {
        System.out.println("\n--- Buscar Fazenda ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Fazenda f = fazendaService.buscarPorId(id);
            if (f != null) {
                System.out.println("Fazenda Encontrada:");
                System.out.println(f);
                System.out.println("Plantações ativas:");
                var plants = plantacaoService.listarPorFazenda(id);
                if (plants.isEmpty()) {
                    System.out.println("  Nenhuma plantação cadastrada nesta fazenda.");
                } else {
                    for (var p : plants) {
                        System.out.println("  - " + p);
                    }
                }
            } else {
                System.out.println("Fazenda não encontrada!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void cadastrarPlantacao() {
        System.out.println("\n--- Cadastrar Plantação ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int idFazenda = Integer.parseInt(scanner.nextLine());
            Fazenda f = fazendaService.buscarPorId(idFazenda);
            if (f == null) {
                System.out.println("Fazenda não encontrada!");
                return;
            }

            System.out.print("Cultura (ex: Soja, Milho, Café): ");
            String cultura = scanner.nextLine();
            System.out.print("Área Cultivada (Hectares): ");
            double area = Double.parseDouble(scanner.nextLine());
            System.out.print("Data de Plantio (dd/mm/aaaa): ");
            String data = scanner.nextLine();

            if (cultura.trim().isEmpty() || area <= 0 || data.trim().isEmpty()) {
                System.out.println("Campos inválidos!");
                return;
            }

            Plantacao p = plantacaoService.cadastrar(cultura, area, data, idFazenda);
            System.out.println("Plantação cadastrada com sucesso! ID: " + p.getId());
        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida!");
        }
    }

    private void listarPlantacoes() {
        System.out.println("\n--- Lista Geral de Plantações ---");
        var lista = plantacaoService.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma plantação cadastrada.");
        } else {
            for (Plantacao p : lista) {
                System.out.println(p);
            }
        }
    }

    private void gerarAnaliseSatelital() {
        System.out.println("\n--- Gerar Análise Satelital Simulada ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int idFazenda = Integer.parseInt(scanner.nextLine());
            Fazenda f = fazendaService.buscarPorId(idFazenda);
            if (f == null) {
                System.out.println("Fazenda não encontrada!");
                return;
            }

            var img = analiseService.gerarAnaliseSatelital(idFazenda);
            System.out.println("Imagem de satélite processada com sucesso!");
            System.out.println("Dados obtidos pelo Satélite Virtual:");
            System.out.println("  ID Captura: " + img.getId());
            System.out.println("  Data de Captura: " + img.getDataCaptura());
            System.out.println("  Índice de Vegetação (NDVI): " + String.format("%.2f", img.getIndiceVegetacao()));
            System.out.println("  Umidade do Solo: " + String.format("%.1f", img.getUmidade()) + "%");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void gerarRiscoClimatico() {
        System.out.println("\n--- Gerar Risco Climático Simulado ---");
        System.out.print("Digite o ID da Fazenda: ");
        try {
            int idFazenda = Integer.parseInt(scanner.nextLine());
            Fazenda f = fazendaService.buscarPorId(idFazenda);
            if (f == null) {
                System.out.println("Fazenda não encontrada!");
                return;
            }

            var sensor = analiseService.gerarRiscoClimatico(idFazenda);
            System.out.println("Sensor climático atualizado com sucesso!");
            System.out.println("Dados climáticos atuais:");
            System.out.println("  ID Sensor: " + sensor.getId());
            System.out.println("  Temperatura: " + String.format("%.1f", sensor.getTemperatura()) + "°C");
            System.out.println("  Umidade do Solo: " + String.format("%.1f", sensor.getUmidade()) + "%");
            System.out.println("  Precipitação: " + String.format("%.1f", sensor.getPrecipitacao()) + " mm");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
}
