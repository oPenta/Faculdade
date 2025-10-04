public class Livro {

    public String titulo;
    private String autor;
    protected int paginas;
    double preco;

    public Livro(){
        this.titulo = "A Volta dos que nao foram";
        this.autor = "Brunão Almeida";
        this.paginas = 1000;
        this.preco = 50;
    }
    public void detalhesDoLivro(){
        System.out.print(this.titulo +"\n"+
                this.autor +"\n"+
                this.paginas + "\n"+
                this.preco
        );
    }
}