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
public class Locadora {
    static String funcionario;
    static String tituloFilme;
    static String studioFilme;
    static String rgCliente;
    static String data;
    static int periodoAlugado;
    static float valor;
    static boolean devolvido;
    
    public Locadora(){
        this.funcionario = "";
        this.tituloFilme = "";
        this.studioFilme = "";
        this.rgCliente = "";
        this.data = "";
        this.periodoAlugado = 0;
        this.valor = 0;
        this.devolvido = false;
    }
    
    public Locadora(String funcionario, String tituloFilme, String studioFilme, String rgCliente, String data, int periodoAlugado, float valor, boolean devolvido){
        this.funcionario = funcionario;
        this.tituloFilme = tituloFilme;
        this.studioFilme= studioFilme;
        this.rgCliente = rgCliente;
        this.data = data;
        this.periodoAlugado = periodoAlugado;
        this.valor = valor;
        this.devolvido = devolvido;
    }

    public static void setFuncionario(String funcionario) {
        Locadora.funcionario = funcionario;
    }

    public static void setTituloFilme(String tituloFilme) {
        Locadora.tituloFilme = tituloFilme;
    }
    
    public static void setStudioFilme(String studioFilme) {
        Locadora.studioFilme = studioFilme;
    }

    public static void setRgCliente(String rgCliente) {
        Locadora.rgCliente = rgCliente;
    }

    public static void setData(String data) {
        Locadora.data = data;
    }

    public static void setPeriodoAlugado(int periodoAlugado) {
        Locadora.periodoAlugado = periodoAlugado;
    }

    public static void setValor(float valor) {
        Locadora.valor = valor;
    }

    public static void setDevolvido(boolean devolvido) {
        Locadora.devolvido = devolvido;
    }

    public static String getRgCliente() {
        return rgCliente;
    }

    public static String getTituloFilme() {
        return tituloFilme;
    }
    
    public static String getStudioFilme() {
        return studioFilme;
    }

    public static String getFuncionario() {
        return funcionario;
    }

    public static String getData() {
        return data;
    }

    public static int getPeriodoAlugado() {
        return periodoAlugado;
    }

    public static float getValor() {
        return valor;
    }

    public static boolean getDevolvido() {
        return devolvido;
    }
       
    
    
}
