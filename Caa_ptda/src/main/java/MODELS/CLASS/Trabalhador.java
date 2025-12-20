package MODELS.CLASS;

public class Trabalhador {

    private int IdTrabalhador;
    private String Nome;
    private String EmailPessoal;
    private int Categoria;
    private boolean Ativo;

    public Trabalhador() {
    }

    public Trabalhador(int id, String nome, String emailPessoal, int categoria, boolean ativo) {
        this.IdTrabalhador = id;
        this.Nome = nome;
        this.EmailPessoal = emailPessoal;
        this.Categoria = categoria;
        this.Ativo = ativo;
    }

   
    public int getIdTrabalhador() {
        return IdTrabalhador;
    }

    public void setIdTrabalhador(int IdTrabalhador) {
        this.IdTrabalhador = IdTrabalhador;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmailPessoal() {
        return EmailPessoal;
    }

    public void setEmailPessoal(String EmailPessoal) {
        this.EmailPessoal = EmailPessoal;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
    
    public String toString() {
        return Nome; 
    }
}