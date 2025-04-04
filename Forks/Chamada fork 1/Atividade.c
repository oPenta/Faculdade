#include <unistd.h>

int main(){
    int id;
    id = fork();
    if(id != 0){
        printf("Eu sou o pai e espero pelo meu filho %d\n",id);
        waint(0);
        printf("Meu filho acabou de terminar... Vou terminar tambem\n");
    }
    else{
        printf("Eu sou o filho %d e espero 10 segundos \n", getpid());
        sleep(10);
        printf("Ja esperei e vou embora");
    }
}