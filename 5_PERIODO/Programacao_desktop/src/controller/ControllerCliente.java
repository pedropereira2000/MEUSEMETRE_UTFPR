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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author pedropereira
 */
public class ControllerCliente extends ControllerArquivo  {
    //Atributos utilizados na controller
    private Cliente cliente = null;
    private ArrayList<Cliente> clientes = null;
    private ObjectInputStream leitor = null;
    private ObjectOutputStream escritor = null;
    
    //Função para retornar uma lista de cliente
    public ArrayList<Cliente> getCliente(){
        return clientes;
    }
    
    //Função para atribuir um novo cliente
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    //Função para validar se o rg do cliente já existe
    public boolean validaCliente(){
        int pos = 0;
        
        while(pos < getCliente().size()){
            if(getCliente().get(pos).getRg().equals(cliente.getRg()))
                return false;
            
            pos++;
        }
        
        return true;
    }
    
    //Função para retornar a posição de um cliente a partir da comparação de rg
    public int encontraCliente(String rg){
        int pos = 0;
        while(pos < getCliente().size()){
            if(getCliente().get(pos).getRg().equals(rg))
                return pos;
            
            pos++;
        }
        return -1;
    }
    
    //Função para buscar clientes que tenha dados iguais com os passados para comparação
    //podendo ser nome, rg, endereço ou telefone
    public ArrayList<Integer> buscarClientes(String obj, String tipo){
        ArrayList<Integer> cliPos = new ArrayList<Integer>();
        int pos = 0;
        try{
            while(pos != getCliente().size()){
                if(tipo.equals("nome")&&getCliente().get(pos).getNome().equals(obj)){
                    cliPos.add(pos);
                }
                if(tipo.equals("rg")&&getCliente().get(pos).getRg().equals(obj)){
                    cliPos.add(pos);
                }
                if(tipo.equals("endereco")&&getCliente().get(pos).getEndereco().equals(obj)){
                    cliPos.add(pos);
                }
                if(tipo.equals("telefone")&&getCliente().get(pos).getTelefone().equals(obj)){
                    cliPos.add(pos);
                }                
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.out.println("não mais cliente que isso");
        } catch(NullPointerException err){
            System.err.println(err.getMessage() + "Erro ao buscar o cliente.");
        } catch(IndexOutOfBoundsException err){
            System.err.println(err.getMessage() + "Não encontrou clientes compátiveis");
        }
        return cliPos;
    }
    
    //Função para editar as informações de um cliente especifico
    public void editarCliente(Cliente cliente){
        int pos = 0;
        try{
            while(pos != getCliente().size()){
                if(getCliente().get(pos).getRg().equals(cliente.getRg())){
                    getCliente().set(pos, cliente);
                }
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getCliente().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getCliente().get(pos));
                    escritor.close();
                }
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.out.println("não mais cliente que isso");
        } catch(IOException err) {
            System.err.println(err.getMessage() + "Erro ao escrever arquivo binário.");
        }
    }
    
    //Função para excluir um cliente especifico 
    public void excluiCliente(Cliente cli){
        int pos = 0;
        try{
            while(pos != getCliente().size()){
                if(getCliente().get(pos).getRg().equals(cli.getRg())){
                    getCliente().remove(pos);
                }
                
                if(getCliente().size() == 0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    //escritor.writeObject(getCliente().get(pos));
                    escritor.close();
                    apagaArquivo();
                    break;
                }
                
                if(pos == getCliente().size()){
                    break;
                }
                
                if(pos==0){
                    escritor = CriaEscritorObjeto(arquivo, false);
                    escritor.writeObject(getCliente().get(pos));
                    escritor.close();
                }else{
                    escritor = CriaEscritorObjeto(arquivo, true);
                    escritor.writeObject(getCliente().get(pos));
                    escritor.close();
                }
                
                pos++;
            }
        } catch(ArrayIndexOutOfBoundsException err){
            System.out.println("não mais cliente que isso");
        } catch(IOException err) {
            System.err.println(err.getMessage() + "Erro ao escrever arquivo binário.");
        }
    }
    
    @Override
    public boolean ler() {
        clientes = new ArrayList<Cliente>();
        try {
            leitor = new ObjectInputStream(new FileInputStream(arquivo));
            Cliente cliente = null;
            do{
                cliente = (Cliente) leitor.readObject();
                if(cliente != null){
                    clientes.add(cliente);
                }
            }while(cliente != null);
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
                escritor.writeObject(cliente);
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
        } catch (IOException erro) {
            System.out.println("Erro ao criar arquivo. " + erro);
        }
        return out;
    }
}
