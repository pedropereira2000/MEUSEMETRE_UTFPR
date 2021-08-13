/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class EscritaTexto01 {

    public static void main(String[] args) {
        Writer fileWriter = null;
        try {
            File arquivo = new File("C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\log\\log.txt");
            fileWriter = new FileWriter(arquivo, false);
            fileWriter.write("Hello world 1!");
            fileWriter.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
}
