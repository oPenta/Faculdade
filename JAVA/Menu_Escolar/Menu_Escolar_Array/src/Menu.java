import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> alunos = new ArrayList<>();
            List<List<Double>> notas = new ArrayList<>();
            String alunoNome;
            int alunoIndex;


            while (true) {
                System.out.println("1 - Cadastrar aluno");
                System.out.println("2 - Cadastrar nota");
                System.out.println("3 - Calcular média de um aluno");
                System.out.println("4 - Listar os nomes dos alunos sem notas");
                System.out.println("5 - Excluir aluno");
                System.out.println("6 - Excluir nota");
                System.out.println("7 - Sair");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:  // CADASTRAR ALUNO
                        System.out.print("Digite o nome do aluno: ");
                        String nome = scanner.next();
                        alunos.add(nome);
                        notas.add(new ArrayList<>());
                        break;


                    case 2: // CADASTRAR NOTA
                        System.out.print("Alunos cadastrados: "); 
                        for (String aluno : alunos) {
                            System.out.print(aluno +", " );
                        }
                        System.out.print("\nDigite o nome do aluno para cadastrar a nota: ");
                        alunoNome = scanner.next();
                        alunoIndex = alunos.indexOf(alunoNome);
                        if (alunoIndex!= -1) {
                            System.out.print("Digite a nota: ");
                            double nota = scanner.nextDouble();
                            notas.get(alunoIndex).add(nota);
                        }
                        break;


                        case 3: // CALCULAR MÉDIA
                            System.out.print("Alunos cadastrados: ");
                            for (String aluno : alunos) {
                                System.out.print(aluno +", " );
                            }
                            System.out.print("\nDigite o nome do aluno para calcular a média: ");
                            alunoNome = scanner.next();
                            alunoIndex = alunos.indexOf(alunoNome);
                            if (alunoIndex!= -1) {
                                List<Double> notasAluno = notas.get(alunoIndex);
                                if (notasAluno.size() >= 2) {
                                    double somaNotas = 0;
                                    for (double nota : notasAluno) {
                                        somaNotas += nota;
                                    }
                                    double media = somaNotas / notasAluno.size();
                                    System.out.println("A média do aluno " + alunoNome + " é: " + media);
                                } else {
                                    System.out.println("O aluno " + alunoNome + " não tem notas suficientes para calcular a média. ");
                                }
                            }
                            break;


                        case 4: //LISTAR ALUNOS SEM NOTA
                            System.out.println("Alunos sem notas: ");
                            for (int i = 0; i < alunos.size(); i++) {
                                if (notas.get(i).isEmpty()) {
                                    System.out.println(alunos.get(i));
                                }
                            }
                            break;


                        case 5: // EXCLUIR ALUNO
                            System.out.print("Alunos cadastrados: ");
                            for (String aluno : alunos) {
                                System.out.print(aluno + ", ");
                            }
                            System.out.print("\nDigite o nome do aluno para excluir: ");
                            alunoNome = scanner.next();
                            alunoIndex = alunos.indexOf(alunoNome);
                            if (alunoIndex!= -1) {
                                alunos.remove(alunoIndex);
                                notas.remove(alunoIndex);
                                System.out.println("Aluno removido com sucesso.");
                            } else {
                                System.out.println("Aluno não encontrado.");
                            }
                            break;


                        case 6: // EXCLUIR NOTA
                            System.out.print("Alunos cadastrados: ");
                            for (String aluno : alunos) {
                                System.out.print(aluno + ", ");
                            }
                            System.out.print("\nDigite o nome do aluno para excluir suas notas: ");
                            alunoNome = scanner.next();
                            alunoIndex = alunos.indexOf(alunoNome);
                            if (alunoIndex!= -1) {
                                List<Double> notasAluno = notas.get(alunoIndex);
                                if (notasAluno.size() > 0) {
                                    notasAluno.clear();
                                    System.out.println("Notas do aluno " + alunoNome + " excluídas com sucesso.");
                                } else {
                                    System.out.println("Aluno não tem notas para excluir.");
                                }
                            } else {
                                System.out.println("Aluno não encontrado.");
                            }
                            break;


                        case 7: //SAIR
                            break;


                        default:
                            System.out.println("Opção inválida.");
                        }

                    
                    if (opcao == 7) {
                        break;
                                
                        
                }
            }
        }
    }
}