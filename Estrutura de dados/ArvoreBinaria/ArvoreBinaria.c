#include <stdio.h>
#include <stdlib.h>

typedef struct no
{
    int dado;
    struct no *esquerda, *direita;
} No;

typedef struct
{
    No *raiz;
} ArvB;

void inserirEsquerda(No *no, int valor)
{
    if (no->esquerda == NULL)
    {
        No *novo = (No *)malloc(sizeof(No));
        novo->dado = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        no->esquerda = novo;
    }
    else
    {
        if (valor > no->esquerda->dado)
            inserirEsquerda(no->esquerda, valor);
        if (valor < no->esquerda->dado)
            inserirDireita(no->esquerda, valor);
    }
}

void inserirDireita(No *no, int valor)
{
    if (no->direita == NULL)
    {
        No *novo = (No *)malloc(sizeof(No));
        novo->dado = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        no->direita = novo;
    }
    else
    {
        if (valor > no->direita->dado)
            inserirDireita(no->direita, valor);
        if(valor < no->direita->dado)
            inserirEsquerda(no->direita, valor);
    }
}

void inserir(ArvB *arv, int valor)
{
    if (arv->raiz == NULL)
    {
        No *novo = (No *)malloc(sizeof(No));
        novo->dado = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        arv->raiz = novo;
    }
    else
    {
        if (valor < arv->raiz->dado)
            inserirEsquerda(arv->raiz, valor);
        if(valor > arv->raiz->dado)
            inserirDireita(arv->raiz, valor);
    }
}

void imprimir(No *raiz)
{
    if (raiz != NULL)
    {
        imprimir(raiz->esquerda);
        printf("%d ", raiz->dado); 
        imprimir(raiz->direita); 

    }
}

int main(void)
{
    int op, valor;
    ArvB arv;
    arv.raiz = NULL;
    do
    {
        printf("\n0- sair\n1- inserir\n2- imprimir\n");
        scanf("%d", &op);

        switch (op)
        {
        case 0:
            printf("\n Saindo...\n");
            break;
        case 1:
            printf("\n Digite um valor\n");
            scanf("%d", &valor);
            inserir(&arv, valor);
            break;
        case 2:
            printf("Impressao da Arvore binaria:\n");
            imprimir(arv.raiz);
            break;
        default:
            printf("Opção invalida");
        }
    } while (op != 0);

    return 0;
}