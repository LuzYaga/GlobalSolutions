package br.com.agrosatai.service;

import br.com.agrosatai.model.Alerta;
import br.com.agrosatai.model.AnaliseIA;
import br.com.agrosatai.model.ImagemSatelite;
import br.com.agrosatai.model.SensorClimatico;
import br.com.agrosatai.util.GeradorDados;

import java.util.ArrayList;
import java.util.List;

public class AnaliseService {
    private static final List<ImagemSatelite> imagens = new ArrayList<>();
    private static final List<SensorClimatico> sensores = new ArrayList<>();
    private static final List<AnaliseIA> analises = new ArrayList<>();
    private static final List<Alerta> alertas = new ArrayList<>();

    private static int geradorIdImagem = 1;
    private static int geradorIdSensor = 1;
    private static int geradorIdAnalise = 1;
    private static int geradorIdAlerta = 1;

    static {
        // Dados de teste iniciais
        imagens.add(new ImagemSatelite(geradorIdImagem++, "01/06/2025", 0.75, 62.5, 1));
        imagens.add(new ImagemSatelite(geradorIdImagem++, "01/06/2025", 0.28, 25.0, 2));

        sensores.add(new SensorClimatico(geradorIdSensor++, 28.5, 60.0, 15.0, "01/06/2025", 1));
        sensores.add(new SensorClimatico(geradorIdSensor++, 36.2, 28.0, 0.0, "01/06/2025", 2));

        alertas.add(new Alerta(geradorIdAlerta++, "Risco de Seca", "Alto", "Solo seco detectado pela umidade satelital abaixo de 30%.", "01/06/2025", 2));
    }

    public ImagemSatelite gerarAnaliseSatelital(int idFazenda) {
        double ndvi = GeradorDados.gerarIndiceVegetacao();
        double umidade = GeradorDados.gerarUmidade();
        String data = GeradorDados.gerarDataAtual();

        ImagemSatelite nova = new ImagemSatelite(geradorIdImagem++, data, ndvi, umidade, idFazenda);
        imagens.add(nova);
        return nova;
    }

    public SensorClimatico gerarRiscoClimatico(int idFazenda) {
        double temp = GeradorDados.gerarTemperatura();
        double umidade = GeradorDados.gerarUmidade();
        double precip = GeradorDados.gerarPrecipitacao();
        String data = GeradorDados.gerarDataAtual();

        SensorClimatico novo = new SensorClimatico(geradorIdSensor++, temp, umidade, precip, data, idFazenda);
        sensores.add(novo);
        return novo;
    }

    public List<AnaliseIA> gerarRecomendacaoIA(int idFazenda) {
        // Busca última imagem de satélite e dados de sensor
        ImagemSatelite ultimaImg = null;
        for (int i = imagens.size() - 1; i >= 0; i--) {
            if (imagens.get(i).getIdFazenda() == idFazenda) {
                ultimaImg = imagens.get(i);
                break;
            }
        }

        SensorClimatico ultimoSensor = null;
        for (int i = sensores.size() - 1; i >= 0; i--) {
            if (sensores.get(i).getIdFazenda() == idFazenda) {
                ultimoSensor = sensores.get(i);
                break;
            }
        }

        List<AnaliseIA> novasAnalises = new ArrayList<>();
        String data = GeradorDados.gerarDataAtual();

        if (ultimaImg != null) {
            double ndvi = ultimaImg.getIndiceVegetacao();
            double umidade = ultimaImg.getUmidade();

            // Regra 1: Índice de vegetação muito baixo (< 0.2) -> suspeita de pragas
            if (ndvi < 0.2) {
                AnaliseIA analise = new AnaliseIA(geradorIdAnalise++, "Pragas/Doenças",
                        "Índice de vegetação crítico (NDVI = " + String.format("%.2f", ndvi) + ")",
                        "Inspeção imediata do campo. Suspeita de pragas ou doenças severas na lavoura. Verificar sinais de lagartas, fungos ou bactérias e considerar aplicação emergencial de defensivos.",
                        "Vermelho", data, idFazenda);
                analises.add(analise);
                novasAnalises.add(analise);

                Alerta alerta = new Alerta(geradorIdAlerta++, "Suspeita de Pragas", "ALTO",
                        "NDVI crítico de " + String.format("%.2f", ndvi) + " indica possível ataque de pragas.", data, idFazenda);
                alertas.add(alerta);
            }
            // Regra 2: Índice de vegetação baixo (< 0.3) -> recomendação de irrigação
            else if (ndvi < 0.3) {
                AnaliseIA analise = new AnaliseIA(geradorIdAnalise++, "Vigor Vegetal Baixo",
                        "Queda detectada no vigor vegetal da plantação (NDVI = " + String.format("%.2f", ndvi) + ")",
                        "Possível deficiência hídrica. Recomendado aumentar irrigação em 15%, monitorar a região afetada e inspecionar sinais precoces de pragas.",
                        "Amarelo", data, idFazenda);
                analises.add(analise);
                novasAnalises.add(analise);

                Alerta alerta = new Alerta(geradorIdAlerta++, "Vigor Vegetal Baixo", "MEDIO",
                        "NDVI baixo de " + String.format("%.2f", ndvi) + ". Aumentar monitoramento.", data, idFazenda);
                alertas.add(alerta);
            }

            // Regra 3: Umidade < 30% -> risco de seca
            if (umidade < 30.0) {
                AnaliseIA analise = new AnaliseIA(geradorIdAnalise++, "Déficit Hídrico",
                        "Umidade do solo crítica de " + String.format("%.1f", umidade) + "%",
                        "Risco de seca severa. Ativar sistema de irrigação imediatamente, aplicar cobertura morta (mulching) para reter umidade e avaliar replantio.",
                        "Laranja", data, idFazenda);
                analises.add(analise);
                novasAnalises.add(analise);

                Alerta alerta = new Alerta(geradorIdAlerta++, "Risco de Seca", "ALTO",
                        "Umidade do solo muito baixa (" + String.format("%.1f", umidade) + "%).", data, idFazenda);
                alertas.add(alerta);
            }
            // Regra 4: Umidade > 80% -> risco de enchente
            else if (umidade > 80.0) {
                AnaliseIA analise = new AnaliseIA(geradorIdAnalise++, "Saturação de Solo",
                        "Umidade do solo elevada em " + String.format("%.1f", umidade) + "%",
                        "Risco de enchente ou encharcamento. Verificar sistema de drenagem da fazenda e suspender irrigação preventiva.",
                        "Azul", data, idFazenda);
                analises.add(analise);
                novasAnalises.add(analise);

                Alerta alerta = new Alerta(geradorIdAlerta++, "Risco de Enchente", "MEDIO",
                        "Umidade do solo saturada em " + String.format("%.1f", umidade) + "%.", data, idFazenda);
                alertas.add(alerta);
            }
        }

        if (ultimoSensor != null) {
            double temp = ultimoSensor.getTemperatura();
            // Regra 5: Temperatura > 35°C -> alerta climático
            if (temp > 35.0) {
                AnaliseIA analise = new AnaliseIA(geradorIdAnalise++, "Estresse Térmico",
                        "Temperatura ambiente elevada de " + String.format("%.1f", temp) + "°C",
                        "Alerta climático de calor extremo. Realizar irrigação preferencialmente nos períodos mais frescos (início da manhã ou fim da tarde) para reduzir perda de água.",
                        "Laranja", data, idFazenda);
                analises.add(analise);
                novasAnalises.add(analise);

                Alerta alerta = new Alerta(geradorIdAlerta++, "Alerta Climático", "MEDIO",
                        "Temperatura extrema de " + String.format("%.1f", temp) + "°C registrada na fazenda.", data, idFazenda);
                alertas.add(alerta);
            }
        }

        // Se nenhuma condição de alerta foi atingida
        if (novasAnalises.isEmpty()) {
            AnaliseIA analise = new AnaliseIA(geradorIdAnalise++, "Monitoramento Saudável",
                    "Condições estáveis e saudáveis",
                    "Manter cronograma de manejo atual. Os índices mostram vegetação vigorosa e níveis de umidade adequados.",
                    "Verde", data, idFazenda);
            analises.add(analise);
            novasAnalises.add(analise);
        }

        return novasAnalises;
    }

    public List<ImagemSatelite> listarImagensPorFazenda(int idFazenda) {
        List<ImagemSatelite> filtradas = new ArrayList<>();
        for (ImagemSatelite img : imagens) {
            if (img.getIdFazenda() == idFazenda) {
                filtradas.add(img);
            }
        }
        return filtradas;
    }

    public List<SensorClimatico> listarSensoresPorFazenda(int idFazenda) {
        List<SensorClimatico> filtradas = new ArrayList<>();
        for (SensorClimatico sc : sensores) {
            if (sc.getIdFazenda() == idFazenda) {
                filtradas.add(sc);
            }
        }
        return filtradas;
    }

    public List<Alerta> listarAlertasPorFazenda(int idFazenda) {
        List<Alerta> filtradas = new ArrayList<>();
        for (Alerta a : alertas) {
            if (a.getIdFazenda() == idFazenda) {
                filtradas.add(a);
            }
        }
        return filtradas;
    }

    public List<Alerta> listarTodosAlertas() {
        return new ArrayList<>(alertas);
    }

    public List<AnaliseIA> listarTodasAnalises() {
        return new ArrayList<>(analises);
    }
}
