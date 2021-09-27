/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pedropereira
 */
public class ControllFile {
    
    public static String buscaArquivo(String nomeArquivo){
        //var aux = new ArrayList<String> ();
        String result = "";
        if(!nomeArquivo.equals("")){
            if(!ControllFile.leituraPasta("/home/pedropereira/Desktop", nomeArquivo).equals("")){
                result=leituraArquivo(leituraPasta("/home/pedropereira/Desktop", nomeArquivo));
            }
            /*    
                //aux.add(ControllFile.leituraPasta("/home/pedropereira/Desktop", nomeArquivo));
            if(!ControllFile.leituraPasta("/home/pedropereira/Documents", nomeArquivo).equals(""))
                //aux.add(ControllFile.leituraPasta("/home/pedropereira/Documents", nomeArquivo));
            if(!ControllFile.leituraPasta("/home/pedropereira/Downloads", nomeArquivo).equals(""))
                //aux.add(ControllFile.leituraPasta("/home/pedropereira/Downloads", nomeArquivo));
            if(!ControllFile.leituraPasta("/home/pedropereira/Music", nomeArquivo).equals(""))
                //aux.add(ControllFile.leituraPasta("/home/pedropereira/Music", nomeArquivo));
            if(!ControllFile.leituraPasta("/home/pedropereira/Videos", nomeArquivo).equals(""))
                //aux.add(ControllFile.leituraPasta("/home/pedropereira/Videos", nomeArquivo));
            if(!ControllFile.leituraPasta("/home/pedropereira/Pictures", nomeArquivo).equals(""))
                //aux.add(ControllFile.leituraPasta("/home/pedropereira/Pictures", nomeArquivo));*/
        }
//else{
           /* String conteudo = "Não foram informados os campos da pasta e tipo do arquivo";
            //aux.add(conteudo);
        }
        **/
        
        return result;
    }
    
    public static String leituraPasta (String path, String arqvo) {
        String aux = "";
        String defaultPath = path;
        File dir = new File(defaultPath);
        String[] listagem = dir.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);
            if(filho.getName().endsWith(arqvo)&&filho.isFile()){            
                aux += (filho.getAbsolutePath()+"\n");
            }
            if(filho.isDirectory()==true){
                leituraPasta(dir +  "/" + filho.getName(), arqvo);
            }
        }
        return aux;
    }
    
    public static String leituraArquivo(String path) {
        String str = path;
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(new File(path)));
            while (leitor.ready()) {
                String data = leitor.readLine();
                System.out.println(data);
                return data;
            }
            leitor.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
        
        return "não foi possivel fazer a leitura";
    }
}
