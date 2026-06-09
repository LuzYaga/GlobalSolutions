package br.com.agrosatai.model;

/**
 * Classe que representa um Produtor rural no sistema AgroSat AI.
 * Armazena informações de contato e identificação do produtor.
 */
public class Produtor {

    // Atributos
    private int id;
    private String nome;
    private String telefone;
    private String email;

    // Construtor vazio
    public Produtor() {
    }

    // Construtor completo
    public Produtor(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Representação textual do Produtor
    @Override
    public String toString() {
        return "=== Produtor ===" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nE-mail: " + email;
    }
}
