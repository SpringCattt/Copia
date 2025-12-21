package MODELS.CLASS;

public class Sala {

    /* 
     * Atributos Sala
     */
    private int IdSala;
    private String Nome;
    private int TipoEspaco;
    private int Lugares;
    private boolean Ocupada;
    private boolean TemLugares;
    private boolean Ativo;

    /* 
     * Getters e Setters
     */

    /* 
     * Funções da Sala
     */

    public int getIdSala() {
        return IdSala;
    }

    public void setIdSala(int IdSala) {
        this.IdSala = IdSala;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getTipoEspaco() {
        return TipoEspaco;
    }

    public void setTipoEspaco(int TipoEspaco) {
        this.TipoEspaco = TipoEspaco;
    }

    public int getLugares() {
        return Lugares;
    }

    public void setLugares(int Lugares) {
        this.Lugares = Lugares;
    }

    public boolean isOcupada() {
        return Ocupada;
    }

    public void setOcupada(boolean Ocupada) {
        this.Ocupada = Ocupada;
    }

    public boolean isTemLugares() {
        return TemLugares;
    }

    public void setTemLugares(boolean TemLugares) {
        this.TemLugares = TemLugares;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
}
