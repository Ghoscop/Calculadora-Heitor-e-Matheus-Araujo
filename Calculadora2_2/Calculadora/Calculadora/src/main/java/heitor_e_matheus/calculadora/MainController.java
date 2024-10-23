package heitor_e_matheus.calculadora;

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
    //coloca imagem de título
    @FXML private Pane paneTitulo;
    //inicia botões de minimizar e finalizar programa
    @FXML private ImageView btnFechar, btnMinimizar;
    //inicia barra geradora dos resultados das operações
    @FXML private Label labelResultado;

    //inicia variavel de digitar primeiro número
    private double numero1;
    //inicia qual operação será realizada
    private String operador = "";
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
        //habilita as funçõs de minimizar e fechar
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
            //resultado calcula através da digitação do valor2
            double resultado = calcularResultado(numero2);
            labelResultado.setText(String.valueOf(resultado));

            //adiciona o resultado a lista double
            resultados.add(resultado);

            //variável registro para gerar e armazenar todas as operações
            String registro = String.format("%.2f %s %.2f = %.2f", numero1, operador, numero2, resultado);
            //registro é adicionado a lista String
            registros.add(registro);

            //gera as operações no terminal
            System.out.println("Resultado armazenado: " + registro);

            //aramzena todas as operações de registros em um arquivo csv, contido na pasta out
            Main.colocarArquivoComResultados(registros, "out/sumário.csv");

            //coloca operador como default
            operador = "";

            //se o botão reset for apertado, o labelResultado volta para 0
        }else if (simbolo.equals("Reset")) {
            labelResultado.setText("0");
            operador = "";
            clicksoos.play();
        } else {
            //define todos os símbolos de operações com seus nomes
            switch (simbolo) {
                case "Soma" -> {
                    operador = "+";
                    clickSound.play();
                }
                case "Menos" -> {
                    operador = "-";
                    clickSound.play();
                }
                case "Multiplicacao" -> {
                    operador = "*";
                    clickSound.play();
                }
                case "Divisao" -> {
                    operador = "/";
                    clickSound.play();
                }
                case "Exponencial" -> {
                    operador = "^";
                    clickSound.play();
                }
                case "Raiz" -> {
                    operador = "√";
                    clickSound.play();
                }
                case "Porcentagem" -> {
                    operador = "%";
                    clickSound.play();
                }
            }
            //transforma o valor digitado em texto na interface, o valor está em double
            numero1 = Double.parseDouble(labelResultado.getText());
            //volta pra zero depois de escolher o operador
            labelResultado.setText("0");
        }
    }

    //método pra calcular resultado e puxa o numero2 para seguno valor
    private double calcularResultado(double numero2) {
        //switch case para qual operador for clicado
        return switch (operador) {
            case "+" -> numero1 + numero2;
            case "-" -> numero1 - numero2;
            case "*" -> numero1 * numero2;
            case "/" -> numero1 / numero2;
            case "^" -> Esponencial(numero2); //retorna método com número2 para digitar o valor do exponencial
            case "√" -> Raiz(); // retorna método
            case "%" -> (numero1 * numero2) / 100;
            //volta como número padrã igual a 0
            default -> 0;
        };
    }

    //método para armazenar o exponencial
    public double Esponencial(double numero2) {
        //math.pow para multiplicar o exponencial
        return Math.pow(numero1, numero2);
    }

    //método pra calcular a raiz
    public double Raiz() {
        //Math.sqrt para calcular raiz
        return Math.sqrt(numero1);
    }
}
