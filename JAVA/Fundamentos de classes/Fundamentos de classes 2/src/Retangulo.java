import java.util.Scanner;

public class Retangulo {
    double largura;
    double altura;
    
    public Retangulo(){
        this.largura = 10;
        this.altura = 10;
    }
    public Retangulo(double largura, double altura){
        this.largura = largura;
        this.altura = altura;
    }

    public void calcularArea(){
        System.out.println("Area:"+ altura*largura);
    }

    public void aumentarTamanho(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a nova altura:");
        double novaAltura = scanner.nextDouble();
        System.out.println("Digite a nova Largura:");
        double novaLargura = scanner.nextDouble();
        System.out.println("Nova area:"+(largura+novaLargura)*(altura+novaAltura));
        scanner.close();
    }
}
