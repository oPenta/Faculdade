public class Carro2 {

    public String marca;
    private String modelo;
    protected int ano;
    double preco;

    public Carro2(String marca,String modelo,int ano,double preco){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;


    }
    public void exibirDetalhes(){
        System.out.print(this.marca +"\n"+
                this.modelo +"\n"+
                this.ano + "\n"+
                this.preco
        );


    }

}

