import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TabelaHash tabela = new TabelaHash(20);

        //So pra nao ter que colocar todos manualmente
        tabela.inserir(new Pessoa("111.222.333-44", "Orochinho ", 28));
        tabela.inserir(new Pessoa("222.333.444-55", "Lucas Inutilismo", 35));
        tabela.inserir(new Pessoa("333.444.555-66", "Peter Einerd ", 42));
        tabela.inserir(new Pessoa("444.555.666-77", "Goularte", 21));
        tabela.inserir(new Pessoa("555.666.777-88", "Bistecone", 53));

        boolean executando = true;

        while (executando) {
            System.out.println("\n====== SISTEMA DE CADASTRO ======");
            System.out.println("1. Inserir Pessoa");
            System.out.println("2. Buscar Pessoa por CPF");
            System.out.println("3. Excluir Pessoa por CPF");
            System.out.println("4. Imprimir Tabela Completa");
            System.out.println("0. Sair do Sistema");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o CPF (ex: 123.456.789-00): ");
                        String cpf = scanner.nextLine();
                        System.out.print("Digite o Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite a Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();

                        tabela.inserir(new Pessoa(cpf, nome, idade));
                        break;

                    case 2:
                        System.out.print("Digite o CPF que deseja buscar: ");
                        String cpfBusca = scanner.nextLine();
                        Pessoa encontrada = tabela.buscar(cpfBusca);
                        if (encontrada != null) {
                            System.out.println("Pessoa encontrada: " + encontrada);
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                        break;

                    case 3:
                        System.out.print("Digite o CPF que deseja excluir: ");
                        String cpfExclusao = scanner.nextLine();
                        boolean excluido = tabela.excluir(cpfExclusao);
                        if (excluido) {
                            System.out.println("Pessoa com CPF" + cpfExclusao + " foi excluída.");
                        } else {
                            System.out.println("Pessoa com CPF " + cpfExclusao + " não foi encontrada para exclusão.");
                        }
                        break;

                    case 4:
                        tabela.imprimirTabela();
                        break;

                    case 0:
                        executando = false;
                        System.out.println("Finalizando...");
                        break;

                    default:
                        System.out.println("Opção invalida!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número para a opção");
                scanner.nextLine();
            }
        }
    }
}