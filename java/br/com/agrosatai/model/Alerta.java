package br.com.agrosatai.model;

/**
 * Classe que representa um Alerta no sistema AgroSat AI.
 * Armazena dados de alertas gerados pelo sistema com tipo, nível,
 * descrição e vínculo com a fazenda.
 */
public class Alerta {

    // Atributos
    private int id;
    private String tipo;
    private String nivel;
    private String descricao;
    private String dataAlerta;
    private int idFazenda;

    // Construtor vazio
    public Alerta() {
    }

    // Construtor completo
    public Alerta(int id, String tipo, String nivel, String descricao, String dataAlerta, int idFazenda) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.descricao = descricao;
        this.dataAlerta = dataAlerta;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataAlerta() {
        return dataAlerta;
    }

    public void setDataAlerta(String dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    // Representação textual do Alerta
    @Override
    public String toString() {
        return "=== Alerta ===" +
                "\nID: " + id +
                "\nTipo: " + tipo +
                "\nNível: " + nivel +
                "\nDescrição: " + descricao +
                "\nData do Alerta: " + dataAlerta +
                "\nID da Fazenda: " + idFazenda;
    }
}
