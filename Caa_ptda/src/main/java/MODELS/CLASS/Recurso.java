package MODELS.CLASS;

public class Recurso {
    /* 
     * Atributos Recurso
     */
    
    private int IdRecurso;
    private String Nome;
    private double Preco;
    private int Quantidade;
    private boolean Ativo;
    
    /* 
     * Getters e Setters
     */

    public int getIdRecurso() {
        return IdRecurso;
    }

    public void setIdRecurso(int IdRecurso) {
        this.IdRecurso = IdRecurso;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
    
    /* 
     * Funções do Recurso
     */
}
