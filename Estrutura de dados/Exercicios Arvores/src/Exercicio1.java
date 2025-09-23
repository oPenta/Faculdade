import java.util.Scanner;

public class Exercicio1 {

    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No noAtual, int valor) {
        if (noAtual == null) {
            return new No(valor);
        }
        if (valor < noAtual.valor) {
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, valor);
        } else if (valor > noAtual.valor) {
            noAtual.direita = inserirRecursivo(noAtual.direita, valor);
        }
        return noAtual;
    }

    public void mostrarTodos() {
        System.out.print("Todos os números (em ordem): ");
        mostrarEmOrdem(raiz);
        System.out.println();
    }

    private void mostrarEmOrdem(No no) {
        if (no != null) {
            mostrarEmOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            mostrarEmOrdem(no.direita);
        }
    }

    public void mostrarMaior() {
        if (raiz == null) {
            System.out.println("A arvore está vazia.");
            return;
        }
        No no = raiz;
        while (no.direita != null) {
            no = no.direita;
        }
        System.out.println("Maior número: " + no.valor);
    }

    public void mostrarMenor() {
        if (raiz == null) {
            System.out.println("A arvore está vazia.");
            return;
        }
        No no = raiz;
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        System.out.println("Menor número: " + no.valor);
    }

    public static void main(String[] args) {
        Exercicio1 arvore = new Exercicio1();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Inserir número");
            System.out.println("2 - Mostrar todos os números");
            System.out.println("3 - Mostrar o maior número");
            System.out.println("4 - Mostrar o menor número");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o número a ser inserido: ");
                    arvore.inserir(scanner.nextInt());
                    break;
                case 2:
                    arvore.mostrarTodos();
                    break;
                case 3:
                    arvore.mostrarMaior();
                    break;
                case 4:
                    arvore.mostrarMenor();
                    break;
                case 5:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        } while (opcao != 5);
        scanner.close();
    }
}
