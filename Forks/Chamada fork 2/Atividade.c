#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();
    if (pid == 0) {

        sleep(5);
        printf("Filho: ");
        for (int i = 6; i <= 10; i++) {
            printf("%d ", i);
        }
        printf("\n");
        sleep(5);
        exit(0);
    }

    printf("Pai: ");
    for (int i = 1; i <= 5; i++) {
        printf("%d ", i);
    }
    printf("\n");

    wait(NULL);

    printf("Pai: ");
    for (int i = 11; i <= 15; i++) {
        printf("%d ", i);
    }
    printf("\n");

    return 0;
}