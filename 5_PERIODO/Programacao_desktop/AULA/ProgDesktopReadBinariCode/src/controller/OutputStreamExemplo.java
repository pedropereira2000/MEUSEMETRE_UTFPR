/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class OutputStreamExemplo {

    public static void main(String[] args) throws IOException {
        URL locator = new URL("http://paginapessoal.utfpr.edu.br/fabricio/imagens/utfpr.jpg");
        InputStream in = locator.openStream();
        FileOutputStream out = new FileOutputStream("C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\imgtest.jpg");
        int next = in.read();
        while (next != -1) {
            if(next == 30){
                out.write(0);
            }else{
                out.write(next);
            }
            next = in.read();
        }
        in.close();
        out.close();
    
    }
}
