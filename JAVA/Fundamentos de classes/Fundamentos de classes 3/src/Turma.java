public class Turma {
    private String nomeTurma;
    private String[] alunos;

    public Turma() {
        this.nomeTurma = "Sem Nome";
        this.alunos = new String[5];
    }

    public Turma(String nomeTurma, String[] alunos) {
        this.nomeTurma = nomeTurma;
        this.alunos = alunos;
    }

    public void imprimirTurma() {
        System.out.println("Nome da Turma: " + this.nomeTurma);
        System.out.println("Alunos:");
        for (String aluno : this.alunos) {
            if (aluno != null) {
                System.out.println(aluno);
            }
        }
    }

    public int quantidadeAlunos() {
        int contador = 0;
        for (String aluno : this.alunos) {
            if (aluno != null) {
                contador++;
            }
        }
        return contador;
    }

    public boolean buscarAluno(String nomeAluno) {
        for (String aluno : this.alunos) {
            if (aluno != null && aluno.equals(nomeAluno)) {
                return true;
            }
        }
        return false;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String[] getAlunos() {
        return alunos;
    }

    public void setAlunos(String[] alunos) {
        this.alunos = alunos;
    }
}
