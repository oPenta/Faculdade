import java.util.LinkedList;

public class TabelaHash {
    private int tamanho;
    private LinkedList<Pessoa>[] tabela;
    private int numeroDeElementos;
    private int numeroDeColisoes;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new LinkedList[tamanho];
        this.numeroDeElementos = 0;
        this.numeroDeColisoes = 0;
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(String cpf) {
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");
        long hash = Long.parseLong(cpfNumerico);
        return (int) (hash % tamanho);
    }

    public void inserir(Pessoa pessoa) {
        String cpf = pessoa.getCpf();
        int indice = funcaoHash(cpf);
        LinkedList<Pessoa> lista = tabela[indice];

        for (Pessoa p : lista) {
            if (p.getCpf().equals(cpf)) {
                System.out.println("CPF " + cpf + " já cadastrado.");
                return;
            }
        }

        if (!lista.isEmpty()) {
            numeroDeColisoes++;
        }

        lista.add(pessoa);
        numeroDeElementos++;
        System.out.println(pessoa.getNome() + " cadastrado(a).");
    }

    public Pessoa buscar(String cpf) {
        int indice = funcaoHash(cpf);
        LinkedList<Pessoa> lista = tabela[indice];

        for (Pessoa p : lista) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public boolean excluir(String cpf) {
        int indice = funcaoHash(cpf);
        LinkedList<Pessoa> lista = tabela[indice];

        for (Pessoa p : lista) {
            if (p.getCpf().equals(cpf)) {
                lista.remove(p);
                numeroDeElementos--;
                return true;
            }
        }
        return false;
    }

    public void imprimirTabela() {
        System.out.println("\n--- ESTADO ATUAL DA TABELA HASH ---");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Índice [" + i + "]: ");
            LinkedList<Pessoa> lista = tabela[i];
            if (lista.isEmpty()) {
                System.out.println("Vazio");
            } else {
                for (Pessoa p : lista) {
                    System.out.print(p.toString() + " -> ");
                }
                System.out.println("null");
            }
        }
        System.out.println("------------------------------------");
        imprimirEstatisticas();
    }

    public void imprimirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DE EFICIÊNCIA ---");
        System.out.println("Tamanho da Tabela: " + tamanho);
        System.out.println("Número Total de Registros: " + numeroDeElementos);

        double fatorDeCarga = (double) numeroDeElementos / tamanho;
        System.out.printf("Fator de Carga (Ocupação): %.2f\n", fatorDeCarga);

        System.out.println("Número de Colisões na Inserção: " + numeroDeColisoes);
    }
}