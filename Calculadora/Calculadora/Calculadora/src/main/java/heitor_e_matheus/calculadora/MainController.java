package heitor_e_matheus.calculadora;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML private Pane paneTitulo;
    @FXML private ImageView btnFechar,btnMinimizar;
    @FXML private Label labelResultado;

    private double numero1;
    private String operador = "";

    private double x,y;

    public void inicio(Stage stage){
        paneTitulo.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        paneTitulo.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnFechar.setOnMouseClicked(mouseEvent -> {stage.close();});
        btnMinimizar.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    @FXML
    void noNumero(MouseEvent event){
        int valor = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn", ""));
        labelResultado.setText(Double.parseDouble(labelResultado.getText())==0?String.valueOf((double) valor):String.valueOf(Double.parseDouble(labelResultado.getText())*10+valor));
    }

    @FXML
    void noSinal(MouseEvent event) {
        String simbolo = ((Pane) event.getSource()).getId().replace("btn", "");
        if (simbolo.equals("Igual")) {
            double numero2 = Double.parseDouble(labelResultado.getText());
            double resultado = calcularResultado(numero2);
            labelResultado.setText(String.valueOf(resultado));
            operador = "";
        } else if (simbolo.equals("Reset")) {
            labelResultado.setText("0");
            operador = "";
        } else {
            switch (simbolo) {
                case "Soma" -> operador = "+";
                case "Menos" -> operador = "-";
                case "Multiplicacao" -> operador = "*";
                case "Divisao" -> operador = "/";
                case "Exponencial" -> operador = "^";
                case "Raiz" -> operador = "√";
                case "Porcentagem" -> operador = "%";
            }
            numero1 = Double.parseDouble(labelResultado.getText());
            labelResultado.setText("0");
        }
    }

    private double calcularResultado(double numero2) {
        return switch (operador) {
            case "+" -> numero1 + numero2;
            case "-" -> numero1 - numero2;
            case "*" -> numero1 * numero2;
            case "/" -> numero1 / numero2;
            case "^" -> Esponencial(numero2);
            case "√" -> Raiz();
            case "%" -> (numero1 * numero2) / 100;
            default -> 0;
        };
    }

    public double Esponencial(double numero2) {
        return Math.pow(numero1, numero2);
    }

    public double Raiz() {
        return Math.sqrt(numero1);
    }
}