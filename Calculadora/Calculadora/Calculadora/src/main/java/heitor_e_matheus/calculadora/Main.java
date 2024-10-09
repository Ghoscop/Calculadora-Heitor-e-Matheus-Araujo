package heitor_e_matheus.calculadora;

import javafx.application.Application;
//importe para o media e media player
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

//Application para ser uma aplicação de Javafx
//Para que possa ter controle da vida da aplicação
public class Main extends Application {
    @Override
    //Start faz com que o metodo
    //seja chamado automaticamente quando iniciado o programa
    //(Stage primaryStage) representa uma janela principal
    public void start(Stage primaryStage) {

        //caminho para arquivo da musica, getClass().getResource("/Songs/Music.mp3")
        //localiza o arquivo musica
        String path = getClass().getResource("/Songs/Music.mp3").toExternalForm();

        //cria o objeto media e (path) representa a musica
        Media media = new Media(path);
        //Cria um media player para executar o media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //seta o volume que vai ser reproduzido 0.5 = 50% do volume
        mediaPlayer.setVolume(0.5);
        //seta o ciclo de reprodução para indefinido
        //faz com que toque infinitamente
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //Inica a reprodução
        mediaPlayer.play();

        //Inicia a interface principal
        MainInterface mi = new MainInterface();
        //Tenta iniciar o mi caso n de ele mostra o erro
        try {
            //chama o metodo start deve ser implementado quando usa Application
            //Java fx executa ele automaticamente quando inicia o programa
            mi.start(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //lauch(args) é um metodo de aplication
        //faz com que configure o Javafx e prepara para execução
        //e chma o metodo start
        launch(args);
    }
}
