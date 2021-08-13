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
public class Cliente {
    static String nome;
    static String rg;
    static String endereco;
    static String telefone;
    
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
    
    public static void setNome(String nome){
        Cliente.nome = nome;
    }

    public static void setRg(String rg) {
        Cliente.rg = rg;
    }

    public static void setEndereco(String endereco) {
        Cliente.endereco = endereco;
    }

    public static void setTelefone(String telefone) {
        Cliente.telefone = telefone;
    }

    public static String getNome() {
        return nome;
    }

    public static String getRg() {
        return rg;
    }

    public static String getEndereco() {
        return endereco;
    }

    public static String getTelefone() {
        return telefone;
    }
    
}
