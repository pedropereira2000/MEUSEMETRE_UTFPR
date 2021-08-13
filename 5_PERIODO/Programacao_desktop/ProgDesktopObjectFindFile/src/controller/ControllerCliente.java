/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author pedropereira
 */
public class ControllerCliente extends ControllerArquivo  {
    private Cliente clientes = new Cliente();
    private String texto = null;
    private BufferedReader leitor = null;
    private BufferedWriter escritor = null;
    
    public Cliente getCliente(int cont){
      
      return null;
    }
    
    public void setCliente(String nome, String rg, String endereco, String telefone){
      clientes = new Cliente(nome, rg, endereco, telefone);
      
    }
    
    public boolean escreverEdit(boolean append){
        if (arquivo != null) {
            try {
                escritor = new BufferedWriter(new FileWriter(arquivo, append));
                escritor.write(this.texto);
                escritor.close();
                //this.texto += "\n{\n\tnome: "+clientes.getNome()+"\n\trg: "+clientes.getRg()+"\n\tendereço: "+clientes.getEndereco()+"\n\ttelefone: "+clientes.getTelefone()+"\n}";
                //clientes = null;
                return true;
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void excluiCliente(String rgExc){
        String txt = this.texto;
        String orig = "";
        String rest = "";
        int indexIni = 0;
        int indexFim = 0;

        while(true){
            indexIni = txt.indexOf("rg:");
            indexFim = txt.indexOf("endereço");
            String rg = txt.substring(indexIni+4, indexFim-2);
            if(rg.equals(rgExc)){
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                orig = txt.substring(indexIni);
                indexIni = txt.indexOf("}");
                indexFim = txt.indexOf("{");
                rest = txt.substring(indexIni+2);
                this.texto=(texto.replace(orig, rest)).trim().replace("\\s{2,}", " ");
                escreverEdit(false);
                break;
            }
            indexFim = txt.indexOf("}");
            txt=txt.substring(indexFim+2);
            if(txt.isEmpty())
               break;
        }
    }
    
    public void editCliente(String nomeNovo, String rgNovo, String enderecoNovo, String telefoneNovo){
        String txt = this.texto;
        String orig = "";
        int indexIni = 0;
        int indexFim = 0;

        while(true){
            indexIni = txt.indexOf("rg:");
            indexFim = txt.indexOf("endereço");
            String rg = txt.substring(indexIni+4, indexFim-2);
            if(rg.equals(rgNovo)){
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                orig = txt.substring(indexIni, indexFim+1);
                indexIni = txt.indexOf("nome:");
                indexFim = txt.indexOf("rg");
                String nome = txt.substring(indexIni+6, indexFim-2);
                txt=txt.replace(nome, nomeNovo);
                indexIni = txt.indexOf("rg:");
                indexFim = txt.indexOf("endereço");
                String rgReplace = txt.substring(indexIni+4, indexFim-2);
                txt=txt.replace(rgReplace, rg);
                indexIni = txt.indexOf("endereço:");
                indexFim = txt.indexOf("telefone");
                String endereco = txt.substring(indexIni+10, indexFim-2);
                txt=txt.replace(endereco, enderecoNovo);
                indexIni = txt.indexOf("telefone:");
                indexFim = txt.indexOf("}");
                String telefone = txt.substring(indexIni+10, indexFim-1);
                txt=txt.replace(telefone, telefoneNovo);
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                txt = txt.substring(indexIni, indexFim+1);
                this.texto=texto.replace(orig, txt).trim().replace("\\s{2,}", " ");
                escreverEdit(false);
                break;
            }
            indexFim = txt.indexOf("}");
            txt=txt.substring(indexFim+2);
            if(txt.isEmpty())
               break;
        }
        
        //return "Edição feita com sucesso";
    }
    
    public void buscaCli(String busc, String tipo){
        String txt = this.texto;
        String busca = "";
        int indexIni = 0;
        int indexFim = 0;
        
        if(tipo.equals("nome")){
            while(true){
                indexIni = txt.indexOf("nome:");
                indexFim = txt.indexOf("rg");
                String nome = txt.substring(indexIni+6, indexFim-2);
                if(nome.equals(busc)){
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("endereco")){
            while(true){
                indexIni = txt.indexOf("endereço:");
                indexFim = txt.indexOf("telefone");
                String endereco = txt.substring(indexIni+10, indexFim-2);
                if(endereco.equals(busc)){
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("telefone")){
            while(true){
                indexIni = txt.indexOf("telefone:");
                indexFim = txt.indexOf("}");
                String telefone = txt.substring(indexIni+10, indexFim-1);
                if(telefone.equals(busc)){
                    System.out.println(txt);
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        this.texto=busca;
    }
    
    public Cliente getCliText(String comparar){
        String txt = this.texto;
        Cliente cli = null;
        int indexIni = 0;
        int indexFim = 0;
        ArrayList<String> splitTxt =  new ArrayList<>();
        
        if(txt.equals("")){
            return cli;
        }else{
            while(true){
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                splitTxt.add(txt.substring(indexIni, indexFim+1));
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
            
            for(int i=0; i<splitTxt.size(); i++){
                indexIni = splitTxt.get(i).indexOf("rg:");
                indexFim = splitTxt.get(i).indexOf("endereço");
                String rg = splitTxt.get(i).substring(indexIni+4, indexFim-2);
                if(rg.equals(comparar)){
                    indexIni = splitTxt.get(i).indexOf("nome:");
                    indexFim = splitTxt.get(i).indexOf("rg");
                    String nome = splitTxt.get(i).substring(indexIni+6, indexFim-2);
                    indexIni = splitTxt.get(i).indexOf("endereço:");
                    indexFim = splitTxt.get(i).indexOf("telefone");
                    String endereco = splitTxt.get(i).substring(indexIni+10, indexFim-2);
                    indexIni = splitTxt.get(i).indexOf("telefone:");
                    indexFim = splitTxt.get(i).indexOf("}");
                    String telefone = splitTxt.get(i).substring(indexIni+10, indexFim-1);
                    cli = new Cliente(nome,rg,endereco,telefone);
                    break;
                }
            }
            
        }
        
        return cli;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    @Override
    public boolean ler() {
        StringBuilder line = new StringBuilder();
        try {
            leitor = new BufferedReader(new FileReader(arquivo));
            while (leitor.ready()) {
                line.append(leitor.readLine()).append("\n");
            }
            leitor.close();
            setTexto(line.toString());
            return true;
        } catch (FileNotFoundException erro) {
            //erro.printStackTrace(); //usado para debug
            System.err.println(erro.getMessage() + "Arquivo não encontrado.");
            return false;
        } catch (IOException erro) {
            System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
            return false;
        }
    }
    
    @Override
    public boolean escrever(boolean append){
        if (arquivo != null) {
            try {
                if(!texto.equals("")){
                    escritor = new BufferedWriter(new FileWriter(arquivo, append));
                    escritor.write("\n{\n\tnome: "+clientes.getNome()+"\n\trg: "+clientes.getRg()+"\n\tendereço: "+clientes.getEndereco()+"\n\ttelefone: "+clientes.getTelefone()+"\n}");
                    escritor.close();
                    this.texto += "\n{\n\tnome: "+clientes.getNome()+"\n\trg: "+clientes.getRg()+"\n\tendereço: "+clientes.getEndereco()+"\n\ttelefone: "+clientes.getTelefone()+"\n}";
                    //clientes = null;
                    return true;
                }else{
                    escritor = new BufferedWriter(new FileWriter(arquivo, append));
                    escritor.write("{\n\tnome: "+clientes.getNome()+"\n\trg: "+clientes.getRg()+"\n\tendereço: "+clientes.getEndereco()+"\n\ttelefone: "+clientes.getTelefone()+"\n}");
                    escritor.close();
                    this.texto += "{\n\tnome: "+clientes.getNome()+"\n\trg: "+clientes.getRg()+"\n\tendereço: "+clientes.getEndereco()+"\n\ttelefone: "+clientes.getTelefone()+"\n}";
                    //clientes = null;
                    return true;
                }
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }
}
