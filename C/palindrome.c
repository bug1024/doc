// check whether a number is palindrome or not
#include <stdio.h>
int main() {
    int n, reverse = 0, rem, temp;
    printf("Enter an integer: ");
    scanf("%d", &n);
    temp = n;
    while (temp!=0) {
        rem = temp % 10;
        reverse = reverse * 10 + rem;
        temp /= 10;
    }
    // check if number and it's reverse number is equal
    if (reverse == n) {
        printf("%d is a palindrome.", n);
    } else {
        printf("%d is not a palindrome.", n);
    }

    return 0;
}

