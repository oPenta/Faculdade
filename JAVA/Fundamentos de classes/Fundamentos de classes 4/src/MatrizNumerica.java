public class MatrizNumerica {
    private String nomeMatriz;
    private int[][] matrizNumeros;

    public MatrizNumerica() {
        nomeMatriz = "Matriz Desconhecida";
        matrizNumeros = new int[3][3];
    }

    public MatrizNumerica(String nomeMatriz, int[][] matrizNumeros) {
        this.nomeMatriz = nomeMatriz;
        this.matrizNumeros = matrizNumeros;
    }

    public void imprimirMatriz() {
        System.out.println("Nome da Matriz: " + nomeMatriz);
        for (int i = 0; i < matrizNumeros.length; i++) {
            for (int j = 0; j < matrizNumeros[i].length; j++) {
                System.out.print(matrizNumeros[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean verificarDiagonal(int valorMinimo) {
        for (int i = 0; i < matrizNumeros.length; i++) {
            if (matrizNumeros[i][i] <= valorMinimo) {
                return false;
            }
        }
        return true;
    }
}
