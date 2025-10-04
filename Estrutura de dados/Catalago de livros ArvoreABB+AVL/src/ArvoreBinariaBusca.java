import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

public class ArvoreBinariaBusca {
    private No raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(Livro livro) {
        this.raiz = inserirRec(this.raiz, livro);
    }

    public Livro buscar(int id) {
        No noEncontrado = buscarRec(this.raiz, id);
        return (noEncontrado != null) ? noEncontrado.getLivro() : null;
    }

    public void remover(int id) {
        this.raiz = removerRec(this.raiz, id);
    }

    public void imprimirEmLargura() {
        System.out.println("\n--- Listando em Largura (Nível por Nível) ---");
        if (this.raiz == null) {
            System.out.println("Árvore vazia.");
            System.out.println("------------------------------------------");
            return;
        }
        Queue<No> fila = new LinkedList<>();
        fila.add(this.raiz);
        while (!fila.isEmpty()) {
            No noAtual = fila.poll();
            System.out.println(noAtual.getLivro());
            if (noAtual.getEsquerda() != null) {
                fila.add(noAtual.getEsquerda());
            }
            if (noAtual.getDireita() != null) {
                fila.add(noAtual.getDireita());
            }
        }
        System.out.println("------------------------------------------");
    }

    public List<Integer> getTodosIds() {
        List<Integer> ids = new ArrayList<>();
        getTodosIdsRec(this.raiz, ids);
        return ids;
    }

    private void getTodosIdsRec(No no, List<Integer> ids) {
        if (no != null) {
            getTodosIdsRec(no.getEsquerda(), ids);
            ids.add(no.getLivro().getId());
            getTodosIdsRec(no.getDireita(), ids);
        }
    }

    public int getAltura() {
        return calcularAlturaRec(this.raiz);
    }

    private int calcularAlturaRec(No no) {
        if (no == null) {
            return 0;
        }
        int alturaEsquerda = calcularAlturaRec(no.getEsquerda());
        int alturaDireita = calcularAlturaRec(no.getDireita());
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    private No inserirRec(No noAtual, Livro livro) {
        if (noAtual == null) {
            return new No(livro);
        }
        if (livro.getId() < noAtual.getLivro().getId()) {
            noAtual.setEsquerda(inserirRec(noAtual.getEsquerda(), livro));
        } else if (livro.getId() > noAtual.getLivro().getId()) {
            noAtual.setDireita(inserirRec(noAtual.getDireita(), livro));
        }
        return noAtual;
    }

    private No buscarRec(No noAtual, int id) {
        if (noAtual == null || noAtual.getLivro().getId() == id) {
            return noAtual;
        }
        if (id < noAtual.getLivro().getId()) {
            return buscarRec(noAtual.getEsquerda(), id);
        }
        return buscarRec(noAtual.getDireita(), id);
    }

    private No removerRec(No noAtual, int id) {
        if (noAtual == null) return null;
        if (id < noAtual.getLivro().getId()) {
            noAtual.setEsquerda(removerRec(noAtual.getEsquerda(), id));
        } else if (id > noAtual.getLivro().getId()) {
            noAtual.setDireita(removerRec(noAtual.getDireita(), id));
        } else {
            if (noAtual.getEsquerda() == null) return noAtual.getDireita();
            if (noAtual.getDireita() == null) return noAtual.getEsquerda();
            No sucessor = encontrarMinimo(noAtual.getDireita());
            noAtual.setLivro(sucessor.getLivro());
            noAtual.setDireita(removerRec(noAtual.getDireita(), sucessor.getLivro().getId()));
        }
        return noAtual;
    }

    private No encontrarMinimo(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    public String gerarDotParaGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph BST {\n");
        dot.append("  node [fontname=\"Arial\", shape=record, style=rounded];\n");
        if (this.raiz == null) {
            dot.append("  NULL [shape=plaintext, label=\"Árvore Vazia\"];\n");
        } else {
            gerarDotRec(this.raiz, dot);
        }
        dot.append("}\n");
        return dot.toString();
    }

    private void gerarDotRec(No no, StringBuilder dot) {
        if (no == null) {
            return;
        }
        String titulo = no.getLivro().getTitulo().replace("\"", "\\\"");
        String autor = no.getLivro().getAutor().replace("\"", "\\\"");
        dot.append(String.format("  %d [label=\"{ID: %d | %s | %s}\"];\n",
                no.getLivro().getId(),
                no.getLivro().getId(),
                titulo,
                autor
        ));
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