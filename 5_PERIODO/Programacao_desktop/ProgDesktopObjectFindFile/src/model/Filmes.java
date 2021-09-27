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
public class Filmes {
    static String titulo;
    static String genero;
    static int duracao;
    static String classificacao;
    static String studio;
    static boolean alugado;
    
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

    public static String getTitulo() {
        return titulo;
    }

    public static String getGenero() {
        return genero;
    }

    public static Integer getDuracao() {
        return duracao;
    }

    public static String getClassificacao() {
        return classificacao;
    }

    public static String getStudio() {
        return studio;
    }

    public static Boolean getAlugado() {
        return alugado;
    }

    public static void setTitulo(String titulo) {
        Filmes.titulo = titulo;
    }

    public static void setGenero(String genero) {
        Filmes.genero = genero;
    }

    public static void setDuracao(int duracao) {
        Filmes.duracao = duracao;
    }

    public static void setClassificacao(String classificacao) {
        Filmes.classificacao = classificacao;
    }

    public static void setStudio(String studio) {
        Filmes.studio = studio;
    }   

    public static void setAlugado(boolean alugado) {
        Filmes.alugado = alugado;
    }
}
