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
    private boolean Cancelado;
    private boolean Ativo;
    
    // --- NOVOS ATRIBUTOS ---
    private double Preco;
    private double PrecoBilhete;
    private boolean Alugado;

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

    public boolean isCancelado() {
        return Cancelado;
    }

    public void setCancelado(boolean Cancelado) {
        this.Cancelado = Cancelado;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }

    // --- NOVOS GETTERS E SETTERS ---
    
    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public double getPrecoBilhete() {
        return PrecoBilhete;
    }

    public void setPrecoBilhete(double PrecoBilhete) {
        this.PrecoBilhete = PrecoBilhete;
    }

    public boolean isAlugado() {
        return Alugado;
    }

    public void setAlugado(boolean Alugado) {
        this.Alugado = Alugado;
    }
    
    @Override
    public String toString() {
        return this.getNome();
    }
}