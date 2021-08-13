package arquivos;

import java.io.File;
import java.util.Date;
import java.util.Scanner;
/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class TestaArquivo03 {

    public static void leituraPasta(File dir) {
        int cont_png = 0;
        String[] listagem = dir.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);
            System.out.println("Nome: "+ filho.getName());
            
            if(filho.getName().endsWith("png") || filho.getName().endsWith("jpg") || filho.getName().endsWith("jpeg")){
                cont_png++;
            }
            
            System.out.println(filho.getAbsolutePath());
            System.out.println("É diretorio? " + filho.isDirectory());
            System.out.println("É arquivo? " + filho.isFile());
            System.out.println("Tamanho = " + filho.length() + " bytes.");
            Date aux = new Date(filho.lastModified());
            System.out.println("Ultima alteração = " + aux+"\n\n");
        }
        System.out.println("Quantidade arquivos PNG: "+cont_png);
    }

    public static String leituraPasta (String path) {
        String aux = "";
        int cont_png = 0;
        File dir = new File(path);
        String[] listagem = dir.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);
            aux += ("Nome: "+ filho.getName()+"\n");
            
            if(filho.getName().endsWith("png") || filho.getName().endsWith("jpg") || filho.getName().endsWith("jpeg")){
                cont_png++;
            }
            
            aux += (filho.getAbsolutePath()+"\n");
            aux += ("É diretorio? " + filho.isDirectory()+"\n");
            aux += ("É arquivo? " + filho.isFile()+"\n");
            aux += ("Tamanho = " + filho.length() + " bytes."+"\n");
            Date auxdata = new Date(filho.lastModified());
            aux += ("Ultima alteração = " + auxdata+"\n\n");
        }
        aux += ("Quantidade arquivos PNG: "+cont_png+"\n");
        return aux;
    }
    
    public static void main(String[] args) {
        System.out.println("Entre com o caminho: ");
        Scanner leitura = new Scanner(System.in);
        String path = leitura.nextLine();
        File arquivo = new File(path);
        if (arquivo.exists()){
            leituraPasta(arquivo);
        }else{
            System.out.println("Caminho Invalido");
        }
        leituraPasta(arquivo);
    }
}