package MODELS.CLASS;

public class Credenciais {

    private int idTrabalhador; // FK
    private String email;
    private String password;

    public Credenciais() {}

    public Credenciais(int idTrabalhador, String email, String password, boolean atividade) {
        this.idTrabalhador = idTrabalhador;
        this.email = email;
        this.password = password;
    }

    public int getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(int idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}