package ExemploAula;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Pedro Pereira
 */
public class AtividadeProposta {
    public void leTexto(String filename) throws FileNotFoundException, IOException {
        File entrada = new File(filename);
        FileReader leitor = new FileReader(entrada);
        leitor.read();
        leitor.close();
    }

    public void escreveTexto(String filename) throws FileNotFoundException, IOException {
        File entrada = new File(filename);
        FileWriter escritor = new FileWriter(entrada, false);
        escritor.write("Hello world!");
        escritor.close();
    }

    public static void main(String[] args) {
        String arqEntrada = "C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\log\\entry.txt";
        String arqSaida = "C:\\Users\\Pedro Pereira\\Desktop\\UTFPR\\PROGRAMACAO_DESKTOP\\log\\exit.txt";
        BufferedWriter fileWriter = null;
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(new File(arqEntrada)));
            fileWriter = new BufferedWriter(new FileWriter(new File(arqSaida), true));
            int contLine=1;
            while (leitor.ready()) {
                //String data = leitor.readLine();
                //System.out.println(data);
                fileWriter.write("/* "+contLine+" */"+leitor.readLine());
                fileWriter.newLine();
                contLine++;
            }
            leitor.close();
            fileWriter.flush();
            fileWriter.close();
            
            /*fileWriter.write("Hello world 2!");
            fileWriter.newLine();
            fileWriter.write("Hello world 3!");
            fileWriter.newLine();
            fileWriter.flush();
            fileWriter.close();*/
            //EscritaTexto02 obj = new EscritaTexto02();
            //obj.leTexto("/home/fabricio/logqqqqq.txt");
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado.");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
}
