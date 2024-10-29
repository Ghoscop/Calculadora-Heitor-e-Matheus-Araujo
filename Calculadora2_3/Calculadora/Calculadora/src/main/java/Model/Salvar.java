package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Salvar {
    public static void colocarArquivoComResultados(List<String> registros, String caminho) {
        // Cria a pasta se não existir
        File diretorio = new File("out");
        if (!diretorio.exists()) {
            diretorio.mkdir(); // Cria a pasta "out"
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(diretorio, "sumário.csv")))) {
            writer.write("numero1,operador,numero2,total\n");
            for (String registro : registros) {
                writer.write(registro + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
