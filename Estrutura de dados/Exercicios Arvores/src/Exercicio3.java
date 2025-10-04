import java.util.Scanner;

public class Exercicio3 {

    private No raiz;

    public void inserir(int valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
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

    private No buscar(int valor) {
        No atual = this.raiz;
        while (atual != null && atual.valor != valor) {
            if (valor < atual.valor) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
        return atual;
    }

    public void mostrarTodos() {
        System.out.print("Árvore completa (em ordem): ");
        mostrarEmOrdem(this.raiz);
        System.out.println();
    }

    private void mostrarEmOrdem(No no) {
        if (no != null) {
            mostrarEmOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            mostrarEmOrdem(no.direita);
        }
    }

    public void mostrarSubArvoreDireita(int valor) {
        No no = buscar(valor);
        if (no == null) {
            System.out.println("Nó " + valor + " não encontrado.");
            return;
        }
        if (no.direita == null) {
            System.out.println("O nó " + valor + " não possui sub-árvore direita.");
            return;
        }
        System.out.print("Sub-árvore direita de " + valor + ": ");
        mostrarEmOrdem(no.direita);
        System.out.println();
    }

    public void mostrarSubArvoreEsquerda(int valor) {
        No no = buscar(valor);
        if (no == null) {
            System.out.println("Nó " + valor + " não encontrado.");
            return;
        }
        if (no.esquerda == null) {
            System.out.println("O nó " + valor + " não possui sub-árvore esquerda.");
            return;
        }
        System.out.print("Sub-árvore esquerda de " + valor + ": ");
        mostrarEmOrdem(no.esquerda);
        System.out.println();
    }

    public static void main(String[] args) {
        Exercicio3 arvore = new Exercicio3();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Inserir número");
            System.out.println("2 - Mostrar todos");
            System.out.println("3 - Mostrar a sub-árvore direita de um nó");
            System.out.println("4 - Mostrar a sub-árvore esquerda de um nó");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            int valor;
            switch (opcao) {
                case 1:
                    System.out.print("Digite o número a ser inserido: ");
                    valor = scanner.nextInt();
                    arvore.inserir(valor);
                    break;
                case 2:
                    arvore.mostrarTodos();
                    break;
                case 3:
                    System.out.print("Digite o valor do nó para ver sua sub-árvore direita: ");
                    valor = scanner.nextInt();
                    arvore.mostrarSubArvoreDireita(valor);
                    break;
                case 4:
                    System.out.print("Digite o valor do nó para ver sua sub-árvore esquerda: ");
                    valor = scanner.nextInt();
                    arvore.mostrarSubArvoreEsquerda(valor);
                    break;
                case 5:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 5);
        scanner.close();
    }
}