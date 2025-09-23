import java.util.Scanner;

public class Exercicio2 {

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
        return buscarRecursivo(this.raiz, valor);
    }

    private No buscarRecursivo(No no, int valor) {
        if (no == null || no.valor == valor) {
            return no;
        }
        if (valor < no.valor) {
            return buscarRecursivo(no.esquerda, valor);
        }
        return buscarRecursivo(no.direita, valor);
    }

    public void mostrarNosFolha() {
        System.out.print("Nós folha: ");
        encontrarFolhas(this.raiz);
        System.out.println();
    }

    private void encontrarFolhas(No no) {
        if (no == null) {
            return;
        }
        if (no.esquerda == null && no.direita == null) {
            System.out.print(no.valor + " ");
        }
        encontrarFolhas(no.esquerda);
        encontrarFolhas(no.direita);
    }

    public void mostrarAncestrais(int valor) {
        System.out.print("Ancestrais de " + valor + ": ");
        if (!encontrarAncestrais(this.raiz, valor)) {
            System.out.println("Nó não encontrado ou é a raiz.");
        }
        System.out.println();
    }

    private boolean encontrarAncestrais(No no, int valor) {
        if (no == null) {
            return false;
        }
        if (no.valor == valor) {
            return true;
        }
        if (encontrarAncestrais(no.esquerda, valor) || encontrarAncestrais(no.direita, valor)) {
            System.out.print(no.valor + " ");
            return true;
        }
        return false;
    }

    public void mostrarDescendentes(int valor) {
        No no = buscar(valor);
        if (no == null) {
            System.out.println("Nó " + valor + " não encontrado.");
            return;
        }
        System.out.print("Descendentes de " + valor + ": ");
        mostrarPreOrdem(no.esquerda);
        mostrarPreOrdem(no.direita);
        System.out.println();
    }

    private void mostrarPreOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            mostrarPreOrdem(no.esquerda);
            mostrarPreOrdem(no.direita);
        }
    }

    public void mostrarPaiEFilhos(int valor) {
        if (this.raiz == null) {
            System.out.println("Árvore vazia.");
            return;
        }
        if (this.raiz.valor == valor) {
            System.out.println("Nó " + valor + " é a raiz, não tem pai.");
        } else {
            No pai = encontrarPai(this.raiz, valor);
            if (pai != null) {
                System.out.println("Pai de " + valor + ": " + pai.valor);
            } else {
                System.out.println("Nó " + valor + " não encontrado.");
                return;
            }
        }

        No no = buscar(valor);
        if (no == null) return;

        if (no.esquerda != null) {
            System.out.println("Filho à esquerda: " + no.esquerda.valor);
        } else {
            System.out.println("Não tem filho à esquerda.");
        }

        if (no.direita != null) {
            System.out.println("Filho à direita: " + no.direita.valor);
        } else {
            System.out.println("Não tem filho à direita.");
        }
    }

    private No encontrarPai(No noAtual, int valor) {
        if (noAtual == null) return null;

        if ((noAtual.esquerda != null && noAtual.esquerda.valor == valor) ||
                (noAtual.direita != null && noAtual.direita.valor == valor)) {
            return noAtual;
        }

        if (valor < noAtual.valor) {
            return encontrarPai(noAtual.esquerda, valor);
        } else {
            return encontrarPai(noAtual.direita, valor);
        }
    }

    public static void main(String[] args) {
        Exercicio2 arvore = new Exercicio2();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Inserir número");
            System.out.println("2 - Mostrar os nós folha");
            System.out.println("3 - Mostrar os nós ancestrais de um nó");
            System.out.println("4 - Mostrar os nós descendentes de um nó");
            System.out.println("5 - Mostrar o pai e os filhos de um nó");
            System.out.println("6 - Sair");
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
                    arvore.mostrarNosFolha();
                    break;
                case 3:
                    System.out.print("Digite o valor do nó para ver seus ancestrais: ");
                    valor = scanner.nextInt();
                    arvore.mostrarAncestrais(valor);
                    break;
                case 4:
                    System.out.print("Digite o valor do nó para ver seus descendentes: ");
                    valor = scanner.nextInt();
                    arvore.mostrarDescendentes(valor);
                    break;
                case 5:
                    System.out.print("Digite o valor do nó para ver seu pai e filhos: ");
                    valor = scanner.nextInt();
                    arvore.mostrarPaiEFilhos(valor);
                    break;
                case 6:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        } while (opcao != 6);

        scanner.close();
    }
}