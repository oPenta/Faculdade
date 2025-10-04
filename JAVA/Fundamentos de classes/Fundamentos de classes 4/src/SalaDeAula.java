public class SalaDeAula {

    private String nomeTurma;
    private int[][] notasAlunos;

    public SalaDeAula() {
        nomeTurma = "Turma Desconhecida";
        notasAlunos = new int[5][3];         
    }

        public SalaDeAula(String nomeTurma, int[][] notasAlunos) {
        this.nomeTurma = nomeTurma;
        this.notasAlunos = notasAlunos;
    }

        public void imprimirInformacoes() {
        System.out.println("Nome da Turma: " + nomeTurma);
        for (int i = 0; i < notasAlunos.length; i++) {
            System.out.print("Aluno " + (i + 1) + " notas: ");
            for (int j = 0; j < notasAlunos[i].length; j++) {
                System.out.print(notasAlunos[i][j] + " ");
            }
            System.out.println();
        }
    }

  
    public boolean verificarNotasAcima(int valorMinimo) {
        for (int[] alunoNotas : notasAlunos) {
            for (int nota : alunoNotas) {
                if (nota < valorMinimo) {
                    return false;  
                }
            }
        }
        return true;   
  }
}
