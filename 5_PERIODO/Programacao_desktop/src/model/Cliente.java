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
public class Cliente implements java.io.Serializable {
    private static long serialVersionUID = 1L;
    private String nome;
    private String rg;
    private String endereco;
    private String telefone;
    
    public Cliente(){
        this.nome = "";
        this.rg = "";
        this.endereco = "";
        this.telefone = "";
    }
    
    public Cliente(String nome, String rg, String endereco, String telefone){
        this.nome = nome;
        this.rg = rg;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    
}
