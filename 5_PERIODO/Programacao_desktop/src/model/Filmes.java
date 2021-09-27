/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pedropereira
 */
public class Filmes implements java.io.Serializable{
    private static long serialVersionUID = 1L;
    private String titulo;
    private String genero;
    private int duracao;
    private String classificacao;
    private String studio;
    private boolean alugado;
    
    public Filmes(){
        this.titulo = "";
        this.genero = "";
        this.duracao = 0;
        this.classificacao = "";
        this.studio = "";
        this.alugado = false;
    }
    
    public Filmes(String titulo, String genero, int duracao, String classificacao, String studio, boolean alugado){
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.studio = studio;
        this.alugado = alugado;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getStudio() {
        return studio;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }   

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }
}
