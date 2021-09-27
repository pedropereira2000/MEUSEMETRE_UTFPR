/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
    public void setArquivo(String TextoBotao) {
        arquivo = null;
        String pastainicial = System.getProperty("user.home");
        if(TextoBotao.equals("clientes")){
            arquivo = new File(pastainicial+"/Desktop/UTFPR/Programacao_desktop/arquivos/clientes.txt");    
        }
        if(TextoBotao.equals("filmes")){
            arquivo = new File(pastainicial+"/Desktop/UTFPR/Programacao_desktop/arquivos/filmes.txt");
        }
        if(TextoBotao.equals("locadora")){
            arquivo = new File(pastainicial+"/Desktop/UTFPR/Programacao_desktop/arquivos/locadora.txt");
        }
    }
    
    public void apagaArquivo() {
        this.arquivo = null;    
    }
}
