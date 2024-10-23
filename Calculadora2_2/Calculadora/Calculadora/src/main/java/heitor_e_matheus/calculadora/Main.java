package heitor_e_matheus.calculadora;

import javafx.application.Application;
// Importe para o media e media player
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Application para ser uma aplicação de Javafx
// Para que possa ter controle da vida da aplicação
public class Main extends Application {
    @Override
    // Start faz com que o método
    // seja chamado automaticamente quando iniciado o programa
    // (Stage primaryStage) representa uma janela principal
    public void start(Stage primaryStage) {
        // Caminho para arquivo da música
        // getClass().getResource("/Songs/Music.mp3")
        // localiza o arquivo música
        String path = getClass().getResource("/Songs/Music.mp3").toExternalForm();

        // cria o objeto media e (path) representa a música
        Media media = new Media(path);
        // Cria um media player para executar o media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // seta o volume que vai ser reproduzido 1.0 = 100% do volume
        mediaPlayer.setVolume(1.0); // Aumente o volume se necessário
        // seta o ciclo de reprodução para indefinido
        // faz com que toque infinitamente
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        // Inicia a reprodução
        mediaPlayer.play();

        // Inicia a interface principal
        MainInterface mi = new MainInterface();
        // Tenta iniciar o mi caso não dê erro, mostra o erro
        try {
            // chama o método start que deve ser implementado quando usa Application junto com o mediaPlayer
            // Java fx executa ele automaticamente quando inicia o programa
            mi.start(primaryStage, mediaPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // launch(args) é um método de Application
        // faz com que configure o Javafx e prepara para execução
        // e chama o método start
        launch(args);
    }

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
