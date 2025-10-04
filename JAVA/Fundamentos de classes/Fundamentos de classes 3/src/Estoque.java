public class Estoque {
    private String produto;
    private int[] quantidades;

    public Estoque() {
        this.produto = "Produto Desconhecido";
        this.quantidades = new int[5];
    }

    public Estoque(String produto, int[] quantidades) {
        this.produto = produto;
        this.quantidades = quantidades;
    }

    public void imprimirEstoque() {
        System.out.println("Produto: " + this.produto);
        System.out.println("Quantidades por lote:");
        for (int i = 0; i < this.quantidades.length; i++) {
            System.out.println("Lote " + (i + 1) + ": " + this.quantidades[i] + " unidades");
        }
    }

    public int calcularTotalEstoque() {
        int total = 0;
        for (int quantidade : this.quantidades) {
            total += quantidade;
        }
        return total;
    }

    public boolean verificarDisponibilidade(int quantidadeMinima) {
        for (int quantidade : this.quantidades) {
            if (quantidade > quantidadeMinima) {
                return true;
            }
        }
        return false;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int[] getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(int[] quantidades) {
        this.quantidades = quantidades;
    }
}
