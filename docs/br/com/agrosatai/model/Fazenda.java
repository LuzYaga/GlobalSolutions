package br.com.agrosatai.model;

/**
 * Classe que representa uma Fazenda no sistema AgroSat AI.
 * Armazena dados de localização, área total e vínculo com o produtor.
 */
public class Fazenda {

    // Atributos
    private int id;
    private String nome;
    private String localizacao;
    private double areaTotal;
    private int idProdutor;

    // Construtor vazio
    public Fazenda() {
    }

    // Construtor completo
    public Fazenda(int id, String nome, String localizacao, double areaTotal, int idProdutor) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.areaTotal = areaTotal;
        this.idProdutor = idProdutor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public int getIdProdutor() {
        return idProdutor;
    }

    public void setIdProdutor(int idProdutor) {
        this.idProdutor = idProdutor;
    }

    // Representação textual da Fazenda
    @Override
    public String toString() {
        return "=== Fazenda ===" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nLocalização: " + localizacao +
                "\nÁrea Total (ha): " + areaTotal +
                "\nID do Produtor: " + idProdutor;
    }
}
