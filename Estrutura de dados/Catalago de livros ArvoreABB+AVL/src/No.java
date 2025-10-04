public class No {
    private Livro livro;
    private No esquerda;
    private No direita;

    public No(Livro livro) {
        this.livro = livro;
        this.esquerda = null;
        this.direita = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}