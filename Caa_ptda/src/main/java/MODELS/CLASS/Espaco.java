package MODELS.CLASS;

public class Espaco {
    /* 
     * Atributos Espaço
     */
    
    private int IdEspaco;
    private String Nome;
    private boolean Ativo;
    
    /* 
     * Getters e Setters
     */

    public int getIdEspaco() {
        return IdEspaco;
    }

    public void setIdEspaco(int IdEspaco) {
        this.IdEspaco = IdEspaco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
    
    /* 
     * Funções do Espaço
     */
}
