#include <stdio.h>
#include <stdlib.h>

#define ORDEM 7 

typedef struct noB {
    int chaves[ORDEM - 1];         
    struct noB *filhos[ORDEM];     
    int numChaves;                 
    int folha;                     
} NoB;


NoB* criarNoB(int folha) {
    NoB *novoNo = (NoB*) malloc(sizeof(NoB));
    novoNo->numChaves = 0; 
    novoNo->folha = folha;
    for (int x = 0; x < ORDEM; x++)
        novoNo->filhos[x] = NULL;
    return novoNo;
}

void dividirFilho(NoB* pai, int indice, NoB* cheio) {
    NoB* novoNo = criarNoB(cheio->folha);
    novoNo->numChaves = ORDEM / 2; 

    for (int x = 0; x < ORDEM / 2; x++) {
        novoNo->chaves[x] = cheio->chaves[x + ORDEM / 2];
    }
    if (!cheio->folha) {
        for (int x = 0; x <= ORDEM / 2; x++) {
            novoNo->filhos[x] = cheio->filhos[x + ORDEM / 2];
        }
    }
    cheio->numChaves = ORDEM / 2 - 1;
    for (int x = pai->numChaves; x >= indice + 1; x--) {
        pai->filhos[x + 1] = pai->filhos[x];
    }
    pai->filhos[indice + 1] = novoNo;
    for (int x = pai->numChaves - 1; x >= indice; x--) {
        pai->chaves[x + 1] = pai->chaves[x];
    }
    pai->chaves[indice] = cheio->chaves[ORDEM / 2 - 1];
    pai->numChaves++;
}


void inserirNaoCheio(NoB* no, int chave) {
    int x = no->numChaves - 1;

    if (no->folha) {
        while (x >= 0 && chave < no->chaves[x]) {
            no->chaves[x + 1] = no->chaves[x];
            x--;
        }
        no->chaves[x + 1] = chave;
        no->numChaves++;
    } else {
        while (x >= 0 && chave < no->chaves[x]) {
            x--;
        }
        x++;
        if (no->filhos[x]->numChaves == ORDEM - 1) {
            dividirFilho(no, x, no->filhos[x]);
            if (chave > no->chaves[x]) {
                x++;
            }
        }
        inserirNaoCheio(no->filhos[x], chave);
    }
}

void inserirB(NoB** raiz, int chave) {
    NoB* r = *raiz;

    if (r->numChaves == ORDEM - 1) {
        NoB* novaRaiz = criarNoB(0);
        novaRaiz->filhos[0] = r;

        dividirFilho(novaRaiz, 0, r);
        int x = 0;
        if (novaRaiz->chaves[0] < chave) {
            x++;
        }
        inserirNaoCheio(novaRaiz->filhos[x], chave);

        *raiz = novaRaiz;
    } else {
        inserirNaoCheio(r, chave);
    }
}

int contarChaves(NoB* no) {
    if (no == NULL)
        return 0;

    int total = no->numChaves;
    if (!no->folha) {
        for (int x = 0; x <= no->numChaves; x++) {
            total += contarChaves(no->filhos[x]);
        }
    }
    return total;
}

void preencherArrayChaves(NoB* no, int* array, int* index) {
    if (no == NULL) {
        return;
    }

    for (int x = 0; x < no->numChaves; x++) {
        if (!no->folha) {
            preencherArrayChaves(no->filhos[x], array, index);  
        }
        array[*index] = no->chaves[x];
        (*index)++;
    }
    if (!no->folha) {
        preencherArrayChaves(no->filhos[no->numChaves], array, index);
    }
}


void obterPagina(NoB* raiz, int pagina, int tamanhoPagina) {
    int totalChaves = contarChaves(raiz);  
    int* arrayChaves = (int*) malloc(totalChaves * sizeof(int)); 
    int index = 0;

    preencherArrayChaves(raiz, arrayChaves, &index);  

    int inicio = (pagina - 1) * tamanhoPagina;
    int fim = inicio + tamanhoPagina;
    if (fim > totalChaves) {
        fim = totalChaves;
    }

    if (inicio >= totalChaves) {
        printf("Pagina %d fora do limite.\n", pagina);
    } 
    else {
        printf("Pagina %d:\n", pagina);
        for (int x = inicio; x < fim; x++) {
            printf("%d ", arrayChaves[x]); 
        }
        printf("\n");
    }
    free(arrayChaves);
}

int main() {
    NoB* raiz = criarNoB(1); 

    int numValores;
    printf("Digite o numero de valores a serem inseridos: ");
    scanf("%d", &numValores);

    for (int i = 0; i < numValores; i++) {
        int valor;
        printf("Digite o valor %d: ", i + 1);
        scanf("%d", &valor);
        inserirB(&raiz, valor);
    }

    int tamanhoPagina;
    printf("Digite o tamanho da pagina: ");
    scanf("%d", &tamanhoPagina);

    int totalChaves = contarChaves(raiz);
    int numPaginas = (totalChaves + tamanhoPagina - 1) / tamanhoPagina;

    for (int pagina = 1; pagina <= numPaginas; pagina++) {
    obterPagina(raiz, pagina, tamanhoPagina);
    }

    return 0;
}
