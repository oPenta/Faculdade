import java.util.List;
import java.util.ArrayList;

public class ArvoreAVL {
    private NoAVL raiz;
    private int contadorRotacoes;

    public ArvoreAVL() {
        this.raiz = null;
        this.contadorRotacoes = 0;
    }

    public int getContadorRotacoes() {
        return contadorRotacoes;
    }

    public int getAltura() {
        return this.altura(this.raiz);
    }

    private int altura(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getFatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.getEsquerda();
        NoAVL T2 = x.getDireita();
        x.setDireita(y);
        y.setEsquerda(T2);
        y.setAltura(max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);
        x.setAltura(max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);
        contadorRotacoes++;
        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.getDireita();
        NoAVL T2 = y.getEsquerda();
        y.setEsquerda(x);
        x.setDireita(T2);
        x.setAltura(max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);
        y.setAltura(max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);
        contadorRotacoes++;
        return y;
    }

    public void inserir(Livro livro) {
        raiz = inserirRec(raiz, livro);
    }

    private NoAVL inserirRec(NoAVL no, Livro livro) {
        if (no == null) {
            return (new NoAVL(livro));
        }
        if (livro.getId() < no.getLivro().getId()) {
            no.setEsquerda(inserirRec(no.getEsquerda(), livro));
        } else if (livro.getId() > no.getLivro().getId()) {
            no.setDireita(inserirRec(no.getDireita(), livro));
        } else {
            return no;
        }
        no.setAltura(1 + max(altura(no.getEsquerda()), altura(no.getDireita())));
        int fatorBalanceamento = getFatorBalanceamento(no);
        if (fatorBalanceamento > 1 && livro.getId() < no.getEsquerda().getLivro().getId()) {
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento < -1 && livro.getId() > no.getDireita().getLivro().getId()) {
            return rotacaoEsquerda(no);
        }
        if (fatorBalanceamento > 1 && livro.getId() > no.getEsquerda().getLivro().getId()) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento < -1 && livro.getId() < no.getDireita().getLivro().getId()) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }
        return no;
    }

    public void remover(int id) {
        raiz = removerRec(raiz, id);
    }

    private NoAVL removerRec(NoAVL no, int id) {
        if (no == null) {
            return no;
        }
        if (id < no.getLivro().getId()) {
            no.setEsquerda(removerRec(no.getEsquerda(), id));
        } else if (id > no.getLivro().getId()) {
            no.setDireita(removerRec(no.getDireita(), id));
        } else {
            if ((no.getEsquerda() == null) || (no.getDireita() == null)) {
                NoAVL temp = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();
                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoAVL temp = encontrarMinimo(no.getDireita());
                no.setLivro(temp.getLivro());
                no.setDireita(removerRec(no.getDireita(), temp.getLivro().getId()));
            }
        }
        if (no == null) {
            return no;
        }
        no.setAltura(max(altura(no.getEsquerda()), altura(no.getDireita())) + 1);
        int fatorBalanceamento = getFatorBalanceamento(no);
        if (fatorBalanceamento > 1 && getFatorBalanceamento(no.getEsquerda()) >= 0) {
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento > 1 && getFatorBalanceamento(no.getEsquerda()) < 0) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento < -1 && getFatorBalanceamento(no.getDireita()) <= 0) {
            return rotacaoEsquerda(no);
        }
        if (fatorBalanceamento < -1 && getFatorBalanceamento(no.getDireita()) > 0) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }
        return no;
    }

    private NoAVL encontrarMinimo(NoAVL no) {
        NoAVL atual = no;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public Livro buscar(int id) {
        NoAVL no = buscarRec(this.raiz, id);
        return no != null ? no.getLivro() : null;
    }

    private NoAVL buscarRec(NoAVL no, int id) {
        if (no == null || no.getLivro().getId() == id) {
            return no;
        }
        if (id < no.getLivro().getId()) {
            return buscarRec(no.getEsquerda(), id);
        }
        return buscarRec(no.getDireita(), id);
    }

    public List<Integer> getTodosIds() {
        List<Integer> ids = new ArrayList<>();
        getTodosIdsRec(this.raiz, ids);
        return ids;
    }

    private void getTodosIdsRec(NoAVL no, List<Integer> ids) {
        if (no != null) {
            getTodosIdsRec(no.getEsquerda(), ids);
            ids.add(no.getLivro().getId());
            getTodosIdsRec(no.getDireita(), ids);
        }
    }

    public String gerarDotParaGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph AVL {\n");
        dot.append("  node [fontname=\"Arial\", shape=record, style=rounded];\n");
        if (raiz == null) {
            dot.append("  NULL [shape=plaintext, label=\"Árvore Vazia\"];\n");
        } else {
            gerarDotRec(raiz, dot);
        }
        dot.append("}\n");
        return dot.toString();
    }

    private void gerarDotRec(NoAVL no, StringBuilder dot) {
        if (no == null) return;
        String label = String.format("ID: %d | H: %d | FB: %d",
                no.getLivro().getId(), no.getAltura(), getFatorBalanceamento(no));
        dot.append(String.format("  %d [label=\"{%s}\"];\n", no.getLivro().getId(), label));
        if (no.getEsquerda() != null) {
            dot.append(String.format("  %d -> %d;\n", no.getLivro().getId(), no.getEsquerda().getLivro().getId()));
            gerarDotRec(no.getEsquerda(), dot);
        }
        if (no.getDireita() != null) {
            dot.append(String.format("  %d -> %d;\n", no.getLivro().getId(), no.getDireita().getLivro().getId()));
            gerarDotRec(no.getDireita(), dot);
        }
    }
}