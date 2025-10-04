import java.util.Scanner;

public class Produto {
    String nome;
    double preco;
    int quantidade;

    public Produto(){
        this.nome = "Balinha";
        this.preco = 0.5;
        this.quantidade = 0;
    }
    public Produto(String nome, double preco, int quantidade){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public void adicionarEstoque(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de produtos no estoque:");
        int quantidade = scanner.nextInt();
        this.quantidade = quantidade;
        scanner.close();
    }

    public void CalcularValorEstoque(){
        System.out.println(preco*quantidade);
    }
}
