public class Cliente {
    protected String nome;
    String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;

    }

    public void exibirDadosCliente(){
            System.out.println(
                    nome + "\n" +
                    cpf);

        }
}