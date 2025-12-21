package MODELS.CLASS;

import java.sql.Time;
import java.util.Date;

public class Evento {

    /* * Atributos Evento
     */
    private int IdEvento;
    private String Nome;
    private Date Data;
    private String Descricao;
    private int Responsavel;
    private int Sala;
    private Date Hora;
    private Time Duracao;
    private boolean Estado;
    private boolean Decorreu; 
    private boolean Ativo;

    /* * Getters e Setters
     */
    public int getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(int IdEvento) {
        this.IdEvento = IdEvento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(int Responsavel) {
        this.Responsavel = Responsavel;
    }

    public int getSala() {
        return Sala;
    }

    public void setSala(int Sala) {
        this.Sala = Sala;
    }

    public Date getHora() {
        return Hora;
    }

    public void setHora(Date Hora) {
        this.Hora = Hora;
    }

    public Time getDuracao() {
        return Duracao;
    }

    public void setDuracao(Time Duracao) {
        this.Duracao = Duracao;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public boolean isDecorreu() {
        return Decorreu;
    }

    public void setDecorreu(boolean Decorreu) {
        this.Decorreu = Decorreu;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
    
    @Override
    public String toString() {
        return this.getNome();
    }
}