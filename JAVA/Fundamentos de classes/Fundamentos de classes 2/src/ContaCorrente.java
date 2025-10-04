import java.util.Scanner;

public class ContaCorrente {
    double saldo;

    public ContaCorrente() {
        this.saldo = 0;
    }

    public ContaCorrente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void verificarSaldo() {
        System.out.println("Saldo atual: " + this.saldo);
    }

    public void depositar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o valor a ser depositado:");
        double valorDeposito = scanner.nextDouble();
        
        if (valorDeposito > 0) {
            this.saldo += valorDeposito;
            System.out.println("Novo saldo: " + this.saldo);
        } else {
            System.out.println("Valor de depósito inválido!");
        }
        scanner.close();
    }
}