package br.com.agrosatai.service;

import br.com.agrosatai.model.Produtor;
import java.util.ArrayList;
import java.util.List;

public class ProdutorService {
    private static final List<Produtor> produtores = new ArrayList<>();
    private static int geradorId = 1;

    static {
        // Inicializa alguns dados de teste para facilitar a avaliação acadêmica
        produtores.add(new Produtor(geradorId++, "João da Silva", "(11) 99999-1111", "joao.silva@email.com"));
        produtores.add(new Produtor(geradorId++, "Maria Oliveira", "(11) 99999-2222", "maria.oliveira@email.com"));
    }

    public Produtor cadastrar(String nome, String telefone, String email) {
        Produtor novo = new Produtor(geradorId++, nome, telefone, email);
        produtores.add(novo);
        return novo;
    }

    public List<Produtor> listar() {
        return new ArrayList<>(produtores);
    }

    public Produtor buscarPorId(int id) {
        for (Produtor p : produtores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String telefone, String email) {
        Produtor p = buscarPorId(id);
        if (p != null) {
            p.setNome(nome);
            p.setTelefone(telefone);
            p.setEmail(email);
            return true;
        }
        return false;
    }
}
