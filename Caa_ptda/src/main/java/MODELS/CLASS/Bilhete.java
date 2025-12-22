package MODELS.CLASS;

public class Bilhete {

    /* 
     * Atributos Bilhete
     */
    private int IdBilhete;
    private int IdEvento;
    private int Lugar;
    private int IdCliente;
    private double Preco;
    private boolean Ativo;

    /* 
     * Getters e Setters
     */
    public int getIdBilhete() {
        return IdBilhete;
    }

    public void setIdBilhete(int IdBilhete) {
        this.IdBilhete = IdBilhete;
    }

    public int getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(int IdEvento) {
        this.IdEvento = IdEvento;
    }

    public int getLugar() {
        return Lugar;
    }

    public void setLugar(int Lugar) {
        this.Lugar = Lugar;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }

    /* 
     * Funções do bilhete
     */
}