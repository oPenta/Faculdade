public class Biblioteca {
    private String nome;
    private String[] livros;

    public Biblioteca() {
        this.nome = "Biblioteca Desconhecida";
        this.livros = new String[10];
    }

    public Biblioteca(String nome, String[] livros) {
        this.nome = nome;
        this.livros = livros;
    }

    public void imprimirLivros() {
        System.out.println("Nome da Biblioteca: " + this.nome);
        System.out.println("Livros:");
        for (String livro : this.livros) {
            if (livro != null) {
                System.out.println(livro);
            }
        }
    }

    public int contarLivrosDisponiveis() {
        int contador = 0;
        for (String livro : this.livros) {
            if (livro != null) {
                contador++;
            }
        }
        return contador;
    }

    public boolean verificarDisponibilidade(String nomeLivro) {
        for (String livro : this.livros) {
            if (livro != null && livro.equals(nomeLivro)) {
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getLivros() {
        return livros;
    }

    public void setLivros(String[] livros) {
        this.livros = livros;
    }
}
