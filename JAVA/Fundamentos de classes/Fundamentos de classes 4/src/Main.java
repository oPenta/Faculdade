import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Trabalhar com Sala de Aula");
            System.out.println("2. Trabalhar com Estoque da Loja");
            System.out.println("3. Trabalhar com Cinema");
            System.out.println("4. Trabalhar com Matriz Numérica");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                break;
            }

            switch (opcao) {
                case 1:
                    SalaDeAula turma = new SalaDeAula();
                    System.out.println("Deseja criar a turma com notas pré-definidas? (s/n)");
                    String respostaTurma = scanner.nextLine();

                    if (respostaTurma.equalsIgnoreCase("s")) {
                        System.out.println("Introduza o nome da turma:");
                        String nomeTurma = scanner.nextLine();
                        int[][] notasAlunos = new int[5][3];

                        for (int i = 0; i < 5; i++) {
                            System.out.println("Introduza as notas do aluno " + (i + 1) + ":");
                            for (int j = 0; j < 3; j++) {
                                System.out.print("Nota " + (j + 1) + ": ");
                                notasAlunos[i][j] = scanner.nextInt();
                            }
                        }
                        scanner.nextLine();
                        turma = new SalaDeAula(nomeTurma, notasAlunos);
                    }

                    turma.imprimirInformacoes();
                    System.out.println("Introduza um valor mínimo para verificar as notas:");
                    int valorMinimo = scanner.nextInt();
                    scanner.nextLine();

                    boolean resultadoTurma = turma.verificarNotasAcima(valorMinimo);
                    System.out.println(resultadoTurma ? "Todas as notas são maiores ou iguais ao valor mínimo." : "Nem todas as notas são maiores ou iguais ao valor mínimo.");
                    break;

                case 2:
                    EstoqueLoja estoque = new EstoqueLoja();
                    System.out.println("Deseja criar o estoque com valores pré-definidos? (s/n)");
                    String respostaEstoque = scanner.nextLine();

                    if (respostaEstoque.equalsIgnoreCase("s")) {
                        System.out.println("Introduza o nome do produto:");
                        String nomeProduto = scanner.nextLine();
                        int[][] quantidadesPorLote = new int[4][2];

                        for (int i = 0; i < 4; i++) {
                            System.out.println("Lote " + (i + 1) + " - Introduza a quantidade disponível:");
                            quantidadesPorLote[i][0] = scanner.nextInt();
                            System.out.println("Lote " + (i + 1) + " - Introduza a quantidade vendida:");
                            quantidadesPorLote[i][1] = scanner.nextInt();
                        }
                        scanner.nextLine();

                        estoque = new EstoqueLoja(nomeProduto, quantidadesPorLote);
                    }

                    estoque.imprimirEstoque();
                    System.out.println("Introduza um valor limite para verificar se há lotes críticos:");
                    int limiteVenda = scanner.nextInt();
                    scanner.nextLine();

                    boolean resultadoEstoque = estoque.verificarLoteCritico(limiteVenda);
                    System.out.println(resultadoEstoque ? "Há pelo menos um lote crítico." : "Nenhum lote crítico.");
                    break;

                case 3:
                    Cinema cinema = new Cinema();
                    System.out.println("Deseja definir os assentos manualmente? (s/n)");
                    String respostaCinema = scanner.nextLine();

                    if (respostaCinema.equalsIgnoreCase("s")) {
                        System.out.println("Introduza o nome do cinema:");
                        String nomeCinema = scanner.nextLine();
                        boolean[][] assentos = new boolean[5][5];

                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                System.out.println("Assento da fileira " + (i + 1) + " posição " + (j + 1) + " está ocupado? (s/n)");
                                assentos[i][j] = scanner.nextLine().equalsIgnoreCase("s");
                            }
                        }

                        cinema = new Cinema(nomeCinema, assentos);
                    }

                    cinema.imprimirAssentos();
                    System.out.println("Introduza o número da fileira para verificar a ocupação:");
                    int numeroFileira = scanner.nextInt();
                    System.out.println("Introduza o limite de ocupação:");
                    int limiteOcupacao = scanner.nextInt();
                    scanner.nextLine();

                    boolean resultadoCinema = cinema.verificarLotacaoFileira(numeroFileira, limiteOcupacao);
                    System.out.println(resultadoCinema ? "A fileira está acima do limite de ocupação." : "A fileira está dentro do limite.");
                    break;

                case 4:
                    MatrizNumerica matriz = new MatrizNumerica();
                    System.out.println("Deseja criar a matriz manualmente? (s/n)");
                    String respostaMatriz = scanner.nextLine();

                    if (respostaMatriz.equalsIgnoreCase("s")) {
                        System.out.println("Introduza o nome da matriz:");
                        String nomeMatriz = scanner.nextLine();
                        int[][] matrizNumeros = new int[3][3];

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                System.out.print("Introduza o valor para a posição [" + i + "][" + j + "]: ");
                                matrizNumeros[i][j] = scanner.nextInt();
                            }
                        }
                        scanner.nextLine();

                        matriz = new MatrizNumerica(nomeMatriz, matrizNumeros);
                    }

                    matriz.imprimirMatriz();
                    System.out.println("Introduza um valor mínimo para verificar a diagonal:");
                    int valorDiagonal = scanner.nextInt();
                    scanner.nextLine();

                    boolean resultadoMatriz = matriz.verificarDiagonal(valorDiagonal);
                    System.out.println(resultadoMatriz ? "Todos os valores da diagonal são maiores que o valor mínimo." : "Nem todos os valores da diagonal são maiores que o valor mínimo.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
