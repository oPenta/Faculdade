import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] cartas = new int[7][1];

        for (int i = 0; i < cartas.length; i++) {
            System.out.println("Valor da carta " + (i + 1) + ": ");
            cartas[i][0] = scanner.nextInt();
            while (cartas[i][0] < 1 || cartas[i][0] > 13) {
                System.out.println("Número inválido, precisa ser de 1 a 13.");
                cartas[i][0] = scanner.nextInt();
            }
        }



        System.out.println("A ordem digitada foi:");//Ordem digitada
        for (int i = 0; i < cartas.length; i++) {
            System.out.print(cartas[i][0] + ", ");
        }



        System.out.println("Escolha o método de ordenação:");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Selection Sort");
        System.out.println("3 - Insertion Sort");
        System.out.println("4 - Quick Sort");
        System.out.println("5 - Heap Sort");
        System.out.println("6 - Merge Sort");

        int method = scanner.nextInt();

        switch (method) {
            case 1:
                bubbleSort.sort(cartas);
                break;
            case 2:
                SelectionSort.sort(cartas);
                break;
            case 3:
                InsertionSort.sort(cartas);
                break;
            case 4:
                QuickSort.sort(cartas, 0, cartas.length - 1);
                break;
            case 5:
                HeapSort.sort(cartas);
                break;
            case 6:
                MergeSort.sort(cartas);
                break;
            default:
                System.out.println("Invalid option. Exiting...");
                System.exit(1);
        }

        for (int i = 0; i < cartas.length; i++) {
            System.out.print(cartas[i][0] + ", ");
        }
    }
}
