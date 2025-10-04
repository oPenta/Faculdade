import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("1-Produto" +"\n"+
                "2-Funcionario" +"\n"+
                "3-Retangulo" +"\n"+
                "4-ContaCorrente" +"\n"+
                "5-Calculadora");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        switch (n) {
            case 1:
                Produto produto = new Produto();
                produto.adicionarEstoque();
                produto.CalcularValorEstoque();
                break;

            case 2: 
                Funcionario funcionario = new Funcionario();
                funcionario.calcularSalarioMensal();
                break;
            
            case 3:
                Retangulo retangulo = new Retangulo();
                retangulo.calcularArea();
                retangulo.aumentarTamanho();
            
            case 4:
                ContaCorrente contaCorrente = new ContaCorrente();
                contaCorrente.verificarSaldo();
                contaCorrente.depositar();
                contaCorrente.verificarSaldo();
            case 5:
                Calculadora calculadora = new Calculadora();
                calculadora.obterResultado();
                calculadora.somar();
                calculadora.obterResultado();

                
            default:
            scanner.close();
                break;
        }
    }
}
