package arquivos;

import java.io.File;
import java.util.Date;

/**
 * @author pedropereira.2000@alunos.utfpr.edu.br
 */
public class TestaArquivo02 {

    public static void main(String[] args) {
        
        String str = System.getProperty("user.home");
        
        File dir = new File(str + "\\tempe01\\");
        
        File subdir = new File(dir, "subdir1");
        
        if (subdir.mkdir()) {
//implementar verificacao.
            System.out.println("Subpasta criada com sucesso.");
        } else {
            System.out.println("Pasta não criada.");
        }
        String[] listagem = dir.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);
            System.out.println(filho.getAbsolutePath());
            System.out.println("É diretorio? " + filho.isDirectory());
            System.out.println("É arquivo? " + filho.isFile());
            System.out.println("Tamanho = " + filho.length() + " bytes.");
            Date aux = new Date(filho.lastModified());
            System.out.println("Ultima alteração = " + aux + "\n\n");
        }
        if (subdir.delete()) {
            System.out.println("subdir excluido.");;
        }//implementar verificacao.
    }
}