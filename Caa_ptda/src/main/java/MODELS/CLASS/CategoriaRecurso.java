package MODELS.CLASS;

public class CategoriaRecurso {

    /* 
     * Atributos Categoria Tarefa
     */

    private int IdCategoriaRecurso;
    private String Nome;
    private boolean Ativo;

    /* 
     * Getters e Setters
     */
    public int getIdCategoriaRecurso() {
        return IdCategoriaRecurso;
    }

    public void setIdCategoriaRecurso(int IdCategoriaRecurso) {
        this.IdCategoriaRecurso = IdCategoriaRecurso;
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

    public void setIdCategoria(long generatedId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
