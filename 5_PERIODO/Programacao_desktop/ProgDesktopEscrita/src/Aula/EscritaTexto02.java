/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class EscritaTexto02 {

    public void leTexto(String filename) throws FileNotFoundException, IOException {
        File entrada = new File(filename);
        FileReader leitor = new FileReader(entrada);
        leitor.read();
        leitor.close();
    }

    public void escreveTexto(String filename) throws FileNotFoundException, IOException {
        File entrada = new File(filename);
        FileWriter escritor = new FileWriter(entrada, false);
        escritor.write("Hello world!");
        escritor.close();
    }

    public static void main(String[] args) {
        String filename1 = "C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\log\\log.txt";
        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter(new File(filename1), true));
            fileWriter.write("Hello world 2!");
            fileWriter.newLine();
            fileWriter.write("Hello world !");
            fileWriter.newLine();
            fileWriter.flush();
            fileWriter.close();
            //EscritaTexto02 obj = new EscritaTexto02();
            //obj.leTexto("/home/fabricio/logqqqqq.txt");
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado.");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
}