package MODELS.CLASS;

public class Tarefa {

    /* 
     * Atributos Tarefa
     */

    private int IdTarefa;
    private String Titulo;
    private String Descricao;
    private int IdTrabalhador;
    private int Evento;
    private boolean Estado;
    private int Categoria;
    private boolean Ativo;

    /* 
     * Getters e Setters
     */
    public int getIdTarefa() {
        return IdTarefa;
    }

    public void setIdTarefa(int IdTarefa) {
        this.IdTarefa = IdTarefa;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getIdTrabalhador() {
        return IdTrabalhador;
    }

    public void setIdTrabalhador(int IdTrabalhador) {
        this.IdTrabalhador = IdTrabalhador;
    }

    public int getEvento() {
        return Evento;
    }

    public void setEvento(int Evento) {
        this.Evento = Evento;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
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

    /* 
     * Funções da tarefa
     */
}
