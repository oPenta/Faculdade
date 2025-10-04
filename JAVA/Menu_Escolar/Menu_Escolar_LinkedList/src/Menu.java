import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            LinkedList<String> alunos = new LinkedList<>();
            LinkedList<List<Double>> notas = new LinkedList<>();

            while (true) {
                System.out.println("1 - Cadastrar aluno");
                System.out.println("2 - Cadastrar nota");
                System.out.println("3 - Calcular média de um aluno");
                System.out.println("4 - Listar os nomes dos alunos sem notas");
                System.out.println("5 - Excluir aluno");
                System.out.println("6 - Excluir nota");
                System.out.println("7 - Listar nomes Cadastrados");
                System.out.println("8 - Sair");

                int opcao = scanner.nextInt();
                if (opcao == 8 ){
                break;
                }

                switch (opcao) {
                    case 1: // CADASTRAR ALUNO
                        System.out.println("Digite o nome do aluno: ");
                        String nome = scanner.next();
                        alunos.add(nome);
                        notas.add(new LinkedList<>());
                        break;
                    case 2: // CADASTRAR NOTA
                        System.out.println("\nDigite o nome do aluno: ");
                        String aluno = scanner.next();
                        int index = alunos.indexOf(aluno);
                        if (index == -1) {
                            System.out.println("Aluno não encontrado.");
                            break;
                        }
                        System.out.println("Digite a nota: ");
                        double nota = scanner.nextDouble();
                        notas.get(index).add(nota);
                        break;



                    case 3: // CALCULAR MÉDIA DE UM ALUNO
                        System.out.println("\nDigite o nome do aluno: ");
                        String aluno2 = scanner.next();
                        int index2 = alunos.indexOf(aluno2);
                        if (index2 == -1) {
                            System.out.println("Aluno não encontrado.");
                            break;
                        }
                        List<Double> notasDoAluno = notas.get(index2);
                        if (notasDoAluno.size() < 2) {
                            System.out.println("Não é possível calcular a média com menos de 2 notas.");
                            break;
                        }
                        double soma = 0;
                        for (Double notaDoAluno : notasDoAluno) {
                            soma += notaDoAluno;
                        }
                        double media = soma / notasDoAluno.size();
                        System.out.println("Média de " + aluno2 + ": " + media);
                        break;



                    case 4: // LISTAR OS NOMES DOS ALUNOS SEM NOTAS   
                        System.out.println("Alunos sem notas: ");
                    
                        for (int i = 0; i < alunos.size(); i++) {
                            if (notas.get(i).size() == 0) {
                                System.out.println(alunos.get(i));
                            }
                        }
                        break;



                    case 5: // EXCLUIR ALUNO
                        System.out.println("\nDigite o nome do aluno que deseja excluir: ");
                        String aluno3 = scanner.next();
                        int index3 = alunos.indexOf(aluno3);
                        if (index3 == -1) {
                            System.out.println("Aluno não encontrado.");
                        } else {
                            alunos.remove(index3);
                            notas.remove(index3);
                            System.out.println("Aluno removido com sucesso");
                        }
                        break;
                    


                    case 6: // EXCLUIR NOTA
                        System.out.println("\nDigite o nome do aluno que deseja excluir suas notas: ");
                        String aluno4 = scanner.next();
                        int index4 = alunos.indexOf(aluno4);
                        if (index4 == -1) {
                            System.out.println("Aluno não encontrado.");
                             break;
                          }
                        List<Double> notasdoAluno = notas.get(index4);
                        if (notasdoAluno.size() == 0) {
                          System.out.println("Aluno não tem notas para excluir.");
                            break;
                        }
                        System.out.println("Notas do aluno: " + notasdoAluno);
                        System.out.println("Digite o número da nota que deseja excluir (1-" + notasdoAluno.size() + "): ");
                        int notaIndex = scanner.nextInt() - 1;
                        if (notaIndex < 0 || notaIndex >= notasdoAluno.size()) {
                            System.out.println("Nota inválida.");
                            break;
                        }
                        notasdoAluno.remove(notaIndex);
                        System.out.println("Nota removida com sucesso");
                        break;
                    
                    

                        case 7:
                        System.out.println("\nAlunos cadastrados:");
                        for (int i = 0; i < alunos.size(); i++) {
                            System.out.println((i + 1) + ". " + alunos.get(i));
                        }
                        break;
                    
            

                    case 8: //SAIR
                        break;
                    

                    default:
                        System.out.println("Opcao invalida");
                        break;

                    
    
}   }   }   }   }          
                