public class Funcionario {
    String nome;
    double salario;
    int horasTrabalhadas;

    public Funcionario(){
        this.nome = "Azevedo";
        this.salario = 10;
        this.horasTrabalhadas = 12;
    }
    public Funcionario(String nome, double salario, int horasTrabalhadas){
        this.nome = nome;
        this.salario = salario;
        this.horasTrabalhadas = horasTrabalhadas;
    }
    public void calcularSalarioMensal(){
        System.out.println(salario*horasTrabalhadas);
    }
    public void ajustarSalario(){
        salario += salario*(0.1);
    }
}
