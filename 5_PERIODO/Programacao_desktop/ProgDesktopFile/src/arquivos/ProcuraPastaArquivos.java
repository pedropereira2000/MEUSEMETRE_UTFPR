/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Pedro Pereira
 */
public class ProcuraPastaArquivos {
    private static int foldersAccess = 0;
    private static int totalSubFoldersAccess = 0;
    
    public static String organizeTree(int cont){
        String result= "";
        
        for(int i=0;i<cont;i++){
            result+="\t";
        }
        
        return result;
    }
    public static String leituraPasta(String dir) {
        String aux = "";
        int cont_arq = 0;
        File path = new File(dir);
        String[] listagem = path.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);                
            aux +=(organizeTree(foldersAccess)+"Nome: "+ filho.getName()+"\n");            
            aux +=(organizeTree(foldersAccess)+filho.getAbsolutePath()+"\n");
            aux +=(organizeTree(foldersAccess)+"É diretorio? " + filho.isDirectory()+"\n");
            aux +=(organizeTree(foldersAccess)+"É arquivo? " + filho.isFile()+"\n");
            aux +=(organizeTree(foldersAccess)+"Tamanho = " + filho.length() + " bytes." +"\n");
            Date auxdata = new Date(filho.lastModified());
            aux +=(organizeTree(foldersAccess)+"Ultima alteração = " + auxdata+"\n\n");
            if(filho.isDirectory()==true){
                foldersAccess++;
                if(totalSubFoldersAccess == 0)
                    totalSubFoldersAccess = foldersAccess;
                if(foldersAccess > totalSubFoldersAccess)
                    totalSubFoldersAccess = foldersAccess;
                aux+=("\n"+organizeTree(foldersAccess)+"||Subpasta: "+filho.getName()+"||\n");
                aux+=leituraPasta(dir +  "\\" + filho.getName());
                foldersAccess--;
            }
            cont_arq++;
        }
        aux += (organizeTree(foldersAccess)+"Quantidade arquivos encontrados na pasta: "+cont_arq+"\n");
        aux += (organizeTree(foldersAccess)+"Quantidade de subpastas: "+totalSubFoldersAccess+"\n\n");
        return aux;
    }

    public static String leituraPastaExtensao (String path, String extensao) {
        String aux = "";
        int cont_arq = 0;
        File dir = new File(path);
        String[] listagem = dir.list();
        for (String arquivo : listagem) {
            File filho = new File(dir, arquivo);
            if(filho.getName().endsWith(extensao)){
                aux +=(organizeTree(foldersAccess)+"Nome: "+ filho.getName()+"\n");            
                aux +=(organizeTree(foldersAccess)+filho.getAbsolutePath()+"\n");
                aux +=(organizeTree(foldersAccess)+"É diretorio? " + filho.isDirectory()+"\n");
                aux +=(organizeTree(foldersAccess)+"É arquivo? " + filho.isFile()+"\n");
                aux +=(organizeTree(foldersAccess)+"Tamanho = " + filho.length() + " bytes." +"\n");
                Date auxdata = new Date(filho.lastModified());
                aux +=(organizeTree(foldersAccess)+"Ultima alteração = " + auxdata+"\n\n");
                cont_arq++;
            }
            if(filho.isDirectory()==true){
                foldersAccess++;
                totalSubFoldersAccess++;
                aux+=("\n"+organizeTree(foldersAccess)+"|| Pasta: "+filho.getAbsolutePath()+" ||\n");
                aux+=leituraPastaExtensao(dir +  "\\" + filho.getName(), extensao);
                //aux+=("\n"+organizeTree(foldersAccess)+"||Close Subpasta||\n");
                foldersAccess--;
            }
        }
        aux+=("\n"+organizeTree(foldersAccess)+"||Close Pasta||\n");
        return aux;
    }
}
