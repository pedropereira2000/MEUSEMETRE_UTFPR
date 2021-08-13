/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Cliente;
import model.Filmes;

/**
 *
 * @author pedropereira
 */
public class ControllerFilmes extends ControllerArquivo {
    //Atributos utilizados na controller
    private Filmes filme = null;
    private ArrayList<Filmes> filmes = null;
    private ObjectInputStream leitor = null;
    private ObjectOutputStream escritor = null;
    
    //Função para retornar uma lista de filmes
    public ArrayList<Filmes> getFilme(){
        return filmes;
    }
    
    //Função para atribuir um novo filme
    public void setFilme(Filmes filme){
        this.filme = filme;
    }
    
    //Função para validar se já existe um filme cadastrado com mesmo titulo e studio
    public boolean validaFilme(){
        int pos = 0;
        
        while(pos < getFilme().size()){
            if(getFilme().get(pos).getTitulo().equals(filme.getTitulo())&&getFilme().get(pos).getStudio().equals(filme.getStudio()))
                return false;
            
            pos++;
        }
        
        return true;
    }
    
    //Função para retornar a posição de um filme a partir da comparação do titulo e studio
    public int encontraFilme(String titulo, String studio){
        int pos = 0;
        while(pos < getFilme().size()){
            if(getFilme().get(pos).getTitulo().equals(titulo)&&getFilme().get(pos).getStudio().equals(studio))
                return pos;
            
            pos++;
        }
        return -1;
    }
    
    //Função para buscar filmes que tenha dados iguais com os passados para comparação
    //podendo ser nome, rg, endereço ou telefone
    public ArrayList<Integer> buscarFilme(String obj, String tipo){
        ArrayList<Integer> filmPos = new ArrayList<Integer>();
        int pos = 0;
        try{
            while(pos != getFilme().size()){
                if(tipo.equals("titulo")&&getFilme().get(pos).getTitulo().equals(obj)){
                    filmPos.add(pos);
                }
                if(tipo.equals("studio")&&getFilme().get(pos).getStudio().equals(obj)){
                    filmPos.add(pos);
                }
                if(tipo.equals("genero")&&getFilme().get(pos).getGenero().equals(obj)){
                    filmPos.add(pos);
                }
                if(tipo.equals("duracao")&&getFilme().get(pos).getDuracao() == Integer.parseInt(obj)){
                    filmPos.add(pos);
                }                
                if(tipo.equals("classificacao")&&getFilme().get(pos).getClassificacao().equals(obj)){
                    filmPos.add(pos);
                }                
                if(tipo.equals("alugado")&&getFilme().get(pos).getAlugado().toString().equals(obj)){
                    filmPos.add(pos);
                }                
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println("não mais Filmes que isso");
        } catch(NullPointerException err){
            System.err.println(err.getMessage() + "Erro ao buscar o Filme.");
        } catch(IndexOutOfBoundsException err){
            System.err.println(err.getMessage() + "Não encontrou filmes compátiveis");
        } catch(NumberFormatException err){
            System.err.println(err.getMessage() + "Não foi inserido numeros");
        }
        return filmPos;
    }
    
    //Função para editar as informações de um filme especifico
    public void editarFilme(Filmes filme){
        int pos = 0;
        try{
            while(pos != getFilme().size()){
                if(getFilme().get(pos).getTitulo().equals(filme.getTitulo())&&getFilme().get(pos).getStudio().equals(filme.getStudio())){
                    getFilme().set(pos, filme);
                }
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getFilme().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getFilme().get(pos));
                    escritor.close();
                }
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println("não mais cliente que isso");
        } catch(IOException err) {
            System.err.println(err.getMessage() + "Erro ao escrever arquivo binário.");
        }
    }
    
    //Função para excluir um filme especifico 
    public void excluiFilme(Filmes filme){
        int pos = 0;
        try{
            while(pos != getFilme().size()){
                if(getFilme().get(pos).getTitulo().equals(filme.getTitulo())&&getFilme().get(pos).getStudio().equals(filme.getStudio())){
                    getFilme().remove(pos);
                }
                
                if(getFilme().size() == 0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.close();
                    apagaArquivo();
                    break;
                }
                
                if(pos == getFilme().size()){
                    break;
                }
                
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getFilme().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getFilme().get(pos));
                    escritor.close();
                }
                
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println("não mais cliente que isso");
        } catch(IOException err) {
            System.err.println(err.getMessage() + "Erro ao escrever arquivo binário.");
        }
    }
    
    @Override
    public boolean ler() {
        filmes = new ArrayList<Filmes>();
        try {
            leitor = new ObjectInputStream(new FileInputStream(arquivo));
            Filmes filme = null;
            do{
                filme = (Filmes) leitor.readObject();
                if(filme != null){
                    filmes.add(filme);
                }
            }while(filme != null);
            leitor.close();
            return true;
        } catch (ClassNotFoundException erro) {
            //erro.printStackTrace();
            System.err.println(erro.getMessage() + "Classe não encontrada.");
            return false;
        } catch (EOFException erro) {
            System.err.println(erro.getMessage() + "Fim do arquivo.");
            return true;
        } catch (IOException erro) {
            erro.printStackTrace();
            System.err.println(erro.getMessage() + "Erro ao ler arquivo binário.");
            return false;
        }
        
    }
    
    @Override
    public boolean escrever(boolean append) {
        if (arquivo != null) {
            try {
                escritor = CriaEscritorObjeto(arquivo, append);
                escritor.writeObject(filme);
                escritor.close();
                return true;
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao escrever arquivo binário.");
                return false;
            }
        } else {
            return false;
        }
    }
    
    public static ObjectOutputStream CriaEscritorObjeto(File arquivo, boolean append) {
        ObjectOutputStream out = null;
        try {
            if (append) {
                FileOutputStream fos = new FileOutputStream(arquivo, append);
                out = new ObjectOutputStream(fos) {

                    @Override
                    protected void writeStreamHeader() {
                        // do not write a header
                    }
                };
            } else {
                FileOutputStream fos = new FileOutputStream(arquivo, append);
                out = new ObjectOutputStream(fos);
            }
        } catch (IOException err) {
            System.err.println("Erro ao criar arquivo. " + err);
        }
        return out;
    }
}
