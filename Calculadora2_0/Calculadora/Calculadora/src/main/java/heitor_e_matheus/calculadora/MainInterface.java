package heitor_e_matheus.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//Application para ser uma aplicação de Javafx
public class MainInterface extends Application {
    @Override
    //Start faz com que o metodo
    //seja chamado automaticamente quando iniciado o programa
    //(Stage stage) representa uma janela
    public void start(Stage stage) throws IOException {

        //carrega o arquivo FXML (que é para definir uma interface grafica)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainInterface.fxml"));
        //metodo loader.load cria uma nova cena
        Scene scene = new Scene(loader.load());

        //Define o contorno como transparente
        scene.setFill(Color.TRANSPARENT);
        //Define a cena criada com o metodo load
        stage.setScene(scene);
        //define que a janela vai ser transparente
        stage.initStyle(StageStyle.TRANSPARENT);
        //Define que nã vai poder mudar a resolução
        stage.setResizable(false);
        //Define o titulo do programa
        stage.setTitle("Sonic Calculator");
        //Define um icone para a aplicação
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/IconSonic.png")));
        //Obtem a instancia do controlador associado ao arquivo FXML
        //e chama o stage
        ((MainController) loader.getController()).inicio(stage);
        //Torna a janela visivel.
        stage.show();
    }
}
