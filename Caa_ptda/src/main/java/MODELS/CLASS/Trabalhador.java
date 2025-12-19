package MODELS.CLASS;

import java.util.ArrayList;
import java.util.List;
/* 
 * Trabalhador Controller - CAA PTDA 
 * Desenvolvido por: Daniel Morais, Filipe Candeias, Gonçalo Fonseca, 
 * Bernardo Oliveira, Juan Gonsalves 
 */
public class Trabalhador {

    /* 
     * Atributos Trabalhador 
     */
    private int IdTrabalhador;
    private String Nome;
    private String EmailPessoal;
    private String EmailTrabalho;
    private int Categoria;
    private String Password;
    private boolean Ativo;

    private static List<Trabalhador> trabalhadores = new ArrayList<>();
    private static int nextId = 1;
    
    public Trabalhador(){}
    
    public Trabalhador(int id, String nome, String emailPessoal, int categoria, boolean ativo) {
        this.IdTrabalhador = id;
        this.Nome = nome;
        this.EmailPessoal = emailPessoal;
        this.Categoria = categoria;
        this.Ativo = ativo;
    }
      
    /* 
     * Getters e Setters
     */
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
    
    /* 
     * Funções do Trabalhador
     */

    public static Trabalhador criarTrabalhador(String nome, String emailP, String emailT, int categoria, String password, boolean atividade) {
        Trabalhador t = new Trabalhador(nextId++, nome, emailP, categoria, atividade);
        trabalhadores.add(t);

        System.out.println("✔ Trabalhador criado com sucesso:");
        System.out.println("ID: " + t.getIdTrabalhador());
        System.out.println("Nome: " + t.getNome());
        System.out.println("Email pessoal: " + t.getEmailPessoal());
        System.out.println("Categoria: " + t.getCategoria());
        System.out.println("Ativo: " + t.isAtivo());

        return t;
    }

    /** Obtener la lista de trabajadores */
    public static List<Trabalhador> getTrabalhadores() {
        return trabalhadores;
    }
    
}
