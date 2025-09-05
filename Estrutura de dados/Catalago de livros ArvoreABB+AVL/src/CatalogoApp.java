import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CatalogoApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinariaBusca arvoreBST = new ArvoreBinariaBusca();
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        List<Livro> livrosParaAdicionar = new ArrayList<>();
        livrosParaAdicionar.add(new Livro(101, "O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        livrosParaAdicionar.add(new Livro(50, "1984", "George Orwell"));
        livrosParaAdicionar.add(new Livro(205, "Dom Casmurro", "Machado de Assis"));
        livrosParaAdicionar.add(new Livro(30, "A Revolução dos Bichos", "George Orwell"));
        livrosParaAdicionar.add(new Livro(150, "O Senhor dos Anéis", "J.R.R. Tolkien"));
        livrosParaAdicionar.add(new Livro(25, "Fahrenheit 451", "Ray Bradbury"));
        livrosParaAdicionar.add(new Livro(300, "O Guia do Mochileiro das Galáxias", "Douglas Adams"));

        for (Livro livro : livrosParaAdicionar) {
            arvoreBST.inserir(livro);
            arvoreAVL.inserir(livro);
        }

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\n========= SISTEMA DE CATÁLOGO (BST vs. AVL) [COMPLETO] =========");
            System.out.println("1. Inserir Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Buscar Livro (pela AVL)");
            System.out.println("4. Listar Livros em Largura (BST)");
            System.out.println("5. Gerar Visualização da Árvore BST");
            System.out.println("6. Gerar Visualização da Árvore AVL");
            System.out.println("7. Comparar Performance");
            System.out.println("0. Sair do Sistema");
            System.out.print(">> Escolha uma opção: ");

            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                continue;
            }

            switch (escolha) {
                case 1:
                    try {
                        System.out.print("Digite o ID do livro: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite o Título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o Autor do livro: ");
                        String autor = scanner.nextLine();

                        Livro novoLivro = new Livro(id, titulo, autor);

                        arvoreBST.inserir(novoLivro);
                        arvoreAVL.inserir(novoLivro);

                        System.out.println("=> Livro inserido em ambas as árvores!");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: O ID deve ser um número inteiro.");
                    }
                    break;
                case 2:
                    try {
                        List<Integer> idsDisponiveis = arvoreAVL.getTodosIds();
                        System.out.print("Digite o ID do livro para remover: ");
                        System.out.println("IDs disponíveis: " + idsDisponiveis);
                        int idRemover = Integer.parseInt(scanner.nextLine());

                        arvoreBST.remover(idRemover);
                        arvoreAVL.remover(idRemover);

                        System.out.println("=> Livro com ID " + idRemover + " removido de ambas as árvores (se existia).");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: O ID deve ser um número inteiro.");
                    }
                    break;
                case 3:
                    try {
                        List<Integer> idsDisponiveis = arvoreAVL.getTodosIds();
                        if (idsDisponiveis.isEmpty()) {
                            System.out.println("Nenhum livro no catálogo para buscar.");
                            break;
                        }

                        System.out.println("IDs disponíveis: " + idsDisponiveis);
                        System.out.print("Digite o ID do livro para buscar: ");
                        int idBusca = Integer.parseInt(scanner.nextLine());
                        Livro livroEncontrado = arvoreAVL.buscar(idBusca);

                        if (livroEncontrado != null) {
                            System.out.println("=> Livro encontrado: " + livroEncontrado);
                        } else {
                            System.out.println("=> Livro com ID " + idBusca + " não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: O ID deve ser um número inteiro.");
                    }
                    break;
                case 4:
                    arvoreBST.imprimirEmLargura();
                    break;
                case 5:
                    gerarVisualizacao(arvoreBST.gerarDotParaGraphviz(), "bst.dot", "bst.png");
                    break;
                case 6:
                    gerarVisualizacao(arvoreAVL.gerarDotParaGraphviz(), "avl.dot", "avl.png");
                    break;
                case 7:
                    System.out.println("\n--- Análise Comparativa de Performance ---");
                    System.out.println("Altura da Árvore BST: " + arvoreBST.getAltura());
                    System.out.println("Altura da Árvore AVL: " + arvoreAVL.getAltura());
                    System.out.println("Total de Rotações na AVL: " + arvoreAVL.getContadorRotacoes());
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private static void gerarVisualizacao(String dotString, String dotFileName, String pngFileName) {
        try {
            FileWriter writer = new FileWriter(dotFileName);
            writer.write(dotString);
            writer.close();
            System.out.printf("\n=> Arquivo '%s' gerado com sucesso!\n", dotFileName);
            System.out.println("   Para criar a imagem, execute no terminal:");
            System.out.printf("   dot -Tpng %s -o %s\n", dotFileName, pngFileName);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao gerar o arquivo .dot: " + e.getMessage());
        }
    }
}