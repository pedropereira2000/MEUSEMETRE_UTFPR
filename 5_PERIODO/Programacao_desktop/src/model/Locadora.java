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
public class Locadora implements java.io.Serializable{
    private static long serialVersionUID = 1L;
    private String funcionario;
    private String tituloFilme;
    private String studioFilme;
    private String rgCliente;
    private String data;
    private int periodoAlugado;
    private float valor;
    private boolean devolvido;
    
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

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }
    
    public void setStudioFilme(String studioFilme) {
        this.studioFilme = studioFilme;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPeriodoAlugado(int periodoAlugado) {
        this.periodoAlugado = periodoAlugado;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public String getRgCliente() {
        return rgCliente;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }
    
    public String getStudioFilme() {
        return studioFilme;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public String getData() {
        return data;
    }

    public int getPeriodoAlugado() {
        return periodoAlugado;
    }

    public float getValor() {
        return valor;
    }

    public Boolean getDevolvido() {
        return devolvido;
    }
       
    
    
}
