package br.com.agrosatai.model;

/**
 * Classe que representa um Relatório Agrícola no sistema AgroSat AI.
 * Armazena dados de produtividade, recomendações e vínculo com a plantação.
 */
public class RelatorioAgricola {

    // Atributos
    private int id;
    private String dataRelatorio;
    private double produtividade;
    private String recomendacao;
    private int idPlantacao;

    // Construtor vazio
    public RelatorioAgricola() {
    }

    // Construtor completo
    public RelatorioAgricola(int id, String dataRelatorio, double produtividade, String recomendacao, int idPlantacao) {
        this.id = id;
        this.dataRelatorio = dataRelatorio;
        this.produtividade = produtividade;
        this.recomendacao = recomendacao;
        this.idPlantacao = idPlantacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(String dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public double getProdutividade() {
        return produtividade;
    }

    public void setProdutividade(double produtividade) {
        this.produtividade = produtividade;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    public int getIdPlantacao() {
        return idPlantacao;
    }

    public void setIdPlantacao(int idPlantacao) {
        this.idPlantacao = idPlantacao;
    }

    // Representação textual do Relatório Agrícola
    @Override
    public String toString() {
        return "=== Relatório Agrícola ===" +
                "\nID: " + id +
                "\nData do Relatório: " + dataRelatorio +
                "\nProdutividade (ton/ha): " + produtividade +
                "\nRecomendação: " + recomendacao +
                "\nID da Plantação: " + idPlantacao;
    }
}
