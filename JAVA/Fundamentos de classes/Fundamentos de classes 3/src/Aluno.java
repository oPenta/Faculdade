public class Aluno {
    private String nome;
    private double[] notas;

    public Aluno() {
        this.nome = "Desconhecido";
        this.notas = new double[4];
    }

    public Aluno(String nome, double[] notas) {
        this.nome = nome;
        this.notas = notas;
    }

    public void imprimirInformacoes() {
        System.out.println("Nome: " + this.nome);
        System.out.print("Notas: ");
        for (double nota : this.notas) {
            System.out.print(nota + " ");
        }
        System.out.println();
    }

    public double calcularMedia() {
        double soma = 0;
        for (double nota : this.notas) {
            soma += nota;
        }
        return soma / this.notas.length;
    }

    public boolean verificarNotaAprovacao(double valorAprovacao) {
        for (double nota : this.notas) {
            if (nota < valorAprovacao) {
                return false;
            }
        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }
}
