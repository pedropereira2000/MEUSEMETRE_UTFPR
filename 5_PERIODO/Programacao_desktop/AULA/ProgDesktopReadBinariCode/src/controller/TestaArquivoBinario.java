/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author fabricio@utfpr.edu.br
 */
public class TestaArquivoBinario {

    public static ObjectOutputStream CriaEscritorBinario(File arquivo) {
        ObjectOutputStream out = null;
        try {
            FileOutputStream fos = new FileOutputStream(arquivo);
            out = new ObjectOutputStream(fos);
        } catch (IOException erro) {
            System.out.println("Erro ao criar arquivo. " + erro);
        }
        return out;
    }

    public static ObjectInputStream CriaLeitorBinario(File arquivo) {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            ois = new ObjectInputStream(fis);
        } catch (IOException erro) {
            System.out.println("Erro ao ler arquivo. " + erro);
        }
        return ois;
    }

    public static void EscreveObjeto(ObjectOutputStream oos, Object obj, boolean flush) {
        try {
            oos.writeObject(obj);
            if (flush) {
                oos.flush();
            }
        } catch (IOException erro) {
            System.out.println("Erro na escrita. " + erro);
        }
    }

    public static Object LeObjeto(ObjectInputStream ois) {
        Object objlido = null;
        try {
            objlido = ois.readObject();
        } catch (ClassNotFoundException erro) {
            System.err.println("Classe nao encontrada. " + erro);
        } catch (java.io.EOFException erro) {
            System.err.println("Final de arquivo. " + erro);
        } catch (IOException erro) {
            System.err.println("Erro na leitura do objeto. " + erro);
        } finally {
            return objlido;
        }
    }

    public static void imprimeCliente(Cliente cli) {
        if (cli != null) {
            System.out.println(cli.toString());
        } else {
            System.out.println("Cliente nulo.");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente c1 = new Cliente("Zezinho", "3354-2000", 53);
        Cliente c2 = new Cliente("Huginho", "3354-2000", 50);
        Cliente c3 = new Cliente("Luizinho", "3354-2000", 51);
        File arquivo = new File("C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\clientes.obj");

        //inicialmente usando programacao sequencial 
        
        /*FileOutputStream fileout = new FileOutputStream(arquivo, false);
        ObjectOutputStream objout = new ObjectOutputStream(fileout);
        objout.writeObject(c1);
        objout.flush();
        objout.close();
        fileout.close();
        
        FileInputStream filein = new FileInputStream(arquivo);
        ObjectInputStream objin = new ObjectInputStream(filein);
        Cliente leitura1 = (Cliente) objin.readObject();
        System.out.println(leitura1.toString());
        
        objin.close();
        filein.close();*/
        
        //Agora usando POO
        
        ObjectOutputStream saida = CriaEscritorBinario(arquivo);
        saida.writeObject(c1);
        saida.writeObject(c2);
        saida.writeObject(c3);
        saida.close();
        
        ObjectInputStream entrada = CriaLeitorBinario(arquivo);
        Cliente cli1 = (Cliente) LeObjeto(entrada);
        Cliente cli2 = (Cliente) LeObjeto(entrada);
        Cliente cli3 = (Cliente) LeObjeto(entrada);
        imprimeCliente(cli1);
        imprimeCliente(cli2);
        imprimeCliente(cli3);
        entrada.close();     
        
    }
}
