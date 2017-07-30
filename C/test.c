#include <stdio.h>
#include <string.h>

#define ONE 1
#define TWO 2

void reverse(char str[]) {
    int c, i, j;
    for (i = 0, j = strlen(str) - 1; i < j ; i++, j--) {
        c = str[i];
        str[i] = str[j];
        str[j] = c;
    }
}

void strcopy(const char *from, char *to) {
    while ((*to++ = *from++));
}

// 负号开头的参数表示可选

int main(int argc, char *argv[]) {
    int a = ONE;
    printf("hello fork!%d", a);

    int b = 0;
    int c;
    while ((c = getchar()) != EOF) {
        // '\n'是单字符，是一个整型数，而"\n"是包含1 个字符以及1个结束符'\n'的字符数组
        if (c == '\n') {
            b++;
        }
    }

    // 枚举变量，默认第一个值为0，第二个值为1
    enum boolean {NO, YES};

    // const限定符
    const double e = 2.71828;

    char str[] = "fork";
    reverse(str);

    char *to;
    char *from = "Where are you from?";
    strcopy(from, to);

    // 联合体只能用第一个成员类型的值进行初始化
    union u_tag {
        int ival;
        float fval;
        char *sval;
    } u;

    printf("%d\n", b);
    printf("%s\n", str);
    printf("%s\n", to);
}

