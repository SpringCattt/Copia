package MODELS.CLASS;

public class CategoriaTarefa {

    /* 
     * Atributos Categoria Tarefa
     */

    private int IdCategoriaTarefa;
    private String Nome;
    private boolean Ativo;

    /* 
     * Getters e Setters
     */
    public int getIdCategoriaTarefa() {
        return IdCategoriaTarefa;
    }

    public void setIdCategoriaTarefa(int IdCategoriaTarefa) {
        this.IdCategoriaTarefa = IdCategoriaTarefa;
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
     * Funções da categoria da tarefa
     */
}
