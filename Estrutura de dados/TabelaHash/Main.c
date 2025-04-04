#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    long long dado;
    struct No* proximo;
} No;

typedef struct TabelaHash {
    int tamanho;
    No** tabela;
} TabelaHash;

TabelaHash* criarTabelaHash(int tamanho) {
    TabelaHash* tabelaHash = (TabelaHash*)malloc(sizeof(TabelaHash));
    tabelaHash->tamanho = tamanho;
    tabelaHash->tabela = (No**)malloc(tamanho * sizeof(No*));
    for (int i = 0; i < tamanho; i++) {
        tabelaHash->tabela[i] = NULL;
    }
    return tabelaHash;
}

int funcaoHash(long long chave) {
    long long parte1 = chave / 10000000000;
    long long parte2 = chave % 10000000000;
    return (parte1 + parte2) % 1000;
}

void inserir(TabelaHash* tabelaHash, long long dado) {
    int indice = funcaoHash(dado);
    No* novoNo = (No*)malloc(sizeof(No));
    novoNo->dado = dado;
    novoNo->proximo = tabelaHash->tabela[indice];
    tabelaHash->tabela[indice] = novoNo;
}

void imprimirTabelaHash(TabelaHash* tabelaHash) {
    for (int i = 0; i < tabelaHash->tamanho; i++) {
        printf("Índice %d -> ", i);
        No* atual = tabelaHash->tabela[i];
        while (atual != NULL) {
            printf("%lld -> ", atual->dado);
            atual = atual->proximo;
        }
        printf("NULL\n");
    }
}

int main() {
    TabelaHash* tabelaHash = criarTabelaHash(1000);
    long long valores[] = {202470856591, 202470007444, 202270007548, 202178845847, 202080201121, 201571147145, 202480149372};
    int tamanho = sizeof(valores) / sizeof(valores[0]);
    for (int i = 0; i < tamanho; i++) {
        inserir(tabelaHash, valores[i]);
    }

    imprimirTabelaHash(tabelaHash);
    return 0;
}