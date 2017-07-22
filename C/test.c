#include <stdio.h>

#define ONE 1
#define TWO 2

int main() {
    int a = ONE;
    printf("hello fork!%d", a);

    int b = 0;
    int c;
    while ((c = getchar()) != EOF) {
        // '\n'是单字符，是一个整型数，而"\n"是包含1 个字符的字符串常量
        if (c == '\n') {
            b++;
        }
    }

    printf("%d\n", b);
}
