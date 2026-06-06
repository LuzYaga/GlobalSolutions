package br.com.agrosatai.model;

/**
 * Classe que representa um Sensor Climático no sistema AgroSat AI.
 * Armazena leituras de temperatura, umidade, precipitação e vínculo com a fazenda.
 */
public class SensorClimatico {

    // Atributos
    private int id;
    private double temperatura;
    private double umidade;
    private double precipitacao;
    private String dataLeitura;
    private int idFazenda;

    // Construtor vazio
    public SensorClimatico() {
    }

    // Construtor completo
    public SensorClimatico(int id, double temperatura, double umidade, double precipitacao, String dataLeitura, int idFazenda) {
        this.id = id;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.precipitacao = precipitacao;
        this.dataLeitura = dataLeitura;
        this.idFazenda = idFazenda;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public String getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(String dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    // Representação textual do Sensor Climático
    @Override
    public String toString() {
        return "=== Sensor Climático ===" +
                "\nID: " + id +
                "\nTemperatura (°C): " + temperatura +
                "\nUmidade (%): " + umidade +
                "\nPrecipitação (mm): " + precipitacao +
                "\nData da Leitura: " + dataLeitura +
                "\nID da Fazenda: " + idFazenda;
    }
}
