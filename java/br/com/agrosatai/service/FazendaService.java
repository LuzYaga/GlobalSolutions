package br.com.agrosatai.service;

import br.com.agrosatai.model.Fazenda;
import java.util.ArrayList;
import java.util.List;

public class FazendaService {
    private static final List<Fazenda> fazendas = new ArrayList<>();
    private static int geradorId = 1;

    static {
        // Dados iniciais
        fazendas.add(new Fazenda(geradorId++, "Fazenda Esperança", "Ribeirão Preto - SP", 150.5, 1));
        fazendas.add(new Fazenda(geradorId++, "Sítio Boa Vista", "Campinas - SP", 45.0, 2));
    }

    public Fazenda cadastrar(String nome, String localizacao, double areaTotal, int idProdutor) {
        Fazenda nova = new Fazenda(geradorId++, nome, localizacao, areaTotal, idProdutor);
        fazendas.add(nova);
        return nova;
    }

    public List<Fazenda> listar() {
        return new ArrayList<>(fazendas);
    }

    public List<Fazenda> listarPorProdutor(int idProdutor) {
        List<Fazenda> filtradas = new ArrayList<>();
        for (Fazenda f : fazendas) {
            if (f.getIdProdutor() == idProdutor) {
                filtradas.add(f);
            }
        }
        return filtradas;
    }

    public Fazenda buscarPorId(int id) {
        for (Fazenda f : fazendas) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String localizacao, double areaTotal) {
        Fazenda f = buscarPorId(id);
        if (f != null) {
            f.setNome(nome);
            f.setLocalizacao(localizacao);
            f.setAreaTotal(areaTotal);
            return true;
        }
        return false;
    }
}
