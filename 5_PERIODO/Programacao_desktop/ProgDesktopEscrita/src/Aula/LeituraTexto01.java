/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class LeituraTexto01 {

    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            File entrada = new File("C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\log\\log.txt");
            if (entrada.exists()) {
                fileReader = new FileReader(entrada);
                int data = fileReader.read();
                while (data != -1) {
                    char c = (char) data;
                    System.out.println(data + " = " + c);
                    data = fileReader.read();
                }
                fileReader.close();
            }else{
                System.out.println(entrada.getName()+" Arquivo não existe!");
            }
        } catch (IOException erro) {
            erro.printStackTrace();
        }

    }
}