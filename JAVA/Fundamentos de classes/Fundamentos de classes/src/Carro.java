public class Carro {
    public String marca;
    private String modelo;
    protected int ano;
    double preco;

    public Carro(){
        this.marca = "chevrolet";
        this.modelo = "s10";
        this.ano = 2020;
        this.preco = 100000;
    }
    public void exibirDetalhes(){
        System.out.print(this.marca +"\n"+
                this.modelo +"\n"+
                this.ano + "\n"+
                this.preco
        );
    }
}