import java.util.Scanner;

public class Calculadora {
    double resultado;

    public Calculadora() {
        this.resultado = 0;
    }

    public Calculadora(double resultadoInicial) {
        this.resultado = resultadoInicial;
    }

    public void obterResultado() {
        System.out.println("Resultado atual: " + this.resultado);
    }

    public void somar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o primeiro número:");
        double numero1 = scanner.nextDouble();
        System.out.println("Digite o segundo número:");
        double numero2 = scanner.nextDouble();
        
        this.resultado = numero1 + numero2;
        System.out.println("Novo resultado: " + this.resultado);
        scanner.close();
    }
}
