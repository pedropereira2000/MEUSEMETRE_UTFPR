package arquivos;

import java.io.File;
import java.io.IOException;

/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class TestaArquivo01 {

    public static void main(String[] args) throws IOException{
        
        String str = System.getProperty("user.home");
        
        File dirHome = new File(str);
        
        File subDir = new File(dirHome, "tempe03");
        
        File arq = new File(subDir, "log.txt");
        
        if (subDir.mkdir()) {
            System.out.println("Pasta criada com sucesso.");
        } else if (subDir.exists()) {
            System.out.println("Pasta já existe.");
        } else if (subDir.canRead()) {
            System.out.println("Consigo ler.");
        }else{
            System.out.println("Não consigo ler. Pasta não foi criada.");
        }
        if(arq.createNewFile()){
            System.out.println("Arquivo criado com sucesso.");
        }else{
            System.out.println("Arquivo não foi criado.");
        }
        System.out.println("Caminho pasta = "   + subDir.getAbsolutePath());
        System.out.println("Caminho arquivo = " + arq.getAbsolutePath());
    }
}