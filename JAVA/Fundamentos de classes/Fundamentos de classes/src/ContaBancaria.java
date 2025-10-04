public class ContaBancaria {

    public String numeroConta;
    private double saldo;

    public ContaBancaria(String numeroConta, double saldo){
        this.numeroConta = numeroConta;
        this.saldo = saldo;

        }
    public void exibirSaldo(){
        System.out.println(
                numeroConta + "\n" +
                saldo);
    }
}
