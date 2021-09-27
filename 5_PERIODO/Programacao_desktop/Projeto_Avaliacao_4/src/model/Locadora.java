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
public class Locadora{
    private int id;
    private String funcionario;
    private String tituloFilme;
    private String generoFilme;
    private String studioFilme;
    private String cpfCliente;
    private String data;
    private String dataDevolucao;
    private int periodoAlugado;
    private float valor;
    private boolean devolvido;
    
    public Locadora(){
        this.id = 0;
        this.funcionario = "";
        this.tituloFilme = "";
        this.studioFilme = "";
        this.generoFilme = "";
        this.cpfCliente = "";
        this.data = "";
        this.dataDevolucao = "";
        this.periodoAlugado = 0;
        this.valor = 0;
        this.devolvido = false;
    }
    
    public Locadora(int id, String funcionario, String tituloFilme, String generoFilme,  String studioFilme, String cpfCliente, String data, String dataDevolucao, int periodoAlugado, float valor, boolean devolvido){
        this.id = id;
        this.funcionario = funcionario;
        this.tituloFilme = tituloFilme;
        this.studioFilme = studioFilme;
        this.generoFilme = generoFilme;
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.dataDevolucao = dataDevolucao;
        this.periodoAlugado = periodoAlugado;
        this.valor = valor;
        this.devolvido = devolvido;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public void setGeneroFilme(String generoFilme) {
        this.generoFilme = generoFilme;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
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

    public int getId() {
        return id;
    }
    
    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }
    
    public String getStudioFilme() {
        return studioFilme;
    }
    
    public String getGeneroFilme() {
        return generoFilme;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public String getData() {
        return data;
    }
    
    public String getDataDevolucao() {
        return dataDevolucao;
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
