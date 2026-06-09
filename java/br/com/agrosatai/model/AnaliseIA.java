package br.com.agrosatai.model;

/**
 * Classe que representa uma Análise de IA no sistema AgroSat AI.
 * Armazena dados de análise inteligente com tipo, descrição, recomendação,
 * nível de urgência e vínculo com a fazenda.
 */
public class AnaliseIA {

    // Atributos
    private int id;
    private String tipo;
    private String descricao;
    private String recomendacao;
    private String nivel;
    private String dataAnalise;
    private int idFazenda;

    // Construtor vazio
    public AnaliseIA() {
    }

    // Construtor completo
    public AnaliseIA(int id, String tipo, String descricao, String recomendacao, String nivel, String dataAnalise, int idFazenda) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.recomendacao = recomendacao;
        this.nivel = nivel;
        this.dataAnalise = dataAnalise;
        this.idFazenda = idFazenda;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(String dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    // Representação textual da Análise de IA
    @Override
    public String toString() {
        return "=== Análise de IA ===" +
                "\nID: " + id +
                "\nTipo: " + tipo +
                "\nDescrição: " + descricao +
                "\nRecomendação: " + recomendacao +
                "\nNível: " + nivel +
                "\nData da Análise: " + dataAnalise +
                "\nID da Fazenda: " + idFazenda;
    }
}
