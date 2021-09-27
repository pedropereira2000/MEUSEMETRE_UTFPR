/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atividade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Pereira
 */

public class Localiza {
    public static void lerTexto(String dir){
        try{
            BufferedReader leitor = new BufferedReader(new FileReader(new File(dir)));
            
        }catch(IOException erro) {
            erro.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        System.out.println("Digite os arquivos que deseja encontrar separados: ");
        Scanner arg = new Scanner( System.in );
        String vet= " "+arg.nextLine();
        String div = "";
        div = vet.substring(vet.indexOf(" "),vet.lastIndexOf(".txt")+3);
        System.out.println(div);
        
        //while()
        
    }
}
