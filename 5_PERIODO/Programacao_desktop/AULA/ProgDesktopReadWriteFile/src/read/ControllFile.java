/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author pedropereira
 */
public class ControllFile {
    
    public static String buscaArquivo(String nomeArquivo){
        String result = "";
        
        String[] textoSeparado = nomeArquivo.split(" ");
                
        if(!nomeArquivo.equals("")){
            for(int i = 0; i < textoSeparado.length;i++){
                String arq = (textoSeparado[i]);
                result+=arq+": "+leituraArquivo(leituraPasta("C:\\Users\\Pedro Pereira\\Desktop", arq));
                result+="\n";
            }
        }
//else{
           /* String conteudo = "Não foram informados os campos da pasta e tipo do arquivo";
            //aux.add(conteudo);
        }
        **/
        
        return result;
    }
    
    public static String leituraPasta (String path, String arqvo) {
        String auxDf = "";
        String defaultPath = path;
        File dir = new File(defaultPath);
        String[] listagem = dir.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);
            if(filho.getName().equals(arqvo)&&filho.isFile()){            
                auxDf+=filho.getAbsolutePath();
            }
            if(filho.isDirectory()==true){
                auxDf+=leituraPasta(dir +  "\\" + filho.getName(), arqvo);
            }            
        }
        return auxDf;
    }
    
    public static String leituraArquivo(String path) {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(new File(path)));
            while (leitor.ready()) {
                String data = leitor.readLine();
                return data;
            }
            leitor.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
        
        return "não foi possivel fazer a leitura";
    }
}
