public class Main {
    public static void main(String[] args) {
        Estudante estudante1 = new Estudante("Paulo","paulo@gmail.com");
        Estudante estudante2 = new Estudante("Lucas","lucas@gmail.com");

        Curso curso1 = new Curso("Matematica","Matematica Basica do 1 ao 9 ano");
        Curso curso2 = new Curso("Geogragia","Geogragia do 1 ao 9 ano");

        System.out.printf("Estudante 1: " + estudante1.getNome()  + " - " + estudante1.getEmail() + "\n" );
        System.out.printf("Curso 1: " + curso1.getTitulo() + " - " + curso1.getDescricao()+ "\n" );

        estudante1.inscreverCursos(curso1);
        estudante1.mostrarCursos();

        Avaliacao avaliacao1 = new Avaliacao("Prova1", 8.5);
        Avaliacao avaliacao2 = new Avaliacao("Prova2", 9);

        estudante1.realizarAvaliacao(avaliacao1);
        estudante1.mostrarAvalicoes();

    }
}
