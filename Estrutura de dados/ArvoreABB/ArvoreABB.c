#include <stdio.h>
#include <stdlib.h>

typedef struct no {
    int valor;
    struct no *esquerda;
    struct no *direita;
} No;

No* criarNo(int valor) {
    No *novoNo = (No*) malloc(sizeof(No));
    novoNo->valor = valor;
    novoNo->esquerda = NULL;
    novoNo->direita = NULL;
    return novoNo;
}

No* inserir(No* raiz, int valor) {
    if (raiz == NULL) {
        return criarNo(valor);
    }

    if (valor < raiz->valor) {
        raiz->esquerda = inserir(raiz->esquerda, valor);
    } else if (valor > raiz->valor) {
        raiz->direita = inserir(raiz->direita, valor);
    }
    return raiz;
}

int contarNos(No* raiz) {
    if (raiz == NULL) {
        return 0;
    }
    return 1 + contarNos(raiz->esquerda) + contarNos(raiz->direita);
}

void preencherArray(No* raiz, int* array, int* index) {
    if (raiz == NULL) {
        return;
    }
    preencherArray(raiz->esquerda, array, index);
    array[*index] = raiz->valor;
    (*index)++;
    preencherArray(raiz->direita, array, index);
}

void obterPagina(No* raiz, int pagina, int tamanhoPagina) {
    int totalNos = contarNos(raiz);  
    int* array = (int*) malloc(totalNos * sizeof(int));
    int index = 0;
    preencherArray(raiz, array, &index);
    int inicio = (pagina - 1) * tamanhoPagina;
    int fim = inicio + tamanhoPagina;
    if (fim > totalNos) {
        fim = totalNos;
    }
    if (inicio >= totalNos) {
        printf("Pagina %d fora do limite.\n", pagina);
    } 
    else {
        printf("Pagina %d:\n", pagina);
        for (int i = inicio; i < fim; i++) {
            printf("%d ", array[i]);
        }
        printf("\n");
    }
free(array);
}

int main() {
    No* raiz = NULL;
    raiz = inserir(raiz, 50);
    raiz = inserir(raiz, 30);
    raiz = inserir(raiz, 70);
    raiz = inserir(raiz, 20);
    raiz = inserir(raiz, 40);
    raiz = inserir(raiz, 60);
    raiz = inserir(raiz, 80);

    int tamanhoPagina = 4; 
    int pagina = 1;
    obterPagina(raiz, pagina, tamanhoPagina);
    obterPagina(raiz, pagina + 1, tamanhoPagina);

    return 0;
}



