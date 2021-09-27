/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * @author fabricio@utfpr.edu.br
 */
public class InputStreamExemplo {
    public static void main(String[] args) throws IOException {
        URL locator = new URL("http://paginapessoal.utfpr.edu.br/fabricio/imagens/utfpr.jpg");
        InputStream in = locator.openStream();
        int next = in.read();
        while (next != -1) {
            System.out.println(next);
            next = in.read();
        }
        in.close();
    }
}