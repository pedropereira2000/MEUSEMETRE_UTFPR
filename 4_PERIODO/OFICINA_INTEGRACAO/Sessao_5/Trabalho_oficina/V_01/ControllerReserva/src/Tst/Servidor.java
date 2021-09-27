package Tst;


import Tst.Permissao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phili
 */
public abstract class Servidor {
    private String nome;
    private int siape;
    private String email;
    private int ramal;
    private String senha;
    Permissao perm;
    
    public Servidor(){
        nome = "";
        siape = 0;
        email = "";
        ramal = 0;
        senha = "";
        perm = new Permissao();
    }
    
    public Servidor(String nome, int siape, String email, int ramal, String senha, Permissao perm){
        this.nome = nome;
        this.siape = siape;
        this.email = email;
        this.ramal = ramal;
        this.senha = senha;
        this.perm = perm;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getSiape(){
        return siape;
    }
    
    public void setSiape(int siape){
        this.siape = siape;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public int getRamal(){
        return ramal;
    }
    
    public void setRamal(int Ramal){
        this.ramal = ramal;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public Permissao getPerm(){
        return perm;
    }
    
    public void setPerm(Permissao perm){
        this.perm = perm;
    }
}
