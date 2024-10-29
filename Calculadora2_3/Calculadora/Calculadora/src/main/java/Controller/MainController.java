package Controller;

import Model.Calculadora;
import Model.Salvar;
import heitor_e_matheus.calculadora.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private Calculadora calculadora;

    public MainController() {
        // Inicializa a calculadora com valores padrão (exemplo: numero1 = 0, operador = "")
        this.calculadora = new Calculadora(0, "", 0);
    }

    //coloca imagem de título
    @FXML private Pane paneTitulo;
    //inicia botões de minimizar e finalizar programa
    @FXML private ImageView btnFechar, btnMinimizar;
    //inicia barra geradora dos resultados das operações
    @FXML private Label labelResultado;
    //inicia variáveis de coordenadas para mover a tela da calculadora
    private double x, y;

    //armazena valores double em uma lista específica
    private List<Double> resultados = new ArrayList<>();
    //armazena todos as operações em ordem com outra variável (registro)
    private List<String> registros = new ArrayList<>();

    //inicia e sinaliza caminho dos áudios que foram usados
    private final AudioClip clickSound = new AudioClip(getClass().getResource("/Songs/Kek.mp3").toString());
    private final AudioClip clicksoos = new AudioClip(getClass().getResource("/Songs/Anel.mp3").toString());
    private final AudioClip click = new AudioClip(getClass().getResource("/Songs/ok.mp3").toString());
    //cria MediaPlayer para organizar e fazer com que os áudios possam ser tocados ao mesmo tempo sem interromper outros
    private MediaPlayer mediaPlayer;

    //método para englobar a movimentação e opções de minimizar e fechar programa
    public void inicio(Stage stage, MediaPlayer mediaPlayer) {
        //coloca o mediaPlayer em uma variável para ser usado fora do private
        this.mediaPlayer=mediaPlayer;
        //habilita o movimento da janela pela tela
        paneTitulo.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        //habilita as funções de minimizar e fechar
        paneTitulo.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnFechar.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimizar.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    @FXML
        //permite que os números sejam registrados quando clicados
    void noNumero(MouseEvent event) {
        //variável valor para conter o número inteiro do número apertado
        int valor = Integer.parseInt(((Pane) event.getSource()).getId().replace("btn", ""));
        //número aparece na interface através do labelResultado
        labelResultado.setText(Double.parseDouble(labelResultado.getText()) == 0 ? String.valueOf((double) valor) : String.valueOf(Double.parseDouble(labelResultado.getText()) * 10 + valor));
        //pega a variável de som e o executa quando clicar no número
        clickSound.play();
    }

    @FXML
        //inicia outro evento de click para um segundo valor depois de digitado o primeiro
    void noSinal(MouseEvent event) {
        //coloca que quando clicar em "Igual" os valores são calculados
        String simbolo = ((Pane) event.getSource()).getId().replace("btn", "");
        if (simbolo.equals("Igual")) {
            click.play();
            double numero2 = Double.parseDouble(labelResultado.getText());
            calculadora.setNumero2(numero2); // Define numero2 na calculadora

            //resultado calcula através da digitação do valor2
            double resultado = calculadora.calcularResultado(numero2);
            labelResultado.setText(String.valueOf(resultado));

            //adiciona o resultado a lista double
            resultados.add(resultado);

            //variável registro para gerar e armazenar todas as operações
            String registro = String.format("%.2f %s %.2f = %.2f", calculadora.getNumero1(), calculadora.getOperador(), numero2, resultado);
            //registro é adicionado a lista String
            registros.add(registro);

            //gera as operações no terminal
            System.out.println("Resultado armazenado: " + registro);

            //armazena todas as operações de registros em um arquivo csv, contido na pasta out
            Salvar.colocarArquivoComResultados(registros, "out/sumário.csv");

            //coloca operador como default
            calculadora.setOperador("");
        } else if (simbolo.equals("Reset")) {
            labelResultado.setText("0");
            calculadora.setOperador(""); // Reseta o operador
            clicksoos.play();
        } else {
            //define todos os símbolos de operações com seus nomes
            switch (simbolo) {
                case "Soma" -> calculadora.setOperador("+");
                case "Menos" -> calculadora.setOperador("-");
                case "Multiplicacao" -> calculadora.setOperador("*");
                case "Divisao" -> calculadora.setOperador("/");
                case "Exponencial" -> calculadora.setOperador("^");
                case "Raiz" -> calculadora.setOperador("√");
                case "Porcentagem" -> calculadora.setOperador("%");
            }
            calculadora.setNumero1(Double.parseDouble(labelResultado.getText())); // Define numero1 na calculadora
            labelResultado.setText("0"); // Reseta o campo de entrada
        }
    }
}
