import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("1-Carro" +"\n"+
                "2-Carro2" +"\n"+
                "3-Livro" +"\n"+
                "4-Aluno" +"\n"+
                "5-Conta e Cliente");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        switch(n){
            case 1:
                System.out.println("Carro");
                Carro carro = new Carro();
                carro.exibirDetalhes();
                System.out.println("\n");
            break;
            
            case 2:    
                System.out.println("Carro com parâmetros");
                Carro2 carro2 = new Carro2("chevrolet","s10", 2020, 100000);
                carro2.exibirDetalhes();
            break;

            case 3:
                System.out.println("Livro");
                Livro livro = new Livro();
                livro.detalhesDoLivro();
                System.out.println("\n");
            break;

            case 4:
                System.out.println("Aluno");
                Aluno aluno = new Aluno();
                aluno.exibirInformacoes();    
            break;

            case 5:
                System.out.println("ContaBancaria e Cliente");
                ContaBancaria contaBancaria = new ContaBancaria("123123",0);
                contaBancaria.exibirSaldo();
                Cliente cliente = new Cliente("Paulo","12859112398");
                cliente.exibirDadosCliente();
            break;
            default:
                scanner.close();
             
               

        }
    }
}
