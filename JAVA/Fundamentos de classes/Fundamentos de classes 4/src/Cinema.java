public class Cinema {
    private String nomeCinema;
    private boolean[][] assentos;

    public Cinema() {
        nomeCinema = "Cinema Desconhecido";
        assentos = new boolean[5][5];
    }

    public Cinema(String nomeCinema, boolean[][] assentos) {
        this.nomeCinema = nomeCinema;
        this.assentos = assentos;
    }

    public void imprimirAssentos() {
        System.out.println("Nome do Cinema: " + nomeCinema);
        for (int i = 0; i < assentos.length; i++) {
            System.out.print("Fileira " + (i + 1) + ": ");
            for (int j = 0; j < assentos[i].length; j++) {
                System.out.print(assentos[i][j] ? "[Ocupado] " : "[Livre] ");
            }
            System.out.println();
        }
    }

    public boolean verificarLotacaoFileira(int numeroFileira, int limiteOcupacao) {
        int ocupados = 0;
        for (boolean assento : assentos[numeroFileira - 1]) {
            if (assento) {
                ocupados++;
            }
        }
        return ocupados > limiteOcupacao;
    }
}
