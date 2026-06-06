package br.com.agrosatai.service;

import br.com.agrosatai.model.Plantacao;
import br.com.agrosatai.model.RelatorioAgricola;
import br.com.agrosatai.util.GeradorDados;

import java.util.ArrayList;
import java.util.List;

public class RelatorioService {
    private static final List<RelatorioAgricola> relatorios = new ArrayList<>();
    private static int geradorId = 1;

    static {
        // Dados de teste iniciais
        relatorios.add(new RelatorioAgricola(geradorId++, "01/06/2025", 3450.0, "Manter manejo atual. Vegetação em estágio avançado de desenvolvimento.", 1));
        relatorios.add(new RelatorioAgricola(geradorId++, "01/06/2025", 1950.0, "Atenção: Aumentar irrigação em 15% devido a estresse hídrico moderado detectado.", 3));
    }

    public RelatorioAgricola gerarRelatorio(int idPlantacao, Plantacao plantacao) {
        if (plantacao == null) {
            return null;
        }
        double produtividade = GeradorDados.gerarProdutividade(plantacao.getCultura());
        String data = GeradorDados.gerarDataAtual();
        
        // Determina recomendação básica
        String rec = "Recomenda-se realizar monitoramento contínuo das imagens de satélite. ";
        double randomVal = Math.random();
        if (randomVal < 0.3) {
            rec += "Atenção a variações bruscas de umidade do solo. Manter cronograma de adubação.";
        } else if (randomVal < 0.6) {
            rec += "Otimizar irrigação nos horários mais frescos do dia para evitar perda hídrica.";
        } else {
            rec += "Lavouras em ótimas condições de vigor vegetativo. Seguir planejamento de colheita.";
        }

        RelatorioAgricola novo = new RelatorioAgricola(geradorId++, data, produtividade, rec, idPlantacao);
        relatorios.add(novo);
        return novo;
    }

    public List<RelatorioAgricola> listar() {
        return new ArrayList<>(relatorios);
    }

    public List<RelatorioAgricola> listarPorPlantacao(int idPlantacao) {
        List<RelatorioAgricola> filtrados = new ArrayList<>();
        for (RelatorioAgricola r : relatorios) {
            if (r.getIdPlantacao() == idPlantacao) {
                filtrados.add(r);
            }
        }
        return filtrados;
    }

    public RelatorioAgricola buscarPorId(int id) {
        for (RelatorioAgricola r : relatorios) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public boolean atualizar(int id, double produtividade, String recomendacao) {
        RelatorioAgricola r = buscarPorId(id);
        if (r != null) {
            r.setProdutividade(produtividade);
            r.setRecomendacao(recomendacao);
            return true;
        }
        return false;
    }
}
