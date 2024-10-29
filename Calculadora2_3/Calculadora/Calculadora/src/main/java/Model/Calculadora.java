package Model;

public class Calculadora {

    //inicia variavel de digitar primeiro número
    private double numero1;
    //inicia qual operação será realizada
    private String operador = "";

    private double numero2;

    public Calculadora(double numero1, String operador, double numero2) {
        this.numero1 = numero1;
        this.operador = operador;
        this.numero2 = numero2;
    }

    public double getNumero1() {
        return numero1;
    }

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public double getNumero2() {
        return numero2;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    //método pra calcular resultado e puxa o numero2 para seguno valor
    public double calcularResultado(double numero2) {
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
    private double Esponencial(double numero2) {
        //math.pow para multiplicar o exponencial
        return Math.pow(numero1, numero2);
    }

    //método pra calcular a raiz
    private double Raiz() {
        //Math.sqrt para calcular raiz
        return Math.sqrt(numero1);
    }
}
