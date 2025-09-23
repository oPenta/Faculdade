public class Exercicio4 {

    NoAVL raiz;

    int altura(NoAVL N) {
        if (N == null) return 0;
        return N.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        return x;
    }

    NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        return y;
    }

    int getBalance(NoAVL N) {
        if (N == null) return 0;
        return altura(N.esquerda) - altura(N.direita);
    }

    NoAVL inserir(NoAVL no, int valor) {
        if (no == null) return (new NoAVL(valor));
        if (valor < no.valor) no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor) no.direita = inserir(no.direita, valor);
        else return no;

        no.altura = 1 + max(altura(no.esquerda), altura(no.direita));
        int balance = getBalance(no);

        if (balance > 1 && valor < no.esquerda.valor) return rotacaoDireita(no);
        if (balance < -1 && valor > no.direita.valor) return rotacaoEsquerda(no);
        if (balance > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balance < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    NoAVL menorValorNo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null) atual = atual.esquerda;
        return atual;
    }

    NoAVL remover(NoAVL raiz, int valor) {
        if (raiz == null) return raiz;
        if (valor < raiz.valor) raiz.esquerda = remover(raiz.esquerda, valor);
        else if (valor > raiz.valor) raiz.direita = remover(raiz.direita, valor);
        else {
            if ((raiz.esquerda == null) || (raiz.direita == null)) {
                NoAVL temp = (raiz.esquerda != null) ? raiz.esquerda : raiz.direita;
                if (temp == null) {
                    raiz = null;
                } else
                    raiz = temp;
            } else {
                NoAVL temp = menorValorNo(raiz.direita);
                raiz.valor = temp.valor;
                raiz.direita = remover(raiz.direita, temp.valor);
            }
        }

        if (raiz == null) return raiz;
        raiz.altura = max(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        int balance = getBalance(raiz);

        if (balance > 1 && getBalance(raiz.esquerda) >= 0) return rotacaoDireita(raiz);
        if (balance > 1 && getBalance(raiz.esquerda) < 0) {
            raiz.esquerda = rotacaoEsquerda(raiz.esquerda);
            return rotacaoDireita(raiz);
        }
        if (balance < -1 && getBalance(raiz.direita) <= 0) return rotacaoEsquerda(raiz);
        if (balance < -1 && getBalance(raiz.direita) > 0) {
            raiz.direita = rotacaoDireita(raiz.direita);
            return rotacaoEsquerda(raiz);
        }
        return raiz;
    }

    void imprimirPreOrdem(NoAVL no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            imprimirPreOrdem(no.esquerda);
            imprimirPreOrdem(no.direita);
        }
    }

    public static void main(String[] args) {
        Exercicio4 arvore = new Exercicio4();
        int[] numeros = {50, 30, 55, 10, 15, 20, 80, 90, 68};

        System.out.println("Inserindo os números na Árvore AVL");
        for (int num : numeros) {
            arvore.raiz = arvore.inserir(arvore.raiz, num);
            System.out.print("Árvore (Pré-Ordem): ");
            arvore.imprimirPreOrdem(arvore.raiz);
            System.out.println();
        }

        System.out.println("\nÁrvore AVL Final (Pré-Ordem)");
        arvore.imprimirPreOrdem(arvore.raiz);
        System.out.println();


        System.out.println("\nÁrvore Final Apos Remoções (Pré-Ordem)");
        arvore.imprimirPreOrdem(arvore.raiz);
        System.out.println();
    }
}