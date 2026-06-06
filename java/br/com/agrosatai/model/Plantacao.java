package br.com.agrosatai.model;

/**
 * Classe que representa uma Plantação no sistema AgroSat AI.
 * Armazena dados da cultura, área plantada, data de plantio e vínculo com a fazenda.
 */
public class Plantacao {

    // Atributos
    private int id;
    private String cultura;
    private double area;
    private String dataPlantio;
    private int idFazenda;

    // Construtor vazio
    public Plantacao() {
    }

    // Construtor completo
    public Plantacao(int id, String cultura, double area, String dataPlantio, int idFazenda) {
        this.id = id;
        this.cultura = cultura;
        this.area = area;
        this.dataPlantio = dataPlantio;
        this.idFazenda = idFazenda;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(String dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    // Representação textual da Plantação
    @Override
    public String toString() {
        return "=== Plantação ===" +
                "\nID: " + id +
                "\nCultura: " + cultura +
                "\nÁrea (ha): " + area +
                "\nData de Plantio: " + dataPlantio +
                "\nID da Fazenda: " + idFazenda;
    }
}
