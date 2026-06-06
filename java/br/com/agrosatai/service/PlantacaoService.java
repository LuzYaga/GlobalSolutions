package br.com.agrosatai.service;

import br.com.agrosatai.model.Plantacao;
import java.util.ArrayList;
import java.util.List;

public class PlantacaoService {
    private static final List<Plantacao> plantacoes = new ArrayList<>();
    private static int geradorId = 1;

    static {
        // Dados iniciais
        plantacoes.add(new Plantacao(geradorId++, "Soja", 80.0, "15/02/2025", 1));
        plantacoes.add(new Plantacao(geradorId++, "Milho", 50.0, "01/03/2025", 1));
        plantacoes.add(new Plantacao(geradorId++, "Café", 30.0, "10/01/2025", 2));
    }

    public Plantacao cadastrar(String cultura, double area, String dataPlantio, int idFazenda) {
        Plantacao nova = new Plantacao(geradorId++, cultura, area, dataPlantio, idFazenda);
        plantacoes.add(nova);
        return nova;
    }

    public List<Plantacao> listar() {
        return new ArrayList<>(plantacoes);
    }

    public List<Plantacao> listarPorFazenda(int idFazenda) {
        List<Plantacao> filtradas = new ArrayList<>();
        for (Plantacao p : plantacoes) {
            if (p.getIdFazenda() == idFazenda) {
                filtradas.add(p);
            }
        }
        return filtradas;
    }

    public Plantacao buscarPorId(int id) {
        for (Plantacao p : plantacoes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String cultura, double area, String dataPlantio) {
        Plantacao p = buscarPorId(id);
        if (p != null) {
            p.setCultura(cultura);
            p.setArea(area);
            p.setDataPlantio(dataPlantio);
            return true;
        }
        return false;
    }
}
