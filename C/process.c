#include<stdio.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    pid_t p = fork();
    if (p < 0) {
        printf("fork error\n");
    } else if (p == 0) {
        printf("fork success child pid: %d\n", getpid());
    } else {
        printf("parent pid: %d\n", getpid());
        //sleep(2);
    }
    return 0;
}
