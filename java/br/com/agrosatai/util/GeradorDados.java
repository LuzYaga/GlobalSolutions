package br.com.agrosatai.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Utilitário para geração de dados simulados inspirados em sensoriamento remoto e observação da Terra.
 */
public class GeradorDados {
    private static final Random random = new Random();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static double gerarIndiceVegetacao() {
        // Gera um valor entre 0.0 e 1.0 (NDVI simulado)
        return 0.1 + (random.nextDouble() * 0.85);
    }

    public static double gerarUmidade() {
        // Gera umidade do solo entre 10% e 95%
        return 10.0 + (random.nextDouble() * 85.0);
    }

    public static double gerarTemperatura() {
        // Gera temperatura entre 15°C e 42°C
        return 15.0 + (random.nextDouble() * 27.0);
    }

    public static double gerarPrecipitacao() {
        // Gera precipitação simulada em mm (0.0 a 150.0)
        return random.nextDouble() * 150.0;
    }

    public static double gerarProdutividade(String cultura) {
        // Simula produtividade estimada em kg/ha baseada no tipo de cultura
        double base = 1000.0;
        if (cultura != null) {
            switch (cultura.toLowerCase()) {
                case "soja":
                    base = 3000.0 + (random.nextDouble() * 1200.0);
                    break;
                case "milho":
                    base = 5000.0 + (random.nextDouble() * 2500.0);
                    break;
                case "café":
                case "cafe":
                    base = 1500.0 + (random.nextDouble() * 800.0);
                    break;
                case "cana":
                case "cana-de-açúcar":
                case "cana-de-acucar":
                    base = 70000.0 + (random.nextDouble() * 15000.0);
                    break;
                default:
                    base = 2000.0 + (random.nextDouble() * 1500.0);
                    break;
            }
        }
        return base;
    }

    public static String gerarDataAtual() {
        return LocalDate.now().format(formatter);
    }
}
