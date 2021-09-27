/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class LeituraTexto02 {

    public static void main(String[] args) {
        try {
            BufferedReader leitor
                    = new BufferedReader(new FileReader(new File("C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\log\\entry.txt")));
            while (leitor.ready()) {
                String data = leitor.readLine();
                System.out.println(data);
            }
            leitor.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
}
