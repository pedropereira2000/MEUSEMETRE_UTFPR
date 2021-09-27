/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author fabricio@utfpr.edu.br
 */
public abstract class ControllerArquivo {
    protected File arquivo = null;
    public abstract boolean ler();
    public abstract boolean escrever(boolean append);
    
    /**
     * @return the arquivo
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * @param TextoBotao o texto para o botão de escolha do usuário
     */
    public void setArquivo(String TextoBotao){
        arquivo = null;
        
        //Pegar apartir da pasto do programa e criar pasta para o database, 
        //se não ouver e o arquivo também
        
        String pastaInicial = System.getProperty("user.dir");
        String dir = (pastaInicial+"\\database");
        
        //Verificando se existe a pasta para armazenar os arquivos
        if(!new File(dir).exists())
            //se a pasta não existe é criada
            new File(dir).mkdir();
        try{
            if(TextoBotao.equals("clientes")){
                arquivo = new File(dir+"\\clientes.obj");
                //Verificando se o arquivo para save existe
                if(!arquivo.exists()){
                    //arquivo não existe, criando o arquivo
                    arquivo.createNewFile();
                }
            }
            if(TextoBotao.equals("filmes")){
                arquivo = new File(dir+"\\filmes.obj");
                //Verificando se o arquivo para save existe
                if(!arquivo.exists()){
                    //arquivo não existe, criando o arquivo
                    arquivo.createNewFile();
                }
            }
            if(TextoBotao.equals("locadoras")){
                arquivo = new File(dir+"\\locadoras.obj");
                //Verificando se o arquivo para save existe
                if(!arquivo.exists()){
                    //arquivo não existe, criando o arquivo
                    arquivo.createNewFile();
                }
            }
        }catch(IOException err){
            System.err.println(err.getMessage() + "Erro ao atribuir o  arquivo.");
        }
    }
    
    public void apagaArquivo() {
        File test = new File(System.getProperty("user.dir")+"\\database"+"\\clientes.obj");
        test.delete();
        System.out.println(test.exists());
    }
}
