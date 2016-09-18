#include <stdio.h>

int main(int argc, char *argv[]) {
    int numbers[] = {1, 3, 2, 7, 99, 10, 23, 20, 100, 4};
    int i, j, temp, len = 10;

    for (i = 0; i < len; i++) {
        for (j = i; j < len; j++) {
            if (numbers[i] > numbers[j]) {
                temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }
    }

    printf("bubble sorted: \n");
    for (i = 0; i < len; i++) {
        printf("%d, ", numbers[i]);
    }

    return 0;
}
