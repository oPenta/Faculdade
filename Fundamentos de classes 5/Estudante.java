import java.util.ArrayList;
import java.util.List;

public class Estudante {
    private String nome;
    private String email;
    List<Curso> cursosInscritos;
    List<Avaliacao> avaliacoesRealizadas;

    public Estudante (String nome, String email){
        this.nome = nome;
        this.email = email;
        this.cursosInscritos = new ArrayList<>();
        this.avaliacoesRealizadas = new ArrayList<>();
    }

    void realizarAvaliacao(Avaliacao avaliacao){
        avaliacoesRealizadas.add(avaliacao);
        avaliacao.estudante = this;
    }

    void mostrarAvalicoes(){
        System.out.printf("Avaliacoes Realizadas por " + nome + ":" );
        for(Avaliacao avaliacao : avaliacoesRealizadas){
            System.out.printf(" " + avaliacao.getTitulo() + " Nota: " + avaliacao.getNota());
        }
    }

    void inscreverCursos(Curso curso){
        cursosInscritos.add(curso);
    }

    void mostrarCursos(){
        System.out.printf("Cursos inscritos de "+ nome + ": ");
        for(Curso curso: cursosInscritos){
            System.out.printf(" "+ curso.getTitulo()+ "\n");
        }

    }
    public  String getNome(){
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public  String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}