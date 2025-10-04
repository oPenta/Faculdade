public class Empresa {
    private String nome;
    private String[] funcionarios;

    public Empresa() {
        this.nome = "Empresa Desconhecida";
        this.funcionarios = new String[8];
    }

    public Empresa(String nome, String[] funcionarios) {
        this.nome = nome;
        this.funcionarios = funcionarios;
    }

    public void imprimirFuncionarios() {
        System.out.println("Nome da Empresa: " + this.nome);
        System.out.println("Funcionários:");
        for (String funcionario : this.funcionarios) {
            if (funcionario != null) {
                System.out.println(funcionario);
            }
        }
    }

    public int contarFuncionarios() {
        int contador = 0;
        for (String funcionario : this.funcionarios) {
            if (funcionario != null) {
                contador++;
            }
        }
        return contador;
    }

    public boolean verificarFuncionario(String nomeFuncionario) {
        for (String funcionario : this.funcionarios) {
            if (funcionario != null && funcionario.equals(nomeFuncionario)) {
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(String[] funcionarios) {
        this.funcionarios = funcionarios;
    }
}
