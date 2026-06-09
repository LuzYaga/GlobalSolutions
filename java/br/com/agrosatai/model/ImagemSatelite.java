package br.com.agrosatai.model;

/**
 * Classe que representa uma Imagem de Satélite no sistema AgroSat AI.
 * Armazena dados de captura, índice de vegetação, umidade e vínculo com a fazenda.
 */
public class ImagemSatelite {

    // Atributos
    private int id;
    private String dataCaptura;
    private double indiceVegetacao;
    private double umidade;
    private int idFazenda;

    // Construtor vazio
    public ImagemSatelite() {
    }

    // Construtor completo
    public ImagemSatelite(int id, String dataCaptura, double indiceVegetacao, double umidade, int idFazenda) {
        this.id = id;
        this.dataCaptura = dataCaptura;
        this.indiceVegetacao = indiceVegetacao;
        this.umidade = umidade;
        this.idFazenda = idFazenda;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataCaptura() {
        return dataCaptura;
    }

    public void setDataCaptura(String dataCaptura) {
        this.dataCaptura = dataCaptura;
    }

    public double getIndiceVegetacao() {
        return indiceVegetacao;
    }

    public void setIndiceVegetacao(double indiceVegetacao) {
        this.indiceVegetacao = indiceVegetacao;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    // Representação textual da Imagem de Satélite
    @Override
    public String toString() {
        return "=== Imagem de Satélite ===" +
                "\nID: " + id +
                "\nData de Captura: " + dataCaptura +
                "\nÍndice de Vegetação (NDVI): " + indiceVegetacao +
                "\nUmidade (%): " + umidade +
                "\nID da Fazenda: " + idFazenda;
    }
}
