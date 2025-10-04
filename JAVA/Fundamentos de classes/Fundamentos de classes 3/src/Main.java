import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("1-Aluno" +"\n"+
                "2-Turma" +"\n"+
                "3-Biblioteca" +"\n"+
                "4-Empresa" +"\n"+
                "5-Estoque");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        switch (n) {
        
        case 1:
            Aluno aluno1 = new Aluno();
            aluno1.imprimirInformacoes();

            double[] notasAluno1 = {7.5, 8.0, 6.0, 9.0};
            aluno1.setNotas(notasAluno1);
            aluno1.imprimirInformacoes();

            double mediaAluno1 = aluno1.calcularMedia();
            System.out.println("Média do aluno1: " + mediaAluno1);

            boolean passouAluno1 = aluno1.verificarNotaAprovacao(5.0);
            System.out.println("Aluno1 aprovado: " + passouAluno1);

            double[] notasAluno2 = {9.0, 7.5, 8.5, 10.0};
            Aluno aluno2 = new Aluno("João", notasAluno2);
            aluno2.imprimirInformacoes();

            double mediaAluno2 = aluno2.calcularMedia();
            System.out.println("Média do aluno2: " + mediaAluno2);

            boolean passouAluno2 = aluno2.verificarNotaAprovacao(6.0);
            System.out.println("Aluno2 aprovado: " + passouAluno2);
            break;

        case 2:  
            String[] alunosTurma1 = {"Ana", "Bruno", "Carlos", "Diana", null};
            Turma turma1 = new Turma("Turma A", alunosTurma1);

            turma1.imprimirTurma();

            int quantidade = turma1.quantidadeAlunos();
            System.out.println("Quantidade de alunos: " + quantidade);

            boolean alunoEncontrado = turma1.buscarAluno("Carlos");
            System.out.println("Aluno Carlos está na turma: " + alunoEncontrado);

            alunoEncontrado = turma1.buscarAluno("Lucas");
            System.out.println("Aluno Lucas está na turma: " + alunoEncontrado);
        case 3:
            String[] livrosBiblioteca1 = {"Dom Quixote", "O Senhor dos Anéis", "1984", "O Pequeno Príncipe", null, null, null, null, null, null};
            Biblioteca biblioteca1 = new Biblioteca("Biblioteca Central", livrosBiblioteca1);

            biblioteca1.imprimirLivros();

            int quantidadeLivros = biblioteca1.contarLivrosDisponiveis();
            System.out.println("Quantidade de livros disponíveis: " + quantidadeLivros);

            boolean livroDisponivel = biblioteca1.verificarDisponibilidade("1984");
            System.out.println("Livro '1984' está disponível: " + livroDisponivel);

            livroDisponivel = biblioteca1.verificarDisponibilidade("A Revolução dos Bichos");
            System.out.println("Livro 'A Revolução dos Bichos' está disponível: " + livroDisponivel);
        
        case 4:
            String[] funcionariosEmpresa1 = {"Alice", "Bruno", "Carlos", "Diana", "Eduardo", null, null, null};
            Empresa empresa1 = new Empresa("Tech Solutions", funcionariosEmpresa1);

            empresa1.imprimirFuncionarios();

            int quantidadeFuncionarios = empresa1.contarFuncionarios();
            System.out.println("Quantidade de funcionários cadastrados: " + quantidadeFuncionarios);

            boolean funcionarioEncontrado = empresa1.verificarFuncionario("Carlos");
            System.out.println("Funcionário Carlos está cadastrado: " + funcionarioEncontrado);

            funcionarioEncontrado = empresa1.verificarFuncionario("Fábio");
            System.out.println("Funcionário Fábio está cadastrado: " + funcionarioEncontrado);
        
        case 5:
            int[] quantidadesLotes = {50, 30, 70, 20, 40};
            Estoque estoque1 = new Estoque("Produto A", quantidadesLotes);

            estoque1.imprimirEstoque();

            int totalEstoque = estoque1.calcularTotalEstoque();
            System.out.println("Total de itens em estoque: " + totalEstoque);

            boolean disponibilidade = estoque1.verificarDisponibilidade(60);
            System.out.println("Algum lote tem mais de 60 unidades: " + disponibilidade);

            disponibilidade = estoque1.verificarDisponibilidade(80);
            System.out.println("Algum lote tem mais de 80 unidades: " + disponibilidade);

        default:
            System.out.println("Valor invalido");
            scanner.close();
        
    }
}
}
