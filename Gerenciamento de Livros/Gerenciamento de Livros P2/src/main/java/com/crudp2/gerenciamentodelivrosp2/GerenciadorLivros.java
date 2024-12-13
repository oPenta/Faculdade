package com.crudp2.gerenciamentodelivrosp2;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorLivros {
    private List<Livro> livros;

    public GerenciadorLivros() {
        this.livros = new ArrayList<>(); //inicia a lista
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void atualizarLivro(int id, Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) { //verifica a lista
            if (livros.get(i).getId() == id) { //verifica se o id do livro é o mesmo q foi atribuido
                livros.set(i, livroAtualizado); //atualiza o livro
                return;
            }
        }
    }

    public void excluirLivro(int id) {
        livros.removeIf(livro -> livro.getId() == id); //se o id estiver certo, vai excluir o livro
    }

    public List<Livro> listarLivros() {
        return livros;
    }
}