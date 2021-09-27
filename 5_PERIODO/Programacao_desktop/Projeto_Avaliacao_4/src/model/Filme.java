/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pedro Pereira
 */
public class Filme{
    private int id;
    private String titulo;
    private String genero;
    private int duracao;
    private String classificacao;
    private String studio;
    
    public Filme(){
        this.id = 0;
        this.titulo = "";
        this.genero = "";
        this.duracao = 0;
        this.classificacao = "";
        this.studio = "";
    }
    
    public Filme(int id, String titulo, String genero, int duracao, String classificacao, String studio){
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.studio = studio;
    }

    public int getId() {
        return id;
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

    public void setId(int id){
        this.id = id;
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
}
