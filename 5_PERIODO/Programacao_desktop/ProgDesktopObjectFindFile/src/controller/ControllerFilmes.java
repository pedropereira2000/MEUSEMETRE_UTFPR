/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Filmes;

/**
 *
 * @author pedropereira
 */
public class ControllerFilmes extends ControllerArquivo {
    private Filmes filme = new Filmes();
    private String texto = null;
    private BufferedReader leitor = null;
    private BufferedWriter escritor = null;
    
    public Filmes getFilme(int cont){
      return null;
    }
    
    public void setFilme(String titulo, String genero, int duracao, String classif, String diretor, boolean alugado){
      filme = new Filmes(titulo, genero, duracao, classif, diretor, alugado);
      //System.out.println(filme.getTitulo()+" "+filme.getGenero()+" "+filme.getDuracao()+" "+filme.getClassificacao()+" "+filme.getDiretor()+" "+filme.getAlugado());
    }
    
    public boolean escreverEdit(boolean append){
        if (arquivo != null) {
            try {
                escritor = new BufferedWriter(new FileWriter(arquivo, append));
                escritor.write(this.texto);
                escritor.close();
                return true;
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void excluiFilme(String tituloExc, String studioExc){
        String txt = this.texto;
        String orig = "";
        String rest = "";
        int indexIni = 0;
        int indexFim = 0;

        while(true){
            indexIni = txt.indexOf("titulo:");
            indexFim = txt.indexOf("gênero");
            String titulo = txt.substring(indexIni+8, indexFim-2);
            indexIni = txt.indexOf("studio:");
            indexFim = txt.indexOf("alugado");
            String studio = txt.substring(indexIni+8, indexFim-2);
            if(titulo.equals(tituloExc)&&studio.equals(studioExc)){
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                orig = txt.substring(indexIni);
                indexIni = txt.indexOf("}");
                indexFim = txt.indexOf("{");
                rest = txt.substring(indexIni+2);
                this.texto=(texto.replace(orig, rest)).trim().replace("\\s{2,}", " ");
                escreverEdit(false);
                break;
            }
            indexFim = txt.indexOf("}");
            txt=txt.substring(indexFim+2);
            if(txt.isEmpty())
               break;
        }
    }
    
   public void editFilme(String tituloAntigo, String studioAntigo,String tituloNovo, String generoNovo, String duracaoNovo, String classificacaoNovo, String studioNovo, String alugadoNovo){
        String txt = this.texto;
        String orig = "";
        int indexIni = 0;
        int indexFim = 0;

        while(true){
            indexIni = txt.indexOf("titulo:");
            indexFim = txt.indexOf("gênero");
            String titulo = txt.substring(indexIni+8, indexFim-2);
            if(titulo.equals(tituloAntigo)){
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                orig = txt.substring(indexIni, indexFim+1);
                txt=txt.replace(titulo, tituloNovo);
                indexIni = txt.indexOf("gênero:");
                indexFim = txt.indexOf("duração");
                String genero = txt.substring(indexIni+8, indexFim-2);
                txt=txt.replace(genero, generoNovo);
                indexIni = txt.indexOf("duração:");
                indexFim = txt.indexOf("classificação");
                String duracao = txt.substring(indexIni+9, indexFim-2);
                txt=txt.replace(duracao, duracaoNovo);
                indexIni = txt.indexOf("classificação:");
                indexFim = txt.indexOf("studio");
                String classificacao = txt.substring(indexIni+15, indexFim-2);
                txt=txt.replace(classificacao, classificacaoNovo);
                indexIni = txt.indexOf("studio:");
                indexFim = txt.indexOf("alugado");
                String studio = txt.substring(indexIni+8, indexFim-2);
                if(studio.equals(studioAntigo)){
                    txt=txt.replace(studio, studioNovo);
                    indexIni = txt.indexOf("alugado:");
                    indexFim = txt.indexOf("}");
                    String alugado = txt.substring(indexIni+9, indexFim-1);
                    txt=txt.replace(alugado, alugadoNovo);
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    txt = txt.substring(indexIni, indexFim+1);
                    this.texto=texto.replace(orig, txt).trim().replace("\\s{2,}", " ");
                    escreverEdit(false);
                    break;
                }
            }
            indexFim = txt.indexOf("}");
            txt=txt.substring(indexFim+2);
            if(txt.isEmpty())
               break;
        }
        
        //return "Edição feita com sucesso";
    }
    
    public void buscaFilme(String busc, String tipo){
        String txt = this.texto;
        String busca = "";
        int indexIni = 0;
        int indexFim = 0;
        
        if(tipo.equals("titulo")){
            while(true){
                indexIni = txt.indexOf("titulo:");
                indexFim = txt.indexOf("gênero");
                String titulo = txt.substring(indexIni+8, indexFim-2);
                if(titulo.equals(busc)){
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("genero")){
            while(true){
                indexIni = txt.indexOf("gênero:");
                indexFim = txt.indexOf("duração");
                String genero = txt.substring(indexIni+8, indexFim-2);
                if(genero.equals(busc)){
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("duracao")){
            while(true){
                indexIni = txt.indexOf("duração:");
                indexFim = txt.indexOf("classificação");
                String duracao = txt.substring(indexIni+9, indexFim-2);
                if(duracao.equals(busc)){
                    System.out.println(txt);
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("classificacao")){
            while(true){
                indexIni = txt.indexOf("classificação:");
                indexFim = txt.indexOf("studio");
                String classificacao = txt.substring(indexIni+15, indexFim-2);
                if(classificacao.equals(busc)){
                    System.out.println(txt);
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("studio")){
            while(true){
                indexIni = txt.indexOf("studio:");
                indexFim = txt.indexOf("alugado");
                String studio = txt.substring(indexIni+8, indexFim-2);
                if(studio.equals(busc)){
                    System.out.println(txt);
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        if(tipo.equals("alugado")){
            while(true){
                indexIni = txt.indexOf("alugado:");
                indexFim = txt.indexOf("}");
                String alugado = txt.substring(indexIni+9, indexFim-1);
                if(alugado.equals(busc)){
                    System.out.println(txt);
                    indexIni = txt.indexOf("{");
                    indexFim = txt.indexOf("}");
                    busca+=(txt.substring(indexIni, indexFim+2));
                }
                indexFim = txt.indexOf("}");
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
        }
        
        this.texto=busca;
    }
    
    public Filmes getFilmText(String tituloPesq, String studioPesq){
        String txt = this.texto;
        Filmes film = null;
        int indexIni = 0;
        int indexFim = 0;
        ArrayList<String> splitTxt =  new ArrayList<>();
        
        if(txt.equals("")){
            return film;
        }else{
            while(true){
                indexIni = txt.indexOf("{");
                indexFim = txt.indexOf("}");
                splitTxt.add(txt.substring(indexIni, indexFim+1));
                txt=txt.substring(indexFim+2);
                if(txt.isEmpty())
                   break;
            }
            
            for(int i=0; i<splitTxt.size(); i++){
                indexIni = splitTxt.get(i).indexOf("titulo:");
                indexFim = splitTxt.get(i).indexOf("gênero");
                String titulo = splitTxt.get(i).substring(indexIni+8, indexFim-2);
                if(titulo.equals(tituloPesq)){
                    indexIni = splitTxt.get(i).indexOf("gênero:");
                    indexFim = splitTxt.get(i).indexOf("duração");
                    String genero = splitTxt.get(i).substring(indexIni+8, indexFim-2);
                    indexIni = splitTxt.get(i).indexOf("duração:");
                    indexFim = splitTxt.get(i).indexOf("classificação");
                    String duracao = splitTxt.get(i).substring(indexIni+9, indexFim-2);
                    indexIni = splitTxt.get(i).indexOf("classificação:");
                    indexFim = splitTxt.get(i).indexOf("studio");
                    String classificacao = splitTxt.get(i).substring(indexIni+15, indexFim-2);
                    indexIni = splitTxt.get(i).indexOf("studio:");
                    indexFim = splitTxt.get(i).indexOf("alugado");
                    String studio = splitTxt.get(i).substring(indexIni+8, indexFim-2);
                    if(studio.equals(studioPesq)){
                        indexIni = splitTxt.get(i).indexOf("alugado:");
                        indexFim = splitTxt.get(i).indexOf("}");
                        boolean alugado = false;
                        if(splitTxt.get(i).substring(indexIni+9, indexFim-1).equals("true")){
                            alugado = true;
                        }else{
                            alugado = false;
                        }
                        film = new Filmes(titulo,genero,Integer.parseInt(duracao),classificacao,studio,alugado);
                        break;
                    }
                }
            }
            
        }
        
        return film;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    @Override
    public boolean ler() {
        StringBuilder line = new StringBuilder();
        try {
            leitor = new BufferedReader(new FileReader(arquivo));
            while (leitor.ready()) {
                line.append(leitor.readLine()).append("\n");
            }
            leitor.close();
            setTexto(line.toString());
            return true;
        } catch (FileNotFoundException erro) {
            //erro.printStackTrace(); //usado para debug
            System.err.println(erro.getMessage() + "Arquivo não encontrado.");
            return false;
        } catch (IOException erro) {
            System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
            return false;
        }
    }
    
    @Override
    public boolean escrever(boolean append){
        if (arquivo != null) {
            try {
                if(!texto.equals("")){
                    escritor = new BufferedWriter(new FileWriter(arquivo, append));
                    escritor.write("\n{\n\ttitulo: "+filme.getTitulo()+"\n\tgênero: "+filme.getGenero()+"\n\tduração: "+filme.getDuracao()+"\n\tclassificação: "+filme.getClassificacao()+"\n\tstudio: "+filme.getStudio()+"\n\talugado: "+filme.getAlugado()+"\n}");
                    escritor.close();
                    this.texto += "\n{\n\ttitulo: "+filme.getTitulo()+"\n\tgênero: "+filme.getGenero()+"\n\tduração: "+filme.getDuracao()+"\n\tclassificação: "+filme.getClassificacao()+"\n\tstudio: "+filme.getStudio()+"\n\talugado: "+filme.getAlugado()+"\n}";
                    return true;
                }else{
                    escritor = new BufferedWriter(new FileWriter(arquivo, append));
                    escritor.write("{\n\ttitulo: "+filme.getTitulo()+"\n\tgênero: "+filme.getGenero()+"\n\tduração: "+filme.getDuracao()+"\n\tclassificação: "+filme.getClassificacao()+"\n\tstudio: "+filme.getStudio()+"\n\talugado: "+filme.getAlugado()+"\n}");
                    escritor.close();
                    this.texto += "{\n\ttitulo: "+filme.getTitulo()+"\n\tgênero: "+filme.getGenero()+"\n\tduração: "+filme.getDuracao()+"\n\tclassificação: "+filme.getClassificacao()+"\n\tstudio: "+filme.getStudio()+"\n\talugado: "+filme.getAlugado()+"\n}";
                    return true;
                }
                
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }
}
