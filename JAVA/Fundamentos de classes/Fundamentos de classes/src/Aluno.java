public class Aluno {
    public String nome;
    private String idade;
    protected String matricula;
    String curso;

    public Aluno(){
        this.nome = "Brunão Almeida";
        this.idade = "35";
        this.matricula = "192045123";
        this.curso = "Engenharia de Software";
    }
    public void exibirInformacoes(){
        System.out.print(this.nome+"\n"+
                this.idade+"\n"+
                this.matricula+ "\n"+
                this.curso
        );
    }



}
