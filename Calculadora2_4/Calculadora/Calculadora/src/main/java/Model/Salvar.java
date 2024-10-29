package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class Salvar {
    public static void colocarArquivoComResultados(List<String> registros, String caminho) {
        // Cria a pasta se não existir
        File diretorio = new File("out");
        if (!diretorio.exists()) {
            diretorio.mkdir(); // Cria a pasta "out"
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHora = LocalDateTime.now().format(fmt);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(diretorio, "sumário.csv")))) {
            writer.write("data,hora,numero1,operador,numero2,total\n");
            for (String registro : registros) {
                writer.write(dataHora + ", " + registro + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
