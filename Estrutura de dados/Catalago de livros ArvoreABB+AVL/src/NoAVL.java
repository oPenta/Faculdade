public class NoAVL {
    private Livro livro;
    private int altura;
    private NoAVL esquerda;
    private NoAVL direita;

    public NoAVL(Livro livro) {
        this.livro = livro;
        this.altura = 1;
        this.esquerda = null;
        this.direita = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NoAVL getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoAVL esquerda) {
        this.esquerda = esquerda;
    }

    public NoAVL getDireita() {
        return direita;
    }

    public void setDireita(NoAVL direita) {
        this.direita = direita;
    }
}