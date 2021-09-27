/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Event;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.Cliente;
import model.Filmes;
import model.Locadora;

/**
 *
 * @author pedropereira
 */
public class ControllerLocadora  extends ControllerArquivo {
    //Atributos utilizados na controller
    private Locadora locadora = null;
    private ArrayList<Locadora> locadoras = null;
    private ObjectInputStream leitor = null;
    private ObjectOutputStream escritor = null;
    
    //Função para retornar uma lista de locacoes
    public ArrayList<Locadora> getLocadora(){
        return locadoras;
    }
    
    //Função para atribuir uma nova locacao
    public void setLocadora(Locadora locadora){
        this.locadora = locadora;
    }
    
    //Função para validar se já existe uma locacao cadastrada com mesmo titulo, studio e se ainda não foi devolvida
    public boolean validaLocadora(){
        int pos = 0;
        
        while(pos < getLocadora().size()){
            if(getLocadora().get(pos).getTituloFilme().equals(locadora.getTituloFilme())&&getLocadora().get(pos).getStudioFilme().equals(locadora.getStudioFilme())&&getLocadora().get(pos).getDevolvido()==false)
                return false;
            
            pos++;
        }
        
        return true;
    }
    
    //Função para retornar a posição de uma locacao a partir da comparação do titulo, rg-cli e data
    public int encontraLocadora(String titulo, String rg, String data){
        int pos = 0;
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //date = formato.parse(getLocadora().get(pos).getData());
        try{
            while(pos < getLocadora().size()){
                if(getLocadora().get(pos).getTituloFilme().equals(titulo)&&getLocadora().get(pos).getRgCliente().equals(rg)&&getLocadora().get(pos).getData().equals(data))
                    return pos;
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println(err.getMessage() + " não localizou locação");
        }
        return -1;
    }
    
    //Função para buscar locacao que tenha dados iguais com os passados para comparação
    //podendo ser funcionario, titulo-filme, studio-filme, rg-cli, data, periodo, valor ou devolvido
    public ArrayList<Integer> buscarLocadora(String obj, String tipo){
        ArrayList<Integer> locPos = new ArrayList<Integer>();
        int pos = 0;
        System.out.println(tipo);
        try{
            while(pos != getLocadora().size()){
                if(tipo.equals("funcionario")&&getLocadora().get(pos).getFuncionario().equals(obj)){
                    locPos.add(pos);
                }
                if(tipo.equals("titulo")&&getLocadora().get(pos).getTituloFilme().equals(obj)){
                    locPos.add(pos);
                }
                if(tipo.equals("studio")&&getLocadora().get(pos).getStudioFilme().equals(obj)){
                    locPos.add(pos);
                }
                if(tipo.equals("rg")&&getLocadora().get(pos).getRgCliente().equals(obj)){
                    locPos.add(pos);
                }                
                if(tipo.equals("data")&&getLocadora().get(pos).getData().equals(obj)){
                    locPos.add(pos);
                }                
                if(tipo.equals("periodo")&&getLocadora().get(pos).getPeriodoAlugado() == Integer.parseInt(obj)){
                    locPos.add(pos);
                }                
                if(tipo.equals("valor")&&getLocadora().get(pos).getValor()== Float.parseFloat(obj)){
                    locPos.add(pos);
                }                
                if(tipo.equals("devolucao")&&getLocadora().get(pos).getDevolvido().toString().equals(obj)){
                    locPos.add(pos);
                }                
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println("não há mais locacao que isso");
        } catch(NullPointerException err){
            System.err.println(err.getMessage() + "Erro ao buscar a locacao.");
        } catch(IndexOutOfBoundsException err){
            System.err.println(err.getMessage() + "Não encontrou locacao compátiveis");
        }
        return locPos;
    }
    
    //Função para editar as informações de uma locacao especifica
    public void editarLocadora(Locadora locadora){
        int pos = 0;
        try{
            while(pos != getLocadora().size()){
                if(getLocadora().get(pos).getRgCliente().equals(locadora.getRgCliente())&&getLocadora().get(pos).getData().equals(locadora.getData())){
                    getLocadora().set(pos, locadora);
                }
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getLocadora().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getLocadora().get(pos));
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
    
    //Função para excluir uma locacao especifica 
    public void excluiLocadora(Locadora locadora){
        int pos = 0;
        try{
            while(pos != getLocadora().size()){
                if(getLocadora().get(pos).getTituloFilme().equals(locadora.getTituloFilme())&&getLocadora().get(pos).getStudioFilme().equals(locadora.getStudioFilme())&&getLocadora().get(pos).getRgCliente().equals(locadora.getRgCliente())&&getLocadora().get(pos).getData().equals(locadora.getData())){
                    getLocadora().remove(pos);
                }
                
                if(getLocadora().size() == 0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.close();
                    apagaArquivo();
                    break;
                }
                
                if(pos == getLocadora().size()){
                    break;
                }
                
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getLocadora().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getLocadora().get(pos));
                    escritor.close();
                }
                
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println("não mais cliente que isso");
        } catch(IOException err) {
            System.err.println(err.getMessage() + "Erro ao escrever arquivo binário.");
        } catch(NullPointerException err){
            System.err.println(err.getMessage() + " erro ao excluir o cliente");
        }
    }
    
    //Função para setar um filma que foi alugado como devolvido
    public boolean setDevolucao(Locadora locadora){
        int pos = 0;
        try{
            while(pos != getLocadora().size()){
                if(getLocadora().get(pos).getTituloFilme().equals(locadora.getTituloFilme())&&getLocadora().get(pos).getStudioFilme().equals(locadora.getStudioFilme())&&getLocadora().get(pos).getRgCliente().equals(locadora.getRgCliente())&&getLocadora().get(pos).getData().equals(locadora.getData())&&getLocadora().get(pos).getDevolvido()==false){
                    locadora.setDevolvido(true);
                    getLocadora().set(pos, locadora);
                }
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getLocadora().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getLocadora().get(pos));
                    escritor.close();
                }
                pos++;
            }
            return true;
        } catch(ArrayIndexOutOfBoundsException err){
            System.err.println("não mais cliente que isso");
            return false;
        } catch(IOException err) {
            System.err.println(err.getMessage() + "Erro ao escrever arquivo binário.");
            return false;
        } catch(NullPointerException err){
            System.err.println(err.getMessage() + " erro ao excluir o cliente");
            return false;
        }
    }
    
    @Override
    public boolean ler() {
        locadoras = new ArrayList<Locadora>();
        try {
            leitor = new ObjectInputStream(new FileInputStream(arquivo));
            Locadora locadora = null;
            do{
                locadora = (Locadora) leitor.readObject();
                if(locadora != null){
                    locadoras.add(locadora);
                }
            }while(locadora != null);
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
                escritor.writeObject(locadora);
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
