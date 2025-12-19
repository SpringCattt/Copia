package MODELS.CLASS;

public class Lugar {
    /* 
     * Atributos Lugar
     */
    
    private int IdLugar;
    private String Numero;
    private int IdSala;
    private boolean Ativo;
    
    /* 
     * Getters e Setters
     */

    public int getIdLugar() {
        return IdLugar;
    }

    public void setIdLugar(int IdLugar) {
        this.IdLugar = IdLugar;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public int getIdSala() {
        return IdSala;
    }

    public void setIdSala(int IdSala) {
        this.IdSala = IdSala;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
    
    /* 
     * Funções do Lugar
     */
}
