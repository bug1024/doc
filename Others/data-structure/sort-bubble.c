#include <stdio.h>

#define LENGTH(array) ((sizeof(array)) / (sizeof(array[0])))
#define SWAP(a,b) (a^=b,b^=a,a^=b)

/**
 * 冒泡排序
 * O(N^2)
 *
 * 遍历若干次要排序的数列，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；如果前者比后者大，则交换它们的位置。
 * 这样，一次遍历之后，最大的元素就在数列的末尾！ 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。重复此操作，直到整个数列都有序为止！
 */
int main(int argc, char *argv[]) {
    int numbers[] = {1, 3, 2, 7, 99, 10, 23, 20, 100, 4};
    int i, j, temp, len = LENGTH(numbers);

    for (i = 0; i < len; i++) {
        for (j = i; j < len; j++) {
            if (numbers[i] > numbers[j]) {
                SWAP(numbers[i], numbers[j]);
            }
        }
    }

    printf("bubble sorted: \n");
    for (i = 0; i < len; i++) {
        printf("%d, ", numbers[i]);
    }

    return 0;
}
