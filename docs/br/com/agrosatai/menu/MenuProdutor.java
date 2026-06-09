package br.com.agrosatai.menu;

import br.com.agrosatai.model.Produtor;
import br.com.agrosatai.service.ProdutorService;
import br.com.agrosatai.service.FazendaService;

import java.util.Scanner;

public class MenuProdutor {
    private final ProdutorService produtorService;
    private final FazendaService fazendaService;
    private final Scanner scanner;

    public MenuProdutor(ProdutorService produtorService, FazendaService fazendaService, Scanner scanner) {
        this.produtorService = produtorService;
        this.fazendaService = fazendaService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 6) {
            System.out.println("\n══════════════════════════════════════════");
            System.out.println("          GESTÃO DE PRODUTORES");
            System.out.println("══════════════════════════════════════════");
            System.out.println("1. Cadastrar Produtor");
            System.out.println("2. Listar Produtores");
            System.out.println("3. Buscar Produtor por ID");
            System.out.println("4. Atualizar Produtor");
            System.out.println("5. Cadastrar Fazenda para Produtor");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        cadastrarProdutor();
                        break;
                    case 2:
                        listarProdutores();
                        break;
                    case 3:
                        buscarProdutor();
                        break;
                    case 4:
                        atualizarProdutor();
                        break;
                    case 5:
                        cadastrarFazendaParaProdutor();
                        break;
                    case 6:
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

    private void cadastrarProdutor() {
        System.out.println("\n--- Cadastrar Produtor ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (nome.trim().isEmpty() || telefone.trim().isEmpty() || email.trim().isEmpty()) {
            System.out.println("Erro: Todos os campos são obrigatórios!");
            return;
        }

        Produtor p = produtorService.cadastrar(nome, telefone, email);
        System.out.println("Produtor cadastrado com sucesso! ID: " + p.getId());
    }

    private void listarProdutores() {
        System.out.println("\n--- Lista de Produtores ---");
        var lista = produtorService.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produtor cadastrado.");
        } else {
            for (Produtor p : lista) {
                System.out.println(p);
            }
        }
    }

    private void buscarProdutor() {
        System.out.println("\n--- Buscar Produtor ---");
        System.out.print("Digite o ID do Produtor: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Produtor p = produtorService.buscarPorId(id);
            if (p != null) {
                System.out.println("Produtor Encontrado:");
                System.out.println(p);
                // Exibe as fazendas deste produtor
                var fazendas = fazendaService.listarPorProdutor(id);
                System.out.println("Fazendas vinculadas:");
                if (fazendas.isEmpty()) {
                    System.out.println("  Nenhuma fazenda vinculada.");
                } else {
                    for (var f : fazendas) {
                        System.out.println("  - " + f);
                    }
                }
            } else {
                System.out.println("Produtor não encontrado!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void atualizarProdutor() {
        System.out.println("\n--- Atualizar Produtor ---");
        System.out.print("Digite o ID do Produtor: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Produtor p = produtorService.buscarPorId(id);
            if (p != null) {
                System.out.print("Novo Nome (" + p.getNome() + "): ");
                String nome = scanner.nextLine();
                if (nome.trim().isEmpty()) nome = p.getNome();

                System.out.print("Novo Telefone (" + p.getTelefone() + "): ");
                String tel = scanner.nextLine();
                if (tel.trim().isEmpty()) tel = p.getTelefone();

                System.out.print("Novo Email (" + p.getEmail() + "): ");
                String email = scanner.nextLine();
                if (email.trim().isEmpty()) email = p.getEmail();

                produtorService.atualizar(id, nome, tel, email);
                System.out.println("Produtor atualizado com sucesso!");
            } else {
                System.out.println("Produtor não encontrado!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private void cadastrarFazendaParaProdutor() {
        System.out.println("\n--- Cadastrar Fazenda para Produtor ---");
        System.out.print("Digite o ID do Produtor: ");
        try {
            int idProd = Integer.parseInt(scanner.nextLine());
            Produtor p = produtorService.buscarPorId(idProd);
            if (p == null) {
                System.out.println("Produtor não encontrado!");
                return;
            }

            System.out.print("Nome da Fazenda: ");
            String nome = scanner.nextLine();
            System.out.print("Localização: ");
            String loc = scanner.nextLine();
            System.out.print("Área Total (Hectares): ");
            double area = Double.parseDouble(scanner.nextLine());

            if (nome.trim().isEmpty() || loc.trim().isEmpty() || area <= 0) {
                System.out.println("Campos inválidos!");
                return;
            }

            var f = fazendaService.cadastrar(nome, loc, area, idProd);
            System.out.println("Fazenda cadastrada com sucesso e vinculada ao Produtor! ID Fazenda: " + f.getId());
        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida!");
        }
    }
}
